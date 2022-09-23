package kr.co.popool.bblmember.domain.shared;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblmember.domain.info.GoogleInfo;
import kr.co.popool.bblmember.domain.info.KakaoInfo;
import kr.co.popool.bblmember.domain.info.NaverInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

//@Component
@RequiredArgsConstructor
public class OauthRequestFactory {

    private final KakaoInfo kakaoInfo;
    private final GoogleInfo googleInfo;
    private final NaverInfo naverInfo;

    public OauthRequest getRequest(String code, String provider) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        if (provider.equals("kakao")) {
            map.add("grant_type", "authorization_code");
            map.add("client_id", kakaoInfo.getKakaoClientId());
            map.add("redirect_uri", kakaoInfo.getKakaoRedirect());
            map.add("code", code);
            return OauthRequest.builder()
                    .url(kakaoInfo.getKakaoUrlToken())
                    .map(map)
                    .build();

        } else if (provider.equals("google")) {
            map.add("grant_type", "authorization_code");
            map.add("client_id", googleInfo.getGoogleClientId());
            map.add("client_secret", googleInfo.getGoogleClientSecret());
            map.add("redirect_uri", googleInfo.getGoogleRedirect());
            map.add("code", code);
            return OauthRequest.builder()
                    .url(googleInfo.getGoogleUrlToken())
                    .map(map)
                    .build();

        } else if (provider.equals("naver")) {
            map.add("grant_type", "authorization_code");
            map.add("client_id", naverInfo.getNaverClientId());
            map.add("client_secret", naverInfo.getNaverClientSecret());
            map.add("redirect_uri", naverInfo.getNaverRedirect());
            map.add("code", code);
            return OauthRequest.builder()
                    .url(naverInfo.getNaverUrlToken())
                    .map(map)
                    .build();

        } else {
            throw new BadRequestException("해당 소셜 로그인은 없습니다.");
        }
    }

    public String getUrlProfile(String provider){
        if(provider.equals("kakao")){
            return kakaoInfo.getKakaoUrlProfile();

        } else if(provider.equals("google")){
            return googleInfo.getGoogleUrlProfile();

        } else if(provider.equals("naver")){
            return naverInfo.getNaverUrlProfile();
        } else{
            throw new BadRequestException("해당 소셜 로그인은 없습니다.");
        }
    }
}
