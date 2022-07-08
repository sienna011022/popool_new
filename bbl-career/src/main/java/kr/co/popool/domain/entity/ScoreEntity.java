package kr.co.popool.domain.entity;

import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_score")
@Getter
@Entity
@ToString
@Setter
public class ScoreEntity extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "career_identity")
    private CareerEntity careerIdentity;

    @Column(name = "evaluator_id", nullable = true, length = 100)
    private String evaluatorId;

    @Column(name = "attendance", nullable = true, length = 100)
    private int attendance;

    @Column(name = "sincerity", nullable = true, length = 100)
    private int sincerity;

    @Column(name = "positiveness", nullable = true, length = 100)
    private int positiveness;

    @Column(name = "technical", nullable = true, length = 100)
    private int technical;

    @Column(name = "cooperative", nullable = true, length = 100)
    private int cooperative;


    @Builder
    public ScoreEntity(CareerEntity careerIdentity, String evaluatorId, int attendance, int sincerity, int positiveness, int technical, int cooperative) {
        this.careerIdentity = careerIdentity;
        this.evaluatorId = evaluatorId;
        this.attendance = attendance;
        this.sincerity = sincerity;
        this.positiveness = positiveness;
        this.technical = technical;
        this.cooperative = cooperative;
    }


}
