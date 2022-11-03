package kr.co.popool.service.career;

import kr.co.popool.domain.dto.career.CareerDto;

import java.util.List;
import kr.co.popool.domain.dto.career.CareerDto.CAREERINFO;
import kr.co.popool.domain.dto.career.CareerDto.DELETE;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.domain.entity.ScoreEntity;

public interface CareerService {

  List<CAREERINFO> showAll();

  CAREERINFO show(String memberIdentity);

  void newCareer(CareerDto.CREATE newCareer);

  void update(CareerDto.UPDATE careerDto);

  CareerEntity findCareerEntity(String memberIdentity);

  CAREERINFO checkGrade(CareerEntity careerEntity);

  void saveGrade(String memberIdentity, GradeEntity gradeEntity);

  void updateGrade(String memberIdentity, GRADEDETAIL gradeDto);

  List<ScoreEntity> findScoreList(String memberIdentity);

  void delete(DELETE careerDto);

  boolean checkDelete(CareerEntity careerEntity);
}

