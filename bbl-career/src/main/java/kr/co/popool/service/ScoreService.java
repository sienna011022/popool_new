package kr.co.popool.service;

import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.entity.ScoreEntity;

public interface ScoreService {
    ScoreDto.SCOREINFO createScore(Long careerId, ScoreDto.SCOREINFO newScore);
}
