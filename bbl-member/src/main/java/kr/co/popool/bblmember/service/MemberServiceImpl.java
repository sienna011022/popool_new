package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.BusinessLogicException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
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
    private final RedisService redisService;
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

        checkEncodePassword(login.getPassword(), memberEntity.getPassword());
        if(checkDelete(memberEntity)) throw new BadRequestException("탈퇴한 회원입니다.");

        String[] tokens = generateToken(memberEntity);
        redisService.createData(memberEntity.getIdentity(), tokens[1], jwtProvider.getRefreshExpire());
        return new MemberDto.TOKEN(tokens[0], tokens[1]);
    }

    /**
     * 일반 회원가입
     * @param create 회원가입을 위한 회원 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void signUp(MemberDto.CREATE create) {
        checkSignUp(create);
        MemberEntity memberEntity = MemberEntity.of(create, passwordEncoder, null);
        memberRepository.save(memberEntity);
    }

    /**
     * 개인 정보 수정
     * @param update : 변경할 데이터
     */
    @Override
    public void update(MemberDto.UPDATE update) {
        MemberEntity memberEntity = getThreadLocal();

        if(!memberEntity.getPhone().equals(new Phone(update.getPhone()))) checkPhone(new Phone(update.getPhone()));
        if(!memberEntity.getEmail().equals(update.getEmail())) checkEmail(update.getEmail());

        memberEntity.updateMember(update);
        memberRepository.save(memberEntity);
    }

    /**
     * 비밀번호 수정
     * @param updatePassword : 변경할 데이터
     */
    @Override
    @Transactional
    public void updatePassword(MemberDto.UPDATE_PASSWORD updatePassword) {
        MemberEntity memberEntity = getThreadLocal();
        checkEncodePassword(updatePassword.getOriginalPassword(), memberEntity.getPassword());
        checkPassword(updatePassword.getNewPassword(), updatePassword.getNewCheckPassword());
        memberEntity.updatePassword(passwordEncoder.encode(updatePassword.getNewPassword()));
        memberRepository.save(memberEntity);
    }

    /**
     * 주소 수정
     * @param updateAddress : 변경할 데이터
     */
    @Override
    public void updateAddress(MemberDto.UPDATE_ADDRESS updateAddress) {
        MemberEntity memberEntity = getThreadLocal();
        memberEntity.updateAddress(updateAddress);
        memberRepository.save(memberEntity);
    }

    /**
     * 전화번호 수정
     * @param updatePhone : 변경할 데이터
     */
    @Override
    public void updatePhone(MemberDto.UPDATE_PHONE updatePhone) {
        MemberEntity memberEntity = getThreadLocal();
        memberEntity.updatePhone(updatePhone);
        memberRepository.save(memberEntity);
    }

    /**
     * 결제 동의 여부 수정
     */
    @Override
    public void paymentAgreeUpdate() {
        MemberEntity memberEntity = getThreadLocal();

        if(memberEntity.getPaymentAgree_yn().equals("N")) memberEntity.agree();
        else if (memberEntity.getPaymentAgree_yn().equals("Y")) memberEntity.disagree();

        memberRepository.save(memberEntity);
    }

    /**
     * 자기 자신 정보 조회
     * @return : 자신의 정보
     */
    @Override
    public MemberDto.READ get() {
        MemberEntity memberEntity = getThreadLocal();

        return MemberDto.READ.builder()
                .id(memberEntity.getId())
                .identity(memberEntity.getIdentity())
                .name(memberEntity.getName())
                .address(memberEntity.getAddress())
                .birth(memberEntity.getBirth())
                .email(memberEntity.getEmail())
                .phone(memberEntity.getPhone())
                .gender(memberEntity.getGender())
                .memberRank(memberEntity.getMemberRank())
                .create_at(memberEntity.getCreatedAt())
                .build();
    }

    /** 아이디 찾기
     * @param readId : 아이디를 찾기 위한 이름, 전화번호, 생년월일을 포함한 객체
     * @return : 찾은 아이디
     * @Exception NotFoundException : 해당 회원이 없을 경우 발생하는 에러.
     */
    @Override
    public String findIdentity(MemberDto.READ_ID readId) {
        return memberRepository
                .findByNameAndPhoneAndBirth(readId.getName(), new Phone(readId.getPhone()), readId.getBirth())
                .orElseThrow(() -> new NotFoundException("MemberEntity"))
                .getIdentity();
    }

    /**
     * 주소 등록 여부
     * @return 주소 등록 안되어있으면 false 되어 있으면 true
     */
    @Override
    public boolean getAddress() {
        MemberEntity memberEntity = getThreadLocal();
        return memberEntity.getAddress() != null;
    }

    /**
     * 자동 결제 동의 여부 조회
     * @return 동의라면 true 미동의라면 false
     */
    @Override
    public boolean getPaymentAgree() {
        MemberEntity memberEntity = getThreadLocal();
        return memberEntity.getPaymentAgree_yn() == "Y";
    }

    @Override
    public MemberEntity getThreadLocal() {
        String identity = MemberThreadLocal.get();
        return memberRepository.findByIdentity(identity)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 회원입니다."));
    }

    /**
     * 회원 탈퇴
     * @param password : 비밀번호
     */
    @Override
    public void delete(String password) {
        MemberEntity memberEntity = getThreadLocal();
        checkEncodePassword(password, memberEntity.getPassword());

        if(checkDelete(memberEntity)) throw new BadRequestException("이미 탈퇴한 회원입니다.");

        memberEntity.deleted();
        memberRepository.save(memberEntity);
    }

    /**
     * 탈퇴한 아이디 복구하기.
     * @param reCreate : 복구할 회원 정보
     */
    @Override
    public void reCreate(MemberDto.RE_CREATE reCreate) {
        MemberEntity memberEntity = memberRepository.findByIdentity(reCreate.getIdentity())
                .orElseThrow(() -> new BadRequestException("아이디나 비밀번호를 다시 확인해주세요"));

        if(!checkDelete(memberEntity)) throw new BadRequestException("탈퇴한 회원이 아닙니다.");

        checkPassword(reCreate.getPassword(), memberEntity.getPassword());
        checkUpdatePhone(new Phone(reCreate.getPhone()), memberEntity.getPhone());
        memberEntity.reCreated();
        memberRepository.save(memberEntity);
    }

    @Override
    public void deleteRefreshToken(String identity) {
        redisService.deleteData(identity);
    }

    /**
     * 아이디 중복 체크
     * @param identity : 중복 체크할 아이디
     * @Return 중복된 identity가 없다면 true
     */
    @Override
    public void checkIdentity(String identity) {
        if (memberRepository.existsByIdentity(identity)) throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
    }

    /**
     * 이메일 중복 체크
     * @param email : 중복 체크할 이메일
     * @Return 중복된 email 없다면 true
     */
    @Override
    public void checkEmail(String email) {
        if(memberRepository.existsByEmail(email)) throw new DuplicatedException(ErrorCode.DUPLICATED_EMAIL);
    }

    /**
     * 전화번호 중복 체크
     * @param phone : 중복 체크할 전화번호
     * @Return 중복된 전화번호가 없다면 true
     */
    @Override
    public void checkPhone(Phone phone) {
        if(memberRepository.existsByPhone(phone)) throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
    }

    /**
     * 전화번호 검사
     * @param phone
     * @param checkPhone
     * @return
     */
    @Override
    public void checkUpdatePhone(Phone phone, Phone checkPhone) {
        if(!phone.equals(checkPhone)) throw new DuplicatedException(ErrorCode.WRONG_PHONE);
    }

    /**
     * 비밀번호 체크
     * @param password 비밀번호
     * @param checkPassword 확인할 비밀번호
     */
    @Override
    public void checkPassword(String password, String checkPassword) {
        if(!password.equals(checkPassword)) throw new BusinessLogicException(ErrorCode.WRONG_PASSWORD);
    }

    /**
     * 암호화된 비밀번호 체크
     * @param password 확인할 비밀번호
     * @param encodePassword 암호화된비밀번호
     */
    @Override
    public void checkEncodePassword(String password, String encodePassword) {
        if(!passwordEncoder.matches(password, encodePassword)) throw new BusinessLogicException(ErrorCode.WRONG_PASSWORD);
    }

    @Override
    public boolean checkDelete(MemberEntity memberEntity) {
        if(memberEntity.getDel_yn().equals("N")) return false;
        return true;
    }

    /**
     * 회원가입에 필요한 체크 사항.
     * @return 중복 됐다면 false, 없다면 true
     */
    @Override
    public void checkSignUp(MemberDto.CREATE create) {
        checkIdentity(create.getIdentity());
        checkPhone(new Phone(create.getPhone()));
        checkPassword(create.getPassword(), create.getCheckPassword());
    }

    /**
     * 기업 회원 가입 시 필요한 체크 사항.
     * @param create
     */
    @Override
    public void checkCorporateSignUp(CorporateDto.CREATE_CORPORATE create) {
        checkIdentity(create.getCreate().getIdentity());
        checkPhone(new Phone(create.getCreate().getPhone()));
        checkPassword(create.getCreate().getPassword(), create.getCreate().getCheckPassword());
    }

    private String[] generateToken(MemberEntity memberEntity){
        String accessToken = jwtProvider.createAccessToken(memberEntity.getIdentity()
                , memberEntity.getMemberRole(), memberEntity.getName());
        String refreshToken = jwtProvider.createRefreshToken(memberEntity.getIdentity()
                , memberEntity.getMemberRole(), memberEntity.getName());
        return new String[]{accessToken, refreshToken};
    }
}