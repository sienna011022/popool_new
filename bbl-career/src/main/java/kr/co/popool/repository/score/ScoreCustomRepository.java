package kr.co.popool.repository.score;

import kr.co.popool.domain.dto.score.ScoreAverage;
import kr.co.popool.domain.dto.score.ScoreResponse;

import java.util.List;

public interface ScoreCustomRepository {
    List<ScoreResponse> findAllScores(String targetId);

    ScoreAverage getAverage(String memberId);
}
