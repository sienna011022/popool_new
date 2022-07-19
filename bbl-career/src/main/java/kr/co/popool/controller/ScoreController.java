package kr.co.popool.controller;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.service.ScoreServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
@RequestMapping(value="/careers/{memberIdentity}/scores")
public class ScoreController {

        private final ScoreServiceImpl scoreService;

        @GetMapping()
        public ResponseFormat show(@PathVariable String memberIdentity){
            List<ScoreDto.SHOWSCORE> scoreDtoList = scoreService.showScores(memberIdentity);
            return ResponseFormat.ok(scoreDtoList);
        }

        @PostMapping()
        public ResponseFormat create( @RequestBody ScoreDto.SCOREINFO newScoreDto) {
            scoreService.createScore(newScoreDto);
            return ResponseFormat.ok();
        }

        @PatchMapping("/{evaluatorIdentity}")
        public ResponseFormat update( @RequestBody ScoreDto.UPDATE updateScoreDto) {
            scoreService.updateScore(updateScoreDto);
            return ResponseFormat.ok();
        }

}
