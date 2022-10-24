package kr.co.popool.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE.DELETE;
import kr.co.popool.domain.dto.score.ScoreDto;
import kr.co.popool.service.grade.GradeServiceImpl;
import kr.co.popool.service.score.ScoreServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/careers/{memberIdentity}/scores")
public class ScoreController {

  private final ScoreServiceImpl scoreService;

  @ApiOperation("개인 평가 내역 조회")
  @GetMapping()
  public ResponseFormat show(@PathVariable String memberIdentity) {
    List<SHOWSCORE> scoreDtoList = scoreService.showScores(memberIdentity);
    return ResponseFormat.ok(scoreDtoList);
  }

  @ApiOperation("평가 내역 등록")
  @PostMapping("/create")
  public ResponseFormat create(@RequestBody ScoreDto.SCOREINFO newScoreDto) {
    scoreService.createScore(newScoreDto);
    //gradeServiceImpl.createGrade(newScoreDto);
    return ResponseFormat.ok();
  }

  @ApiOperation("평가 내역 수정")
  @PatchMapping("/{evaluatorIdentity}")
  public ResponseFormat update(@RequestBody ScoreDto.UPDATE updateScoreDto) {
    scoreService.updateScore(updateScoreDto);
    return ResponseFormat.ok();

  }

  @ApiOperation("평가 내역 삭제")
  @DeleteMapping("/delete")
  public ResponseFormat delete(@RequestBody DELETE deleteDto){
    scoreService.delete(deleteDto);
    //gradeServiceImpl.updateGrade(deleteDto);
    return ResponseFormat.ok();
  }

}
