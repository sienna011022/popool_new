package kr.co.popool.service;

import kr.co.popool.domain.dto.GradeDto;
import kr.co.popool.domain.dto.ScoreDto;

public interface GradeService {

    void updateGrade(ScoreDto.SCOREINFO newScore);
}
