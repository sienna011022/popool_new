package kr.co.popool.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


//TODO : 지은아 이거 가독성 좋게 바꿨어~
@Getter
@AllArgsConstructor
@Builder
public class FilterConfig {

    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
}
