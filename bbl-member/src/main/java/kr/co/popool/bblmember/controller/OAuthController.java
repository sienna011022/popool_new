package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.OauthDto;
import kr.co.popool.bblmember.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequiredArgsConstructor
//@RequestMapping("/oauth")
public class OAuthController {

    private final OauthService oauthService;

    @PostMapping("/kakao")
    public ResponseFormat<String> getKey(){
        return ResponseFormat.ok("https://kauth.kakao.com/oauth/authorize?client_id=dd9e4fedf13f64b018f9f2ec47207136&redirect_uri=http://localhost:8080/oauth/kakao/login&response_type=code");
    }

    @ApiOperation("카카오 회원가입")
    @PostMapping("/kakao/login/singUp")
    public ResponseFormat signUpKakao(@RequestBody OauthDto.CREATE create){
        oauthService.saveAdditionalMemberInfo(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("네이버 회원가입")
    @PostMapping("/naver/login/singUp")
    public ResponseFormat signUpNaver(@RequestBody OauthDto.CREATE create){
        oauthService.saveAdditionalMemberInfo(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("구글 회원가입")
    @PostMapping("/google/login/singUp")
    public ResponseFormat signUpGoogle(@RequestBody OauthDto.CREATE create){
        oauthService.saveAdditionalMemberInfo(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("카카오 기업 회원가입")
    @PostMapping("/kakao/login/singUp/corporate")
    public ResponseFormat signUpCorporateKakao(@RequestBody OauthDto.CREATE_CORPORATE create){
        oauthService.saveAdditionalCorporateInfo(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("네이버 기업 회원가입")
    @PostMapping("/naver/login/singUp/corporate")
    public ResponseFormat signUpCorporateNaver(@RequestBody OauthDto.CREATE_CORPORATE create){
        oauthService.saveAdditionalCorporateInfo(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("구글 기업 회원가입")
    @PostMapping("/google/login/singUp/corporate")
    public ResponseFormat signUpCorporateGoogle(@RequestBody OauthDto.CREATE_CORPORATE create){
        oauthService.saveAdditionalCorporateInfo(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("카카오 로그인")
    @PostMapping("/kakao/login")
    public ResponseFormat<OauthDto.TOKEN_READ> loginKAKAO(@RequestBody OauthDto.LOGIN login){
        return ResponseFormat.ok(oauthService.login(login));
    }

    @ApiOperation("네이버 로그인")
    @PostMapping("/naver/login")
    public ResponseFormat<OauthDto.TOKEN_READ> loginNAVER(@RequestBody OauthDto.LOGIN login){
        return ResponseFormat.ok(oauthService.login(login));
    }

    @ApiOperation("구글 로그인")
    @PostMapping("/google/login")
    public ResponseFormat<OauthDto.TOKEN_READ> signUpGOOGLE(@RequestBody OauthDto.LOGIN login){
        return ResponseFormat.ok(oauthService.login(login));
    }
}
