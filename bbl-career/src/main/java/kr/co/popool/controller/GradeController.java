package kr.co.popool.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.GradeDto;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.service.GradeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j //로깅을 위함
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
@RequestMapping(value="/careers/{memberIdentity}/scores/grade")
public class GradeController {

    private final GradeServiceImpl gradeService;
    
    @ApiOperation("개인 등급 조회")
    @GetMapping("")
    public ResponseFormat showGrade(@PathVariable String memberIdentity){
        GradeDto.ONLYGRADE onlyGrade = gradeService.showGrade(memberIdentity);
        return ResponseFormat.ok(onlyGrade);
    }
    
    @ApiOperation("개인 등급 상세 내역 조회")
    @GetMapping("/all")
    public ResponseFormat showGradeInfo(@PathVariable String memberIdentity){
        GradeDto.GRADEINFO gradeInfo = gradeService.showGradeInfo(memberIdentity);
        return ResponseFormat.ok(gradeInfo);
    }
}
