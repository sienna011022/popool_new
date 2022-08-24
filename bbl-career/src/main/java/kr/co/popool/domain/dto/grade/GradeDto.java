package kr.co.popool.domain.dto.grade;

import io.swagger.annotations.ApiModelProperty;

import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.*;

import java.util.List;

public class GradeDto {
  @Builder
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ONLYGRADE {
    @ApiModelProperty(example = "평가 등급")
    private ScoreGrade grade;
  }
}
