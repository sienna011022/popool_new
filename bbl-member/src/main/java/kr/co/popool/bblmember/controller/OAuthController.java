package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.OauthDto;
import kr.co.popool.bblmember.service.OauthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final OauthServiceImpl oauthService;

    @ApiOperation("카카오 회원가입")
    @PostMapping("/kakao")
    public ResponseFormat signUpKakao(@RequestBody OauthDto.CREATE create){
        oauthService.signUpKakao(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("네이버 회원가입")
    @PostMapping("/naver")
    public ResponseFormat signUpNaver(@RequestBody OauthDto.CREATE create){
        oauthService.signUpNaver(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("구글 회원가입")
    @PostMapping("/google")
    public ResponseFormat signUpGoogle(@RequestBody OauthDto.CREATE create){
        oauthService.signUpGoogle(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("카카오 로그인")
    @PostMapping("/kakao/login")
    public ResponseFormat<OauthDto.TOKEN_READ> loginKAKAO(@RequestBody OauthDto.LOGIN login){
        return ResponseFormat.ok(oauthService.loginKakao(login));
    }

    @ApiOperation("네이버 로그인")
    @PostMapping("/naver/login")
    public ResponseFormat<OauthDto.TOKEN_READ> loginNAVER(@RequestBody OauthDto.LOGIN login){
        return ResponseFormat.ok(oauthService.loginNaver(login));
    }

    @ApiOperation("구글 로그인")
    @PostMapping("/google/login")
    public ResponseFormat<OauthDto.TOKEN_READ> signUpGOOGLE(@RequestBody OauthDto.LOGIN login){
        return ResponseFormat.ok(oauthService.loginGoogle(login));
    }


}
