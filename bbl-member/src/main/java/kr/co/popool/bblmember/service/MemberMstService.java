package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.shared.Phone;

public interface MemberMstService {

    //login
    MemberMstDto.TOKEN login(MemberMstDto.LOGIN login);

    //get
    MemberMstDto.READ get();

    //update
    void update(MemberMstDto.UPDATE update);
    void updatePassword(MemberMstDto.UPDATE_PASSWORD update_password);
    void updateAddress(MemberMstDto.UPDATE_ADDRESS update_address);
    void updatePhone(MemberMstDto.UPDATE_PHONE update_phone);

    //common
    Boolean checkIdentity(String identity);
    Boolean checkEmail(String email);
    Boolean checkPhone(Phone phone);
}
