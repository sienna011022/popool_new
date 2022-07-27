package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.shared.Phone;

public interface MemberService {

    //login
    MemberDto.TOKEN login(MemberDto.LOGIN login);

    //create
    void signUp(MemberDto.CREATE create);

    //update
    void update(MemberDto.UPDATE update);
    void updatePassword(MemberDto.UPDATE_PASSWORD update_password);
    void updateAddress(MemberDto.UPDATE_ADDRESS update_address);
    void updatePhone(MemberDto.UPDATE_PHONE update_phone);
    void paymentAgreeUpdate();

    //get
    MemberDto.READ get();
    String findIdentity(MemberDto.READ_ID read_id);
    boolean getAddress();
    boolean getPaymentAgree();

    //delete
    void delete(String password);
    void reCreate(MemberDto.RE_CREATE re_creat);

    //common
    boolean checkIdentity(String identity);
    boolean checkEmail(String email);
    boolean checkPhone(Phone phone);
}
