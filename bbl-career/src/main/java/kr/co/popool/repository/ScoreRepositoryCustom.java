package kr.co.popool.repository;

import java.util.List;
import java.util.Optional;
import kr.co.popool.domain.dto.queryDto.ScoreQueryDto.SHOWSCORE;


public interface ScoreRepositoryCustom {

  Optional<List<SHOWSCORE>> showAllScores(String memberIdentity);
}
