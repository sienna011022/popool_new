package kr.co.popool.domain.entity;

import kr.co.popool.domain.shared.BaseEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
import kr.co.popool.domain.dto.GradeDto;
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

    @OneToMany
    @JoinColumn(name = "scores")
    private List<ScoreEntity> scores = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "career_id")
    private CareerEntity careerEntity;

    @Column(name = "average")
    @ColumnDefault("0")
    private float average;

    @Column(name = "grade")
    @Enumerated(value = EnumType.STRING)
    private ScoreGrade grade;

    @Column(name = "total_member")
    @ColumnDefault("0")
    private int totalMember;

    @Column(name = "total_score")
    @ColumnDefault("0")
    private int totalScore;

    @Builder
    public GradeEntity(List<ScoreEntity> scores, CareerEntity careerEntity, float average, ScoreGrade grade, int totalMember,int totalScore) {
        this.scores = scores;
        this.careerEntity = careerEntity;
        this.average = average;
        this.grade = grade;
        this.totalMember = totalMember;
        this.totalScore = totalScore;
    }
    public void updateGrade(GradeDto.UPDATEGRADE updateGradeDto){
        this.average = updateGradeDto.getAverage();
        this.grade = updateGradeDto.getGrade();
        this.totalMember = updateGradeDto.getTotalMember();
        this.totalScore = updateGradeDto.getTotalScore();

    }
}


