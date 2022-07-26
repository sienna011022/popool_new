package kr.co.popool.domain.entity;

import kr.co.popool.domain.dto.GradeDto;
import kr.co.popool.domain.shared.BaseEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
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
    private int total_member;

    @Column(name = "total_score")
    @ColumnDefault("0")
    private int total_score;

    @Builder
    public GradeEntity(List<ScoreEntity> scores, CareerEntity careerEntity, float average, ScoreGrade grade, int total_member,int total_score) {
        this.scores = scores;
        this.careerEntity = careerEntity;
        this.average = average;
        this.grade = grade;
        this.total_member = total_member;
        this.total_score = total_score;
    }
    public void updateGrade(GradeDto.UPDATEGRADE updateGradeDto){
        this.average = updateGradeDto.getAverage();
        this.grade = updateGradeDto.getGrade();
        this.total_member = updateGradeDto.getTotal_member();
        this.total_score = updateGradeDto.getTotal_score();

    }
}


