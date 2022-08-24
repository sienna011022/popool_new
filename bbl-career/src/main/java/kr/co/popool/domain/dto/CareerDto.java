package kr.co.popool.domain.dto.career;

import io.swagger.annotations.ApiModelProperty;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.*;
import javax.validation.constraints.NotBlank;


public class CareerDto {

  public static final ScoreGrade DEFAULT_GRADE = ScoreGrade.WHITE;

  @Builder
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CAREERINFO {

    @ApiModelProperty(example = "인사 아이디")
    @NotBlank(message = "평가를 원하는 member 아이디를 입력하세요")
    private String memberIdentity;

    @ApiModelProperty(example = "평가등급")
    private ScoreGrade grade;

    @ApiModelProperty(example = "이름")
    @NotBlank(message = "이름를 입력해주세요.")
    private String name;

    @ApiModelProperty(example = "재직 기간")
    @NotBlank(message = "재직 기간을 입력해주세요")
    private String period;

    @ApiModelProperty(example = "간단한 자기 소개")
    @NotBlank(message = "간단한 자기 소개를 입력해주세요")
    private String context;

    @ApiModelProperty(example = "블록체인 아이디")
    @NotBlank(message = "블록체인")
    private String historyId;

  }

  @Builder
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CREATE {

    @ApiModelProperty(example = "member 아이디")
    @NotBlank(message = "본인의 아이디를 입력하세요")
    private String memberIdentity;

    @ApiModelProperty(example = "이름")
    @NotBlank(message = "이름를 입력해주세요.")
    private String name;

    @ApiModelProperty(example = "재직 기간")
    @NotBlank(message = "재직 기간을 입력해주세요")
    private String period;

    @ApiModelProperty(example = "간단한 자기 소개")
    @NotBlank(message = "간단한 자기 소개를 입력해주세요")
    private String context;

    @ApiModelProperty(example = "블록체인 아이디")
    @NotBlank(message = "블록체인")
    private String historyId;

  }

  @Builder
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UPDATE {

    @ApiModelProperty(example = "member 아이디")
    @NotBlank(message = "본인의 아이디를 입력하세요")
    private String memberIdentity;

    @ApiModelProperty(example = "이름")
    @NotBlank(message = "이름를 입력해주세요.")
    private String name;

    @ApiModelProperty(example = "재직 기간")
    @NotBlank(message = "재직 기간을 입력해주세요")
    private String period;

    @ApiModelProperty(example = "간단한 자기 소개")
    @NotBlank(message = "간단한 자기 소개를 입력해주세요")
    private String context;

    @ApiModelProperty(example = "블록체인 아이디")
    @NotBlank(message = "블록체인")
    private String historyId;

  }

  public static CAREERINFO of(CareerEntity careerEntity) {
    return CAREERINFO.builder()
        .memberIdentity(careerEntity.getMemberIdentity())
        .name(careerEntity.getName())
        .grade(careerEntity.getGradeEntity().getGrade())
        .period(careerEntity.getPeriod())
        .context(careerEntity.getContext())
        .historyId(careerEntity.getHistoryId())
        .build();

  }
  public static CAREERINFO NoneGradeDto(CareerEntity careerEntity) {
    return CAREERINFO.builder()
        .memberIdentity(careerEntity.getMemberIdentity())
        .name(careerEntity.getName())
        .grade(DEFAULT_GRADE)
        .period(careerEntity.getPeriod())
        .context(careerEntity.getContext())
        .historyId(careerEntity.getHistoryId())
        .build();

  }
}
