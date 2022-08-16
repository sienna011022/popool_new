package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.OauthDto;
import org.springframework.http.ResponseEntity;

public interface OauthService {

    //common
    OauthDto.PROFILE checkProfile(ResponseEntity<String> response, String provider);

    //login
    OauthDto.TOKEN_READ login(OauthDto.LOGIN login);

    //create
    void saveAdditionalMemberInfo(OauthDto.CREATE create);
    void saveAdditionalCorporateInfo(OauthDto.CREATE_CORPORATE create);

    //get
    OauthDto.PROFILE getProfile(String accessToken, String provider);
    OauthDto.TOKEN_INFO getAccessToken(OauthDto.LOGIN login);


}
