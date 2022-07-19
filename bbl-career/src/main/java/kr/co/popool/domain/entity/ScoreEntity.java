package kr.co.popool.domain.entity;

import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_score")
@Getter
@Entity
@ToString
@AttributeOverride(name = "id", column = @Column(name = "score_id"))
public class ScoreEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "career_id")
    private CareerEntity careerEntity;

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
    public ScoreEntity(CareerEntity careerEntity, String evaluatorIdentity, int attendance, int sincerity, int positiveness, int technical, int cooperative) {
        this.careerEntity = careerEntity;
        this.evaluatorIdentity = evaluatorIdentity;
        this.attendance = attendance;
        this.sincerity = sincerity;
        this.positiveness = positiveness;
        this.technical = technical;
        this.cooperative = cooperative;
    }

    public void updateScore(ScoreDto.UPDATE updateScoreDto){
        this.attendance = updateScoreDto.getAttendance();
        this.sincerity = updateScoreDto.getSincerity();
        this.positiveness = updateScoreDto.getPositiveness();
        this.technical = updateScoreDto.getTechnical();
        this.cooperative = updateScoreDto.getCooperative();

    }
}
