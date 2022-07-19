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
@RequestMapping(value="/careers/{id}/scores")
public class ScoreController {

        private final ScoreServiceImpl scoreService;

        @GetMapping()
        public ResponseFormat show(@PathVariable Long id){
            List<ScoreDto.SCOREINFO> scoreDtoList = scoreService.showScores(id);
            return ResponseFormat.ok(scoreDtoList);
        }

        @PostMapping()
        public ResponseFormat create(@PathVariable Long id, @RequestBody ScoreDto.SCOREINFO newScoreDto) {
            scoreService.createScore(id,newScoreDto);
            return ResponseFormat.ok();
        }

        @PatchMapping("/{scoreId}")
        public ResponseFormat update(@PathVariable Long scoreId, @RequestBody ScoreDto.UPDATE updateScoreDto) {
            scoreService.updateScore(scoreId,updateScoreDto);
            return ResponseFormat.ok();
        }

}
