package kr.co.popool.service.grade;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.domain.dto.grade.GradeDto;
import kr.co.popool.domain.dto.grade.GradeDto.ONLYGRADE;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GETVALUE;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.repository.grade.GradeRepository;
import kr.co.popool.service.career.CareerService;
import kr.co.popool.service.score.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

  private final GradeRepository gradeRepository;
  private final CareerService careerService;
  private final ScoreService scoreService;

  /**
   * 등급 + 세부 내역 조회
   *
   * @param memberIdentity : 인사 ID
   * @return GRADEDETAIL : 등급 DTO
   * @throws NotFoundException : 해당 아이디는 등급이 없습니다
   */
  @Override
  public GRADEDETAIL showGradeDetail(String memberIdentity) {

    return gradeRepository.showGradeDetail(memberIdentity)
        .orElseThrow(() -> new NotFoundException("해당 아이디는 등급이 없습니다"));

  }

  /**
   * 등급만 조회
   *
   * @param memberIdentity : 인사 ID
   * @return ONLYGRADE : 등급
   */
  @Override
  public ONLYGRADE showGradeOnly(String memberIdentity) {

    GradeEntity gradeEntity = careerService.findCareerEntity(memberIdentity).getGradeEntity();
    return GradeDto.ONLYGRADE.builder().grade(gradeEntity.getGrade())
        .build();

  }

  /**
   * 평가 등록 시 등급 테이블을 위한 각종 값을 계산 후 DTO반환
   *
   * @param memberIdentity : 인사 아이디
   * @return GETVALUE : 등급 테이블을 위한 값 (ex.평균)
   */
  @Override
  public GETVALUE getValue(String memberIdentity) {

    return gradeRepository.getValue(memberIdentity);

  }

  /**
   * 등급 DTO 생성
   *
   * @param memberIdentity , valueDto : 인사 아이디 , 평균,총 인원수 DTO
   * @return GRADEDETAIL : 최종 등급 DTO
   */
  @Override
  public GRADEDETAIL createGradeDto(String memberIdentity, GETVALUE valueDto) {

    return gradeRepository.makeGradeDto(memberIdentity, valueDto)
        .orElseThrow((() -> new BadRequestException("최종 등급 DTO 생성 실패")));

  }

  /**
   * 등급 DTO -> 등급 Entity
   *
   * @param memberIdentity , gradeDto : 인사 아이디 , 새로운 등급 DTO
   * @return GETVALUE : 등급 테이블을 위한 값 (ex.평균)
   */
  @Override
  public GradeEntity createGradeEntity(String memberIdentity, GRADEDETAIL gradeDto) {

    GradeEntity gradeEntity = GradeEntity.of(scoreService.findAllScore(memberIdentity), gradeDto);
    gradeRepository.save(gradeEntity);

    return gradeEntity;

  }

}