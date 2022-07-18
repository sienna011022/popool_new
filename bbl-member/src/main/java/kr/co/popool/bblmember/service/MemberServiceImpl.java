package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.BusinessLogicException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 로그인
     * @param login : 로그인하기 위한 아이디 패스워드
     * @return : 회원의 AccessToken과 RefreshToken을 담은 객체
     * @Exception BadRequestException : 아이디 혹은 비밀번호가 틀렸을 경우 발생하는 예외.
     */
    @Transactional
    @Override
    public MemberDto.TOKEN login(MemberDto.LOGIN login) {

        MemberEntity memberEntity = memberRepository.findByIdentity(login.getIdentity())
                .orElseThrow(() -> new BadRequestException("아이디나 비밀번호를 다시 확인해주세요"));

        if(!passwordEncoder.matches(login.getPassword(), memberEntity.getPassword())){
            throw new BadRequestException("아이디나 비밀번호를 다시 확인해주세요");
        }

        String[] tokens = generateToken(memberEntity);
        memberEntity.updateRefreshToken(tokens[1]);

        return new MemberDto.TOKEN(tokens[0], tokens[1]);
    }

    @Override
    public void signUp(MemberDto.CREATE create) {
        if(!checkIdentity(create.getIdentity())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }

        if(!create.getPassword().equals(create.getCheckPassword())){
            throw new BadRequestException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        if(!checkPhone(new Phone(create.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        MemberEntity memberEntity = MemberEntity.builder()
                .identity(create.getIdentity())
                .password(passwordEncoder.encode(create.getPassword()))
                .name(create.getName())
                .birth(create.getBirth())
                .phone(new Phone(create.getPhone()))
                .gender(Gender.of(create.getGender()))
                .memberRank(MemberRank.of(create.getMemberRank()))
                .memberRole(MemberRole.of(create.getMemberRole()))
                .corporateEntity(null)
                .build();

        memberRepository.save(memberEntity);
    }

    /**
     * 개인 정보 수정
     * @param update : 변경할 데이터
     */
    @Override
    @Transactional
    public void update(MemberDto.UPDATE update) {
        MemberEntity memberEntity = MemberThreadLocal.get();

        if(!checkPhone(new Phone(update.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }
        if(!checkEmail(update.getEmail())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_EMAIL);
        }

        memberEntity.updateMember(update);
        memberEntity.updateUseMember(memberEntity.getId());
        memberRepository.save(memberEntity);
    }

    /**
     * 비밀번호 수정
     * @param update_password : 변경할 데이터
     */
    @Override
    @Transactional
    public void updatePassword(MemberDto.UPDATE_PASSWORD update_password) {
        MemberEntity memberEntity = MemberThreadLocal.get();

        if(!passwordEncoder.matches(update_password.getOriginalPassword(), memberEntity.getPassword())){
            throw new BusinessLogicException(ErrorCode.WRONG_PASSWORD);
        }
        if(!update_password.getNewPassword().equals(update_password.getNewCheckPassword())){
            throw new BadRequestException("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        memberEntity.updatePassword(passwordEncoder.encode(update_password.getNewPassword()));
        memberEntity.updateUseMember(memberEntity.getId());
        memberRepository.save(memberEntity);
    }

    /**
     * 주소 수정
     * @param update_address : 변경할 데이터
     */
    @Override
    @Transactional
    public void updateAddress(MemberDto.UPDATE_ADDRESS update_address) {
        MemberEntity memberEntity = MemberThreadLocal.get();

        memberEntity.updateAddress(update_address);
        memberEntity.updateUseMember(memberEntity.getId());
        memberRepository.save(memberEntity);
    }

    /**
     * 전화번호 수정
     * @param update_phone : 변경할 데이터
     */
    @Override
    @Transactional
    public void updatePhone(MemberDto.UPDATE_PHONE update_phone) {
        MemberEntity memberEntity = MemberThreadLocal.get();

        memberEntity.updatePhone(update_phone);
        memberEntity.updateUseMember(memberEntity.getId());
        memberRepository.save(memberEntity);
    }

    @Override
    public void paymentAgreeUpdate() {
        MemberEntity memberEntity = MemberThreadLocal.get();

        if(memberEntity.getPaymentAgree_yn().equals("N")){
            memberEntity.agree();
        }
        if (memberEntity.getPaymentAgree_yn().equals("Y")){
            memberEntity.disagree();
        }

        memberEntity.updateUseMember(memberEntity.getId());
        memberRepository.save(memberEntity);
    }

    @Override
    public MemberDto.READ get() {
        MemberEntity memberEntity = MemberThreadLocal.get();

        MemberDto.READ read = MemberDto.READ.builder()
                .id(memberEntity.getId())
                .identity(memberEntity.getIdentity())
                .name(memberEntity.getName())
                .address(memberEntity.getAddress())
                .birth(memberEntity.getBirth())
                .email(memberEntity.getEmail())
                .phone(memberEntity.getPhone())
                .gender(memberEntity.getGender())
                .memberRank(memberEntity.getMemberRank())
                .create_at(memberEntity.getCreated_at())
                .build();

        return read;
    }

    //TODO
    @Override
    public boolean getAddress() {
        return false;
    }

    /**
     * 아이디 중복 체크
     * @param identity : 중복 체크할 아이디
     * @return : 중복된 아이디가 있다면 false, 없다면 true
     */
    @Override
    public boolean checkIdentity(String identity) {
        return !memberRepository.existsByIdentity(identity);
    }

    /**
     * 이메일 중복 체크
     * @param email : 중복 체크할 이메일
     * @return : 중복된 이메일이 있다면 false, 없다면 true
     */
    @Override
    public boolean checkEmail(String email) {
        return!memberRepository.existsByEmail(email);
    }

    /**
     * 전화번호 중복 체크
     * @param phone : 중복 체크할 전화번호
     * @return : 중복된 전화번호가 있다면 false, 없다면 true
     */
    @Override
    public boolean checkPhone(Phone phone) {
        return !memberRepository.existsByPhone(phone);
    }

    private String[] generateToken(MemberEntity memberEntity){
        String accessToken = jwtProvider.createAccessToken(memberEntity.getIdentity()
                , memberEntity.getMemberRole(), memberEntity.getName());
        String refreshToken = jwtProvider.createRefreshToken(memberEntity.getIdentity()
                , memberEntity.getMemberRole(), memberEntity.getName());

        return new String[]{accessToken, refreshToken};
    }
}