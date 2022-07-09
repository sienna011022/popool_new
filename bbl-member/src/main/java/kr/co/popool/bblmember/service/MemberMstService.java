package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberMstDto;

public interface MemberMstService {

    //create
    void signUp(MemberMstDto.CREATE create);

    //login
    MemberMstDto.TOKEN login(MemberMstDto.LOGIN login);

    //common
    Boolean checkIdentity(String identity);
}
