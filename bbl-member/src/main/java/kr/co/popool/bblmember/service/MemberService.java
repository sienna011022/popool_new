package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberDto;

public interface MemberService {

    //create
    void signUp(MemberDto.CREATE create);

}
