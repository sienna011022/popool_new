package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.OauthDto;

public interface OauthService {

    //create
    void signUpKakao(OauthDto.CREATE create);
    void signUpNaver(OauthDto.CREATE create);
    void signUpGoogle(OauthDto.CREATE create);

    //login
    OauthDto.TOKEN_READ loginKakao(OauthDto.LOGIN login);
    OauthDto.TOKEN_READ loginNaver(OauthDto.LOGIN login);
    OauthDto.TOKEN_READ loginGoogle(OauthDto.LOGIN login);


}
