package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import kr.co.popool.bblmember.repository.CorporateRepository;
import kr.co.popool.bblmember.repository.MemberMstRepository;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMstServiceImpl implements MemberMstService{

    private final MemberMstRepository memberMstRepository;
    private final MemberRepository memberRepository;
    private final CorporateRepository corporateRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 일반 회원가입
     * @param create 회원가입하기 위한 회원의 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void signUp(MemberMstDto.CREATE create) {

        if(!checkIdentity(create.getIdentity())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }

        if(!create.getPassword().equals(create.getCheckPassword())){
            throw new BadRequestException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        if(!checkPhone(new Phone(create.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        //TODO : 회원 권한은 관리자가 아니라면 모두 ROLE_MEMBER으로 자동 설정, 기업 회원 가입이기 NORMAR_MEMBER 자동 설정.

        MemberMstEntity memberMstEntity = MemberMstEntity.builder()
                .identity(create.getIdentity())
                .password(passwordEncoder.encode(create.getPassword()))
                .name(create.getName())
                .birth(create.getBirth())
                .phone(new Phone(create.getPhone()))
                .gender(Gender.of(create.getGender()))
                .memberRank(MemberRank.of(create.getMemberRank()))
                .memberRole(MemberRole.of(create.getMemberRole()))
                .build();

        MemberEntity memberEntity = MemberEntity.builder()
                .memberMstEntity(memberMstEntity)
                .build();

        memberMstRepository.save(memberMstEntity);
        memberRepository.save(memberEntity);
    }

    /**
     * 기업 회원가입
     * @param create_corporate 회원가입하기 위한 회원의 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void corporateSignUp(MemberMstDto.CREATE_CORPORATE create_corporate) {

        if(!checkIdentity(create_corporate.getIdentity())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }

        if(!create_corporate.getPassword().equals(create_corporate.getCheckPassword())){
            throw new BadRequestException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        if(!checkPhone(new Phone(create_corporate.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        //TODO : 회원 권한은 관리자가 아니라면 모두 ROLE_MEMBER으로 자동 설정, 기업 회원 가입이기 CORPORATE_MEMBER 자동 설정.

        MemberMstEntity memberMstEntity = MemberMstEntity.builder()
                .identity(create_corporate.getIdentity())
                .password(passwordEncoder.encode(create_corporate.getPassword()))
                .name(create_corporate.getName())
                .birth(create_corporate.getBirth())
                .phone(new Phone(create_corporate.getPhone()))
                .gender(Gender.of(create_corporate.getGender()))
                .memberRank(MemberRank.of(create_corporate.getMemberRank()))
                .memberRole(MemberRole.of(create_corporate.getMemberRole()))
                .build();

        CorporateEntity corporateEntity = CorporateEntity.builder()
                .ceoName(create_corporate.getCeoName())
                .businessName(create_corporate.getBusinessName())
                .businessNumber(create_corporate.getBusinessNumber())
                .memberMstEntity(memberMstEntity)
                .build();

        memberMstRepository.save(memberMstEntity);
        corporateRepository.save(corporateEntity);
    }

    /**
     * 로그인
     * @param login : 로그인하기 위한 아이디 패스워드
     * @return : 회원의 AccessToken과 RefreshToken을 담은 객체
     * @Exception BadRequestException : 아이디 혹은 비밀번호가 틀렸을 경우 발생하는 예외.
     */
    @Transactional
    @Override
    public MemberMstDto.TOKEN login(MemberMstDto.LOGIN login) {

        MemberMstEntity memberMstEntity = memberMstRepository.findByIdentity(login.getIdentity())
                .orElseThrow(() -> new BadRequestException("아이디나 비밀번호를 다시 확인해주세요"));

        if(!passwordEncoder.matches(login.getPassword(), memberMstEntity.getPassword())){
            throw new BadRequestException("아이디나 비밀번호를 다시 확인해주세요");
        }

        String[] tokens = generateToken(memberMstEntity);
        memberMstEntity.updateRefreshToken(tokens[1]);

        return new MemberMstDto.TOKEN(tokens[0], tokens[1]);
    }

    /**
     * 아이디 중복 체크
     * @param identity : 중복 체크할 아이디
     * @return : 중복된 아이디가 있다면 false, 없다면 true
     */
    @Override
    public Boolean checkIdentity(String identity) {
        return !memberMstRepository.existsByIdentity(identity);
    }

    /**
     * 이메일 중복 체크
     * @param email : 중복 체크할 이메일
     * @return : 중복된 이메일이 있다면 false, 없다면 true
     */
    @Override
    public Boolean checkEmail(String email) {
        return!memberMstRepository.existsByEmail(email);
    }

    /**
     * 전화번호 중복 체크
     * @param phone : 중복 체크할 전화번호
     * @return : 중복된 전화번호가 있다면 false, 없다면 true
     */
    @Override
    public Boolean checkPhone(Phone phone) {
        return !memberMstRepository.existsByPhone(phone);
    }

    private String[] generateToken(MemberMstEntity memberMstEntity){
        String accessToken = jwtProvider.createAccessToken(memberMstEntity.getIdentity()
                , memberMstEntity.getMemberRole(), memberMstEntity.getName());
        String refreshToken = jwtProvider.createRefreshToken(memberMstEntity.getIdentity()
                , memberMstEntity.getMemberRole(), memberMstEntity.getName());

        return new String[]{accessToken, refreshToken};
    }
}
