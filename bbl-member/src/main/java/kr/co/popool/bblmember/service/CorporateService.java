package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.CorporateDto;

public interface CorporateService {

    //create
    void corporateSignUp(CorporateDto.CREATE_CORPORATE create_corporate);

    //update
    void corporateUpdate(CorporateDto.UPDATE_CORPORATE update_corporate);

}
