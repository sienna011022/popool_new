package kr.co.popool.controller;

import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE.DELETE;
import kr.co.popool.domain.dto.score.ScoreCreateRequest;
import kr.co.popool.domain.dto.score.ScoreDto;
import kr.co.popool.domain.dto.score.ScoreResponse;
import kr.co.popool.domain.dto.score.ScoreResponses;
import kr.co.popool.domain.entity.Score;
import kr.co.popool.service.score.ScoreServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static kr.co.popool.domain.dto.score.ScoreResponses.of;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/careers/{memberId}/scores")
public class ScoreController {
    private final ScoreServiceImpl scoreService;


    @ApiOperation("자신에게 평가된 모든 평가 내역 조회")
    @GetMapping
    public ResponseEntity showAll(@RequestParam String evaluatorId) {
        List<Score> scores = scoreService.showScoreAllByEvaluator(evaluatorId);
        ScoreResponses scoreResponses = of(scores.stream()
            .map(ScoreResponse::of)
            .collect(toList()));
        return new ResponseEntity(scoreResponses, HttpStatus.OK);
    }

    @ApiOperation("자신이 등록한 모든 평가 내역 조회")
    @GetMapping("/all")
    public ResponseEntity<ScoreResponses> showAllMyScores(@RequestParam String memberId) {
        return ResponseEntity.ok(scoreService.showMyAllScore(memberId));
    }

    @ApiOperation("평가 내역 등록")
    @PostMapping
    public ResponseEntity<Void> newScore(@RequestBody ScoreCreateRequest request) {
        ScoreResponse saved = ScoreResponse.of(scoreService.newScore(request));
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

}
