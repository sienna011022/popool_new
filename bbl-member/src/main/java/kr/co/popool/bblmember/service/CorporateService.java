package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;

public interface CorporateService {

    //create
    void corporateSignUp(CorporateDto.CREATE_CORPORATE create_corporate);

}
