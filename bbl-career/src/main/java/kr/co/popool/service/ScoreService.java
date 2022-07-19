package kr.co.popool.service;

import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.entity.ScoreEntity;

import java.util.List;

public interface ScoreService {

    List<ScoreDto.SCOREINFO> showScores(Long id);
    void createScore(Long careerId, ScoreDto.SCOREINFO newScore);

    void updateScore(Long scoreId, ScoreDto.UPDATE updateScoreDto);
}
