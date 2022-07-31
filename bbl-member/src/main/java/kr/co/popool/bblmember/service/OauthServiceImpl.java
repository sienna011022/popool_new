package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.OauthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{

    @Override
    public void signUpKakao(OauthDto.CREATE create) {
        //TODO : kakao 회원가입
    }

    @Override
    public void signUpNaver(OauthDto.CREATE create) {
        //TODO : naver 회원가입
    }

    @Override
    public void signUpGoogle(OauthDto.CREATE create) {
        //TODO : google 회원가입
    }

    @Override
    public OauthDto.TOKEN_READ loginKakao(OauthDto.LOGIN login) {
        //TODO : kakao login
        return null;
    }

    @Override
    public OauthDto.TOKEN_READ loginNaver(OauthDto.LOGIN login) {
        //TODO : naver login
        return null;
    }

    @Override
    public OauthDto.TOKEN_READ loginGoogle(OauthDto.LOGIN login) {
        //TODO : google login
        return null;
    }
}
