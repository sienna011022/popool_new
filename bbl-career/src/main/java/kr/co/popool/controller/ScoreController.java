package kr.co.popool.controller;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.service.ScoreServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
@RequestMapping(value="/careers/{id}/scores")
public class ScoreController {

        private final ScoreServiceImpl scoreService;

        @PostMapping()
        public ResponseFormat create(@PathVariable Long id, @RequestBody ScoreDto.SCOREINFO newScoreDto) {
            ScoreDto.SCOREINFO created = scoreService.createScore(id,newScoreDto);
            return ResponseFormat.ok(created);


        }

}
