package kr.co.popool.service;

import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.entity.ScoreEntity;

import java.util.List;

public interface ScoreService {

    List<ScoreDto.SCOREINFO> showScores(Long id);
    ScoreDto.SCOREINFO createScore(Long careerId, ScoreDto.SCOREINFO newScore);
}
