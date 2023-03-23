package kr.co.popool.domain.entity;

import kr.co.popool.domain.dto.score.ScoreDto;
import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.util.Assert.hasText;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_score")
public class Score extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
  @JoinColumn(name = "member_id")
  private Career career;

  @Column(nullable = false)
  private String evaluatorId;

  private int attendance;

  private int sincerity;

  private int positiveness;

  private int technical;

  private int cooperative;

  @Builder
  public Score(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Career career, String evaluatorId, int attendance, int sincerity, int positiveness, int technical, int cooperative) {
    super(id, createdAt, updatedAt);
    hasText(evaluatorId, "평가자 아이디를 입력하세요");
    this.career = career;
    this.evaluatorId = evaluatorId;
    this.attendance = attendance;
    this.sincerity = sincerity;
    this.positiveness = positiveness;
    this.technical = technical;
    this.cooperative = cooperative;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Score score = (Score) o;
    return attendance == score.attendance && sincerity == score.sincerity && positiveness == score.positiveness && technical == score.technical && cooperative == score.cooperative && Objects.equals(career, score.career) && Objects.equals(evaluatorId, score.evaluatorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(career, evaluatorId, attendance, sincerity, positiveness, technical, cooperative);
  }
}
