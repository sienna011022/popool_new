package kr.co.popool.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;


public  class CareerDto {
    @Builder
    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CAREERINFO {

        @ApiModelProperty(example = "평가등급")
        @Enumerated(EnumType.STRING)
        private ScoreGrade grade;

        @ApiModelProperty(example = "이름")
        @NotBlank(message = "이름를 입력해주세요.")
        private String name;

        @ApiModelProperty(example = "재직 기간")
        @NotBlank(message = "재직 기간을 입력해주세요")
        private String period;

        @ApiModelProperty(example = "블록체인 아이디")
        @NotBlank(message = "블록체인")
        private String historyId;



    }


}