package kr.co.popool.service.score;

import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE.DELETE;
import kr.co.popool.domain.dto.score.ScoreCreateRequest;
import kr.co.popool.domain.dto.score.ScoreDto;


import java.util.List;

import kr.co.popool.domain.dto.score.ScoreResponses;
import kr.co.popool.domain.entity.Score;
import org.springframework.transaction.annotation.Transactional;

public interface ScoreService {
  @Transactional
  Score newScore(ScoreCreateRequest request);

  @Transactional(readOnly = true)
  List<Score> showScoreAllByEvaluator(String evaluatorId);

  @Transactional(readOnly = true)
  ScoreResponses showMyAllScore(String targetId);
}
