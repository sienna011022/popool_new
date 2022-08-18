package kr.co.popool.repository;


import static kr.co.popool.domain.entity.QCareerEntity.careerEntity;
import static kr.co.popool.domain.entity.QScoreEntity.scoreEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import kr.co.popool.domain.dto.queryDto.QScoreQueryDto_SHOWSCORE;
import kr.co.popool.domain.dto.queryDto.ScoreQueryDto.SHOWSCORE;
import kr.co.popool.domain.entity.QCareerEntity;
import kr.co.popool.domain.entity.QScoreEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepositoryCustom {


  private final JPAQueryFactory query;

  private final QCareerEntity qCareerEntity = careerEntity;

  private final QScoreEntity qScoreEntity = scoreEntity;

  @Override
  public Optional<List<SHOWSCORE>> showAllScores(String memberIdentity) {
    return Optional.ofNullable(query
        .select((
                new QScoreQueryDto_SHOWSCORE(
                    qScoreEntity.attendance,
                    qScoreEntity.sincerity,
                    qScoreEntity.positiveness,
                    qScoreEntity.technical,
                    qScoreEntity.cooperative,
                    qScoreEntity.evaluatorIdentity
                )
            )
        )
        .from(qScoreEntity)
        .join(qScoreEntity.careerEntity, qCareerEntity)
        .where(qCareerEntity.memberIdentity.eq(memberIdentity))
        .limit(1000)
        .fetch());
  }
}