package kr.co.popool.bblmember.domain.info;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoInfo {

    private String kakaoUrlLogin = "test.kakao.url.login";

    @Value("${test.kakao.client-id}")
    private String kakaoClientId;

    @Value("${test.kakao.redirect}")
    private String kakaoRedirect;

    @Value("${test.kakao.url.token}")
    private String kakaoUrlToken;

    @Value("${test.kakao.url.profile}")
    private String kakaoUrlProfile;

}