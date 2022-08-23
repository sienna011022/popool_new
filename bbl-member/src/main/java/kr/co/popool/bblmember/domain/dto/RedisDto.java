package kr.co.popool.bblmember.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

public class RedisDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class REFRESH_TOKEN{
        private String identity;
        private String refreshToken;
        private Date expired;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class RE_TOKEN{
        private String accessToken;
        private String refreshToken;
    }
}
