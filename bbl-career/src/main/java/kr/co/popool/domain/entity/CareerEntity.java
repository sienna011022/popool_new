package kr.co.popool.domain.entity;
import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_career")
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

    @Column(name = "history_id", nullable = true, length = 100)
    private String historyId;

    @Builder
    public CareerEntity(String identity,String grade, String name, String period, String historyId) {
        this.identity = identity;
        this.grade = grade;
        this.name = name;
        this.period = period;
        this.historyId = historyId;

    }


}
