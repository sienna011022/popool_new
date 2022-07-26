package kr.co.popool.service;

import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.entity.ScoreEntity;
import java.util.List;

public interface ScoreService {

    List<ScoreDto.SHOWSCORE> showScores(String memberIdentity);
    void createScore( ScoreDto.SCOREINFO newScore);

    void updateScore( ScoreDto.UPDATE updateScoreDto);

}
