package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberMstDto;

public interface MemberService {

    //update
    void update(MemberMstDto.UPDATE memberUpdate);
}
