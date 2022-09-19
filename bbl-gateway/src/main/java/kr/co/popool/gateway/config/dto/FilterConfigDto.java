package kr.co.popool.gateway.config.dto;

import lombok.Data;

@Data
public class FilterConfigDto {
    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
}
