package kr.co.popool.domain.entity;

import kr.co.popool.domain.dto.score.ScoreDto;
import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"evaluator_identity"})}, name = "tbl_score")
@AttributeOverride(name = "id", column = @Column(name = "score_id"))
public class ScoreEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "career_id")
  private Career careerEntity;

  @Column(name = "evaluator_identity", nullable = false, length = 100)
  private String evaluatorIdentity;

  @Column(name = "attendance", nullable = false, length = 100)
  private int attendance;

  @Column(name = "sincerity", nullable = false, length = 100)
  private int sincerity;

  @Column(name = "positiveness", nullable = false, length = 100)
  private int positiveness;

  @Column(name = "technical", nullable = false, length = 100)
  private int technical;

  @Column(name = "cooperative", nullable = false, length = 100)
  private int cooperative;

  @Builder
  public ScoreEntity(Career careerEntity, GradeEntity gradeEntity, String evaluatorIdentity,
      int attendance,
      int sincerity, int positiveness, int technical, int cooperative) {
    this.careerEntity = careerEntity;
    this.evaluatorIdentity = evaluatorIdentity;
    this.attendance = attendance;
    this.sincerity = sincerity;
    this.positiveness = positiveness;
    this.technical = technical;
    this.cooperative = cooperative;
  }

  public static ScoreEntity of(ScoreDto.SCOREINFO newScore, Career careerEntity) {
    ScoreEntity scoreEntity = ScoreEntity.builder()
        .careerEntity(careerEntity)
        .evaluatorIdentity(newScore.getEvaluatorIdentity())
        .attendance(newScore.getAttendance())
        .sincerity(newScore.getSincerity())
        .positiveness(newScore.getSincerity())
        .technical(newScore.getTechnical())
        .cooperative(newScore.getCooperative())
        .build();
    return scoreEntity;
  }

  public void updateScore(ScoreDto.UPDATE updateScoreDto) {
    this.attendance = updateScoreDto.getAttendance();
    this.sincerity = updateScoreDto.getSincerity();
    this.positiveness = updateScoreDto.getPositiveness();
    this.technical = updateScoreDto.getTechnical();
    this.cooperative = updateScoreDto.getCooperative();

  }
}
