package kr.co.popool.service.grade;

import kr.co.popool.domain.dto.grade.GradeDto;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GETVALUE;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.dto.score.ScoreDto;
import kr.co.popool.domain.entity.GradeEntity;


public interface GradeService {

  GRADEDETAIL showGradeDetail(String memberIdentity);

  GradeDto.ONLYGRADE showGradeOnly(String memberIdentity);

  GETVALUE getValue(ScoreDto.SCOREINFO newScoreDto);

  GradeEntity createGradeEntity(String memberIdentity, GRADEDETAIL gradeDto);

  GRADEDETAIL createGradeDto(String memberIdentity,GETVALUE valueDto);


}
