package kr.co.popool.gateway.filters;

import lombok.Getter;

@Getter
public class Config {
    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;

    public Config(String baseMessage,
                  boolean preLogger,
                  boolean postLogger) {
        this.baseMessage = baseMessage;
        this.preLogger = preLogger;
        this.postLogger = postLogger;
    }
}
