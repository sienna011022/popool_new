package kr.co.popool.bblmember.domain.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.util.LinkedMultiValueMap;

@Getter
@Builder
@AllArgsConstructor
public class OauthRequest {
    private String url;
    private LinkedMultiValueMap<String, String> map;
}
