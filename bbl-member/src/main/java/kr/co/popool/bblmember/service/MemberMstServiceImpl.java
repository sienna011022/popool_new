package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.BusinessLogicException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.infra.interceptor.CorporateThreadLocal;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import kr.co.popool.bblmember.repository.MemberMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMstServiceImpl implements MemberMstService{

    private final MemberMstRepository memberMstRepository;
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

    @Override
    @Transactional
    public void update(MemberMstDto.UPDATE update) {
        MemberEntity memberEntity = MemberThreadLocal.get();
        CorporateEntity corporateEntity = CorporateThreadLocal.get();
        MemberMstEntity memberMstEntity = null;

        if(memberEntity != null){
            memberMstEntity = memberEntity.getMemberMstEntity();
        }
        if(corporateEntity != null){
            memberMstEntity = corporateEntity.getMemberMstEntity();
        }
        if(memberMstEntity == null){
            throw new BusinessLogicException(ErrorCode.RE_LOGIN);
        }

        if(!checkPhone(new Phone(update.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }
        if(!checkEmail(update.getEmail())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_EMAIL);
        }

        memberMstEntity.updateMemberMst(update);
        memberMstEntity.updateUseMember(memberMstEntity.getId());
        memberMstRepository.save(memberMstEntity);
    }

    @Override
    @Transactional
    public void updatePassword(MemberMstDto.UPDATE_PASSWORD update_password) {
        MemberEntity memberEntity = MemberThreadLocal.get();
        CorporateEntity corporateEntity = CorporateThreadLocal.get();
        MemberMstEntity memberMstEntity = null;

        if(memberEntity != null){
            memberMstEntity = memberEntity.getMemberMstEntity();
        }
        if(corporateEntity != null){
            memberMstEntity = corporateEntity.getMemberMstEntity();
        }
        if(memberMstEntity == null){
            throw new BusinessLogicException(ErrorCode.RE_LOGIN);
        }
        if(!passwordEncoder.matches(update_password.getOriginalPassword(), memberMstEntity.getPassword())){
            throw new BusinessLogicException(ErrorCode.WRONG_PASSWORD);
        }
        if(!update_password.getNewPassword().equals(update_password.getNewCheckPassword())){
            throw new BadRequestException("새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        memberMstEntity.updatePassword(passwordEncoder.encode(update_password.getNewPassword()));
        memberMstEntity.updateUseMember(memberMstEntity.getId());
        memberMstRepository.save(memberMstEntity);
    }

    @Override
    @Transactional
    public void updateAddress(MemberMstDto.UPDATE_ADDRESS update_address) {
        MemberEntity memberEntity = MemberThreadLocal.get();
        CorporateEntity corporateEntity = CorporateThreadLocal.get();
        MemberMstEntity memberMstEntity = null;

        if(memberEntity != null){
            memberMstEntity = memberEntity.getMemberMstEntity();
        }
        if(corporateEntity != null){
            memberMstEntity = corporateEntity.getMemberMstEntity();
        }
        if(memberMstEntity == null){
            throw new BusinessLogicException(ErrorCode.RE_LOGIN);
        }

        memberMstEntity.updateAddress(update_address);
        memberMstEntity.updateUseMember(memberMstEntity.getId());
        memberMstRepository.save(memberMstEntity);
    }

    @Override
    @Transactional
    public void updatePhone(MemberMstDto.UPDATE_PHONE update_phone) {
        MemberEntity memberEntity = MemberThreadLocal.get();
        CorporateEntity corporateEntity = CorporateThreadLocal.get();
        MemberMstEntity memberMstEntity = null;

        if(memberEntity != null){
            memberMstEntity = memberEntity.getMemberMstEntity();
        }
        if(corporateEntity != null){
            memberMstEntity = corporateEntity.getMemberMstEntity();
        }
        if(memberMstEntity == null){
            throw new BusinessLogicException(ErrorCode.RE_LOGIN);
        }

        memberMstEntity.updatePhone(update_phone);
        memberMstEntity.updateUseMember(memberMstEntity.getId());
        memberMstRepository.save(memberMstEntity);
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