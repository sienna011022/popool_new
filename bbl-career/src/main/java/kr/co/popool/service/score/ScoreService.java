package kr.co.popool.service.score;

import kr.co.popool.domain.dto.score.ScoreCreateRequest;
import kr.co.popool.domain.dto.score.ScoreResponses;
import kr.co.popool.domain.entity.Grade;
import kr.co.popool.domain.entity.Score;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoreService {
  @Transactional
  Score newScore(ScoreCreateRequest request);

  @Transactional(readOnly = true)
  List<Score> showScoreAllByEvaluator(String evaluatorId);

  @Transactional(readOnly = true)
  ScoreResponses showMyAllScore(String targetId);

    @Transactional(readOnly = true)
    Grade getGrade(String memberId);
}
