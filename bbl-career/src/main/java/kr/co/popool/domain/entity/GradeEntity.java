package kr.co.popool.domain.entity;

import kr.co.popool.domain.shared.BaseEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.aop.scope.ScopedProxyFactoryBean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_grade")
@Getter
@ToString
@Entity
@AttributeOverride(name = "id", column = @Column(name = "career_id"))

public class GradeEntity extends BaseEntity {

    @OneToMany
    @JoinColumn(name = "score")
    private List<ScoreEntity> scores = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "career_id")
    private CareerEntity careerEntity;

    @Column(name = "average", nullable = false, length = 100)
    private float average;

    @Column(name = "grade")
    @Enumerated(value = EnumType.STRING)
    private ScoreGrade grade;

    @Column(name = "total_member")
    private Long total;



}
