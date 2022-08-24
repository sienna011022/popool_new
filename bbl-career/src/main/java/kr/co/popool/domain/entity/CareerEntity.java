package kr.co.popool.domain.entity;

import kr.co.popool.domain.dto.career.CareerDto;
import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"member_identity", "history_id"})}, name = "tbl_career")
@Getter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "career_id"))

public class CareerEntity extends BaseEntity {

  @Column(name = "member_identity", nullable = false, length = 100)
  private String memberIdentity;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade")
  private GradeEntity gradeEntity;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "period", nullable = false, length = 100)
  private String period;

  @Column(name = "context", nullable = true, length = 500)
  private String context;

  @Column(name = "history_id", nullable = true, length = 100)
  private String historyId;

  @Builder
  public CareerEntity(String memberIdentity, GradeEntity gradeEntity, String name, String period,
      String context, String historyId) {
    this.memberIdentity = memberIdentity;
    this.gradeEntity = gradeEntity;
    this.name = name;
    this.period = period;
    this.historyId = historyId;
    this.context = context;

  }

  public static CareerEntity of(CareerDto.CREATE newCareer) {
    return CareerEntity.builder()
        .memberIdentity(newCareer.getMemberIdentity())
        .name(newCareer.getName())
        .context(newCareer.getContext())
        .period(newCareer.getPeriod())
        .historyId(newCareer.getHistoryId())
        .build();
  }

  public void updateCareer(CareerDto.UPDATE careerUpdate) {
    this.name = careerUpdate.getName();
    this.period = careerUpdate.getPeriod();
    this.context = careerUpdate.getContext();
    this.historyId = careerUpdate.getHistoryId();
  }

  public void createGrade(GradeEntity gradeEntity) {
    this.gradeEntity = gradeEntity;
  }

}