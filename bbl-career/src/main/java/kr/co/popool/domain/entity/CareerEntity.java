package kr.co.popool.domain.entity;
import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "identity", "history_id" }) },name = "tbl_career")
@Getter
@ToString
@Entity
@AttributeOverride(name = "id", column = @Column(name = "career_id"))

public class CareerEntity extends BaseEntity {

    @Column(name = "identity", nullable = false, length = 100)
    private String identity;

    @Column(name = "grade", nullable = true, length = 100)
    private String grade;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "period", nullable = false, length = 100)
    private String period;

    @Column(name = "context", nullable = true, length = 1000)
    private String context;

    @Column(name = "history_id", nullable = true, length = 100)
    private String historyId;


    @Builder
    public CareerEntity(String identity,String grade, String name, String period, String context,String historyId) {
        this.identity = identity;
        this.grade = grade;
        this.name = name;
        this.period = period;
        this.historyId = historyId;
        this.context = context;

    }
    public void updateCareer(CareerDto.UPDATE careerUpdate){
        this.name = careerUpdate.getName();
        this.period = careerUpdate.getPeriod();
        this.context = careerUpdate.getContext();
        this.historyId = careerUpdate.getHistoryId();

    }

}
