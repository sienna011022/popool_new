package kr.co.popool.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.*;

import java.util.List;

public class GradeDto {


@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
    public static class GRADEINFO {

    @ApiModelProperty(example = "평가들의 평균")
    private float average;

    @ApiModelProperty(example = "평가 등급")
    private ScoreGrade grade;

    @ApiModelProperty(example = "평가 인원")
    private int totalMember;

    @ApiModelProperty(example = "평가 합계")
    private int totalScore;




    }

    @Builder
    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATEGRADE {


        @ApiModelProperty(example = "평가들의 평균")
        private float average;

        @ApiModelProperty(example = "평가 등급")
        private ScoreGrade grade;

        @ApiModelProperty(example = "평가 인원")
        private int totalMember;
        
        @ApiModelProperty(example = "평가 합계")
        private int totalScore;





    }

    @Builder
    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ONLYGRADE {

        @ApiModelProperty(example = "평가 등급")
        private ScoreGrade grade;

    }


}
