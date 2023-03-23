package kr.co.popool.score;

import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.repository.score.ScoreRepository;
import kr.co.popool.service.score.ScoreService;
import kr.co.popool.service.score.ScoreServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static kr.co.popool.career.CareerFixture.MEMBER_ID;
import static kr.co.popool.career.CareerFixture.createCareer;
import static kr.co.popool.score.ScoreFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScoreServiceTest {
    @Mock
    CareerRepository careerRepository;

    @Mock
    ScoreRepository scoreRepository;

    @InjectMocks
    ScoreServiceImpl scoreService;

    @Test
    @DisplayName("인사 내역을 등록하고, 평가자 아이디로 자신이 평가한 평가를 모두 조회할 수 있다")
    public void 평가_등록_및_조회() {
        when(careerRepository.findByMemberId(MEMBER_ID))
            .thenReturn(of(createCareer()));

        when(scoreRepository.findByEvaluatorId(EVALUATOR_ID))
            .thenReturn(of(asList(createScore(),createScore())));

        scoreService.newScore(createScoreRequest());
        scoreService.newScore(createScoreRequest());

        assertThat(scoreService.showScoreAllByEvaluator(EVALUATOR_ID).size())
            .isEqualTo(2);
    }

}
