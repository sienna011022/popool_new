package kr.co.popool.service;

import java.util.Optional;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.dto.queryDto.ScoreQueryDto.SHOWSCORE;

import java.util.List;

public interface ScoreService {

  Optional<List<SHOWSCORE>> showScores(String memberIdentity);

  void createScore(ScoreDto.SCOREINFO newScore);

  void updateScore(ScoreDto.UPDATE updateScoreDto);
}
