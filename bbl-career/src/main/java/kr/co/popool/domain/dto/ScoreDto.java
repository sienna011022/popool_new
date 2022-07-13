package kr.co.popool.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

public class ScoreDto {

    @ToString
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor

    public static class SCOREINFO {

        @JsonProperty("identity")
        @ApiModelProperty(example = "인사")
        @NotBlank(message = "평가를 원하는 인사 아이디를 입력하세요")
        private String identity;

        @JsonProperty("evaluator_id")
        @ApiModelProperty(example = "인사")
        @NotBlank(message = "본인 아이디를 입력하세요요")
        private String evaluatorId;

        @ApiModelProperty(example = "근태")
        @NotBlank(message = "근태점수를 입력하세요")
        private int attendance;

        @ApiModelProperty(example = "성실성")
        @NotBlank(message = "성실성 점수를 입력하세요")
        private int sincerity;

        @ApiModelProperty(example = "적극성")
        @NotBlank(message = "적극성 점수를 입력하세요")
        private int positiveness;

        @ApiModelProperty(example = "기술스킬")
        @NotBlank(message = "기술 점수를 입력하세요")
        private int technical;

        @ApiModelProperty(example = "협업 능력")
        @NotBlank(message = "협업능력 점수를 입력하세요")
        private int cooperative;
    }

}



