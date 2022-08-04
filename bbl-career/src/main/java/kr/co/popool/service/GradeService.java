package kr.co.popool.service;

import kr.co.popool.domain.dto.GradeDto;
import kr.co.popool.domain.dto.ScoreDto;

public interface GradeService {

  void updateGrade(ScoreDto.SCOREINFO newScore);

  GradeDto.GRADEINFO showGradeInfo(String memberIdentity);

  GradeDto.ONLYGRADE showGrade(String memberIdentity);

}
