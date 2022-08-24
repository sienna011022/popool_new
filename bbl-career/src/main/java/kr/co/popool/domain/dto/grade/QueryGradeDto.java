package kr.co.popool.domain.dto.grade;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.Data;
import lombok.Getter;


@Data
public class QueryGradeDto {

  @Getter
  public static class GETVALUE {

    @ApiModelProperty(example = "근태 평균")
    private double attendanceAvg;

    @ApiModelProperty(example = "성실도 평균")
    private double sincerityAvg;

    @ApiModelProperty(example = "적극성 평균")
    private double positivenessAvg;

    @ApiModelProperty(example = "협동력 평균")
    private double cooperativeAvg;

    @ApiModelProperty(example = "기술력 평균")
    private double technicalAvg;

    @ApiModelProperty(example = "총 평가 인원")
    private Long totalMember;

    @QueryProjection
    public GETVALUE(double attendanceAvg, double sincerityAvg, double positivenessAvg,
        double cooperativeAvg, double technicalAvg, Long totalMember) {
      this.attendanceAvg = attendanceAvg;
      this.sincerityAvg = sincerityAvg;
      this.positivenessAvg = positivenessAvg;
      this.cooperativeAvg = cooperativeAvg;
      this.technicalAvg = technicalAvg;
      this.totalMember = totalMember;
    }

  }


  @Getter
  public static class GRADEDETAIL {

    @ApiModelProperty(example = "근태 평균")
    private double attendanceAvg;

    @ApiModelProperty(example = "성실도 평균")
    private double sincerityAvg;

    @ApiModelProperty(example = "적극성 평균")
    private double positivenessAvg;

    @ApiModelProperty(example = "협동력 평균")
    private double cooperativeAvg;

    @ApiModelProperty(example = "기술력 평균")
    private double technicalAvg;

    @ApiModelProperty(example = "총 평가 인원")
    private Long totalMember;

    @ApiModelProperty(example = "총 평가 인원")
    private double totalAvg;

    @ApiModelProperty(example =  "최종 등급")
    private ScoreGrade grade;

    @QueryProjection
    public GRADEDETAIL(double attendanceAvg, double sincerityAvg, double positivenessAvg,
        double cooperativeAvg, double technicalAvg, double totalAvg, ScoreGrade grade,
        Long totalMember) {
      this.attendanceAvg = attendanceAvg;
      this.sincerityAvg = sincerityAvg;
      this.positivenessAvg = positivenessAvg;
      this.cooperativeAvg = cooperativeAvg;
      this.technicalAvg = technicalAvg;
      this.totalAvg = totalAvg;
      this.grade = grade;
      this.totalMember = totalMember;
    }

  }
}



