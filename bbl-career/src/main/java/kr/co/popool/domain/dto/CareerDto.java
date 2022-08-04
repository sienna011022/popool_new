package kr.co.popool.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;


public class CareerDto {

  @Builder
  @ToString
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CAREERINFO {

    @ApiModelProperty(example = "member아이디")
    @NotBlank(message = "평가를 원하는 member 아이디를 입력하세요")
    private String memberIdentity;

    @ApiModelProperty(example = "평가등급")
    private String grade;

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
  @ToString
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
  @ToString
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


}