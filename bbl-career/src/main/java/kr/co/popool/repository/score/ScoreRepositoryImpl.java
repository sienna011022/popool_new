package kr.co.popool.repository.score;

import static kr.co.popool.domain.entity.QCareerEntity.careerEntity;
import static kr.co.popool.domain.entity.QScoreEntity.scoreEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import kr.co.popool.domain.dto.score.QQueryScoreDto_SHOWSCORE;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE.DELETE;
import kr.co.popool.domain.entity.QCareerEntity;
import kr.co.popool.domain.entity.QScoreEntity;
import kr.co.popool.domain.entity.ScoreEntity;
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
            new QQueryScoreDto_SHOWSCORE(
                qScoreEntity.attendance,
                qScoreEntity.sincerity,
                qScoreEntity.positiveness,
                qScoreEntity.technical,
                qScoreEntity.cooperative,
                qScoreEntity.evaluatorIdentity
            ))
        )
        .from(qScoreEntity)
        .where(qScoreEntity.del_yn.eq("N"))
        .join(qScoreEntity.careerEntity, qCareerEntity)
        .where(qCareerEntity.memberIdentity.eq(memberIdentity))
        .limit(1000)
        .fetch());
  }

  public Optional<List<ScoreEntity>> getAllScoreList(String memberIdentity) {

    return Optional.ofNullable(query
        .select(qScoreEntity
        )
        .from(qScoreEntity)
        .where(qScoreEntity.del_yn.eq("N"))
        .join(qScoreEntity.careerEntity, qCareerEntity)
        .where(qCareerEntity.memberIdentity.eq(memberIdentity))
        .limit(1000)
        .fetch());

  }

  public Optional<ScoreEntity> getScoreEntity(DELETE deleteDto) {

    return Optional.ofNullable(query
        .select(qScoreEntity)
        .from(qScoreEntity)
        .join(qScoreEntity.careerEntity, qCareerEntity)
        .where(qScoreEntity.evaluatorIdentity.eq(deleteDto.getEvaluatorIdentity()))
        .where(qCareerEntity.memberIdentity.eq(deleteDto.getMemberIdentity()))
        .fetchOne());

  }
}


