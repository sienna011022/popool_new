package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.shared.Phone;

public interface MemberMstService {

    //create
    void signUp(MemberMstDto.CREATE create);
    void corporateSignUp(MemberMstDto.CREATE_CORPORATE create_corporate);

    //login
    MemberMstDto.TOKEN login(MemberMstDto.LOGIN login);

    //common
    Boolean checkIdentity(String identity);
    Boolean checkEmail(String email);
    Boolean checkPhone(Phone phone);
}
