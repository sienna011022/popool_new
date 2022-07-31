package kr.co.popool.bblmember.domain.info;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NaverInfo{

    private String naverUrlLogin = "test.naver.url.login";

    @Value("${test.naver.client-id}")
    private String naverClientId;

    @Value("${test.naver.client-secret}")
    private String naverClientSecret;

    @Value("${test.naver.redirect}")
    private String naverRedirect;

    @Value("${test.naver.url.token}")
    private String naverUrlToken;

    @Value("${test.naver.url.profile}")
    private String naverUrlProfile;

}