package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.dto.OauthDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.Phone;

public interface MemberService {

    //login
    MemberDto.TOKEN login(MemberDto.LOGIN login);

    //create
    void signUp(MemberDto.CREATE create);

    //update
    void update(MemberDto.UPDATE update);
    void updatePassword(MemberDto.UPDATE_PASSWORD updatePpassword);
    void updateAddress(MemberDto.UPDATE_ADDRESS updateAddress);
    void updatePhone(MemberDto.UPDATE_PHONE updatePhone);
    void paymentAgreeUpdate();

    //get
    MemberDto.READ get();
    String findIdentity(MemberDto.READ_ID readId);
    boolean getAddress();
    boolean getPaymentAgree();
    MemberEntity getThreadLocal();

    //delete
    void delete(String password);
    void reCreate(MemberDto.RE_CREATE reCreat);
    void deleteRefreshToken(String identity);

    //common
    void checkIdentity(String identity);
    void checkEmail(String email);
    void checkPhone(Phone phone);
    void checkUpdatePhone(Phone phone, Phone checkPhone);

    void checkPassword(String password, String checkPassword);
    void checkEncodePassword(String password, String encodePassword);

    boolean checkDelete(MemberEntity memberEntity);
    void checkSignUp(MemberDto.CREATE create);
    void checkOauthSignUp(OauthDto.CREATE create);
    void checkCorporateSignUp(CorporateDto.CREATE_CORPORATE create);
}
