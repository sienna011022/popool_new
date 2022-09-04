package kr.co.popool.service.grade;

import kr.co.popool.domain.dto.grade.QueryGradeDto.GETVALUE;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE.DELETE;
import kr.co.popool.domain.dto.score.ScoreDto.SCOREINFO;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.service.career.CareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CalculateGradeService {

  //TODO:매핑관계 수정 후 oneToMany update 2번 생성되는 이슈 해결

  private final GradeService gradeService;
  private final CareerService careerService;


  /**
   * 평가 등록 시 등급 DTO 생성
   *
   * @param newScoreDto : 새로운 평가 DTO
   * @return GRADEDETAIL : 등급 DTO
   */

  public GRADEDETAIL calculateGradeDto(SCOREINFO newScoreDto) {

    GETVALUE valueDto = gradeService.getValue(newScoreDto.getMemberIdentity());
    return gradeService.createGradeDto(newScoreDto.getMemberIdentity(),
        valueDto);

  }

  public GRADEDETAIL updateGradeDto(DELETE deleteDto){

    GETVALUE valueDto = gradeService.getValue(deleteDto.getMemberIdentity());
    return gradeService.createGradeDto(deleteDto.getMemberIdentity(),valueDto);
  }

  /**
   * 등급 DTO를 각 객체들과 매핑
   *
   * @param gradeDto,memberIdentity : 등급 DTO ,인사 아이디
   */
  public void saveGradeEntity(GRADEDETAIL gradeDto, String memberIdentity) {

    try {
      careerService.updateGrade(memberIdentity, gradeDto);
    } catch (NullPointerException e) {
      GradeEntity gradeEntity = gradeService.createGradeEntity(memberIdentity, gradeDto);
      careerService.saveGrade(memberIdentity, gradeEntity);
    }
  }

}


