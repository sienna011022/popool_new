package kr.co.popool.domain.entity;

import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.shared.BaseEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_grade")
@Getter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "grade_id"))

public class GradeEntity extends BaseEntity {


  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade_id", nullable = true)
  private List<ScoreEntity> scores = new ArrayList<>();

  @Column(name = "attendance_avg", nullable = false)
  private double attendanceAvg;

  @Column(name = "sincerity_avg", nullable = false)
  private double sincerityAvg;

  @Column(name = "positiveness_avg", nullable = false)
  private double positivenessAvg;

  @Column(name = "cooperative_avg", nullable = false)
  private double cooperativeAvg;

  @Column(name = "technical_avg", nullable = false)
  private double technicalAvg;

  @Column(name = "totalAvg", nullable = false)
  private double totalAvg;

  @Column(name = "grade", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private ScoreGrade grade;

  @Column(name = "total_member", nullable = false)
  private Long totalMember;

  @Builder
  public GradeEntity(List<ScoreEntity> scores, double attendanceAvg,
      double sincerityAvg,
      double positivenessAvg, double cooperativeAvg, double technicalAvg, double totalAvg,
      ScoreGrade grade, Long totalMember) {
    this.scores = scores;
    this.attendanceAvg = attendanceAvg;
    this.sincerityAvg = sincerityAvg;
    this.positivenessAvg = positivenessAvg;
    this.cooperativeAvg = cooperativeAvg;
    this.technicalAvg = technicalAvg;
    this.totalAvg = totalAvg;
    this.grade = grade;
    this.totalMember = totalMember;
  }

  public static GradeEntity of(List<ScoreEntity> scoreList, GRADEDETAIL gradedetail) {

    GradeEntity gradeEntity = GradeEntity.builder()
        .scores(scoreList)
        .attendanceAvg(gradedetail.getAttendanceAvg())
        .sincerityAvg(gradedetail.getSincerityAvg())
        .positivenessAvg(gradedetail.getPositivenessAvg())
        .cooperativeAvg(gradedetail.getCooperativeAvg())
        .technicalAvg(gradedetail.getTechnicalAvg())
        .totalAvg(gradedetail.getTotalAvg())
        .grade(gradedetail.getGrade())
        .totalMember(gradedetail.getTotalMember())
        .build();

    return gradeEntity;
  }

  public void updateGrade(List<ScoreEntity> scoreList, GRADEDETAIL gradeDto) {

    this.scores = scoreList;
    this.attendanceAvg = gradeDto.getAttendanceAvg();
    this.sincerityAvg = gradeDto.getSincerityAvg();
    this.positivenessAvg = gradeDto.getPositivenessAvg();
    this.cooperativeAvg = gradeDto.getCooperativeAvg();
    this.technicalAvg = gradeDto.getTechnicalAvg();
    this.totalAvg = gradeDto.getTotalAvg();
    this.grade = gradeDto.getGrade();
    this.totalMember = gradeDto.getTotalMember();

  }

}


