package kr.co.popool.repository.grade;

import static kr.co.popool.domain.entity.QCareerEntity.careerEntity;
import static kr.co.popool.domain.entity.QGradeEntity.*;
import static kr.co.popool.domain.entity.QScoreEntity.scoreEntity;


import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;

import kr.co.popool.domain.dto.grade.QQueryGradeDto_GETVALUE;
import kr.co.popool.domain.dto.grade.QQueryGradeDto_GRADEDETAIL;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GETVALUE;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.entity.QCareerEntity;
import kr.co.popool.domain.entity.QScoreEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class GradeRepositoryImpl implements GradeRepositoryCustom {

  private final JPAQueryFactory query;
  private final QCareerEntity qCareerEntity = careerEntity;
  private final QScoreEntity qScoreEntity = scoreEntity;

  @Override
  public GETVALUE getValue(String memberIdentity) {

    return query
        .select(
            new QQueryGradeDto_GETVALUE(
                qScoreEntity.attendance.avg(),
                qScoreEntity.sincerity.avg(),
                qScoreEntity.positiveness.avg(),
                qScoreEntity.cooperative.avg(),
                qScoreEntity.technical.avg(),
                qScoreEntity.count()
            ))
        .from(qScoreEntity)
        .where(qScoreEntity.careerEntity.memberIdentity.eq(memberIdentity))
        .fetchOne();
  }

  @Override
  public Optional<GRADEDETAIL> makeGradeDto(String memberIdentity, GETVALUE valueDto) {

    return Optional.of(new GRADEDETAIL(
        valueDto.getAttendanceAvg(),
        valueDto.getSincerityAvg(),
        valueDto.getPositivenessAvg(),
        valueDto.getCooperativeAvg(),
        valueDto.getTechnicalAvg(),
        getTotalAvg(valueDto),
        getFinalGrade(getTotalAvg(valueDto)),
        valueDto.getTotalMember()));

  }


  public double getTotalAvg(GETVALUE valueDto) {

    return valueDto.getAttendanceAvg() + valueDto.getCooperativeAvg()
        + valueDto.getPositivenessAvg()
        + valueDto.getSincerityAvg() + valueDto.getTechnicalAvg();

  }

  public ScoreGrade getFinalGrade(double totalAvg) {
    ScoreGrade finalGrade;

    if (20 < totalAvg) {
      finalGrade = ScoreGrade.GOLD;

    } else if (16 < totalAvg) {
      finalGrade = ScoreGrade.SILVER;

    } else if (10 < totalAvg) {
      finalGrade = ScoreGrade.BRONZE;

    } else {
      finalGrade = ScoreGrade.BLACK;
    }
    return finalGrade;

  }

  @Override
  public Optional<GRADEDETAIL> showGradeDetail(String memberIdentity) {

    return Optional.ofNullable(query
        .select((
            new QQueryGradeDto_GRADEDETAIL(
                qCareerEntity.gradeEntity.attendanceAvg,
                qCareerEntity.gradeEntity.sincerityAvg,
                qCareerEntity.gradeEntity.positivenessAvg,
                qCareerEntity.gradeEntity.cooperativeAvg,
                qCareerEntity.gradeEntity.technicalAvg,
                qCareerEntity.gradeEntity.attendanceAvg,
                qCareerEntity.gradeEntity.grade,
                qCareerEntity.gradeEntity.totalMember
            ))
        )
        .from(qCareerEntity)
        .where(qCareerEntity.memberIdentity.eq(memberIdentity))
        .fetchOne());
  }

}