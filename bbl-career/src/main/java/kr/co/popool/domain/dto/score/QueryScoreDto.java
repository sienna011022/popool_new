package kr.co.popool.domain.dto.score;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

public class QueryScoreDto {

  @Getter
  public static class SHOWSCORE {

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

    @ApiModelProperty(example = "협업")
    @NotBlank(message = "협업능력 점수를 입력하세요")
    private int cooperative;

    @JsonProperty("evaluator_identity")
    @ApiModelProperty(example = "인사")
    @NotBlank(message = "본인 아이디를 입력하세요요")
    private String evaluatorIdentity;

    @QueryProjection
    public SHOWSCORE(int attendance, int sincerity, int positiveness, int technical,
        int cooperative,
        String evaluatorIdentity) {
      this.attendance = attendance;
      this.sincerity = sincerity;
      this.positiveness = positiveness;
      this.technical = technical;
      this.cooperative = cooperative;
      this.evaluatorIdentity = evaluatorIdentity;
    }


    @Getter
    public static class DELETE {


      @JsonProperty("evaluator_identity")
      @ApiModelProperty(example = "인사")
      @NotBlank(message = "본인 아이디를 입력하세요요")
      private String evaluatorIdentity;


      @JsonProperty("member_identity")
      @ApiModelProperty(example = "인사")
      @NotBlank(message = "평가를 원하는 인사 아이디를 입력하세요")
      private String memberIdentity;

      @QueryProjection
      public DELETE(String evaluatorIdentity, String memberIdentity) {
        this.evaluatorIdentity = evaluatorIdentity;
        this.memberIdentity = memberIdentity;
      }

    }
  }
}