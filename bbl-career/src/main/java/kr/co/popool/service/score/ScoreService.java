package kr.co.popool.service.score;

import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE;
import kr.co.popool.domain.dto.score.ScoreDto;


import java.util.List;
import kr.co.popool.domain.entity.ScoreEntity;

public interface ScoreService {

  List<SHOWSCORE> showScores(String memberIdentity);

  void createScore(ScoreDto.SCOREINFO newScore);

  void updateScore(ScoreDto.UPDATE updateScoreDto);

  List<ScoreEntity> findAllScore(String memberIdentity);
}