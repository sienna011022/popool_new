package kr.co.popool.repository.score;

import java.util.List;
import java.util.Optional;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE.DELETE;
import kr.co.popool.domain.entity.ScoreEntity;

public interface ScoreRepositoryCustom {

  Optional<List<SHOWSCORE>> showAllScores(String memberIdentity);

  Optional<List<ScoreEntity>> getAllScoreList(String memberIdentity);

  Optional<ScoreEntity> getScoreEntity(DELETE deleteDto);
}
