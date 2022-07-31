package kr.co.popool.bblmember.domain.info;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GoogleInfo {

    private String googleUrlLogin = "test.google.url.login";

    @Value("${test.google.client-id}")
    String googleClientId;

    @Value("${test.google.client-secret}")
    String googleClientSecret;

    @Value("${test.google.redirect}")
    String googleRedirect;

    @Value("${test.google.url.token}")
    private String googleUrlToken;

    @Value("${test.google.url.profile}")
    private String googleUrlProfile;
}
