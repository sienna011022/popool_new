package kr.co.popool.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE.DELETE;
import kr.co.popool.domain.dto.score.ScoreDto;
import kr.co.popool.service.grade.CalculateGradeService;
import kr.co.popool.service.grade.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/careers/{memberIdentity}/scores/grade")
public class GradeController {

  private final GradeService gradeService;
  private final CalculateGradeService calculateService;

  @ApiOperation("개인 등급 조회")
  @GetMapping("")
  public ResponseFormat showGrade(@PathVariable String memberIdentity) {
    return ResponseFormat.ok(gradeService.showGradeOnly(memberIdentity));
  }

  @ApiOperation("개인 등급 상세 내역 조회")
  @GetMapping("/all")
  public ResponseFormat showGradeInfo(@PathVariable String memberIdentity) {
    return ResponseFormat.ok(gradeService.showGradeDetail(memberIdentity));
  }


  public ResponseFormat createGrade(ScoreDto.SCOREINFO newScoreDto) {

    GRADEDETAIL gradedetail = calculateService.calculateGradeDto(newScoreDto);
    calculateService.saveGradeEntity(gradedetail, newScoreDto.getMemberIdentity());

    return ResponseFormat.ok();

  }

  public ResponseFormat updateGrade(DELETE deleteDto) {

    GRADEDETAIL gradedetail = calculateService.updateGradeDto(deleteDto);
    calculateService.saveGradeEntity(gradedetail, deleteDto.getMemberIdentity());

    return ResponseFormat.ok();

  }
}