package kr.co.popool.score;

import kr.co.popool.TestConfig;
import kr.co.popool.domain.entity.Score;
import kr.co.popool.infra.config.QuerydslConfiguration;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.repository.score.ScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static kr.co.popool.career.CareerFixture.MEMBER_ID;
import static kr.co.popool.score.ScoreFixture.EVALUATOR_ID;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({TestConfig.class})
public class ScoreRepositoryTest {

    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    CareerRepository careerRepository;

    @Test
    @Transactional
    @DisplayName("평가를 저장하고 조회한다")
    void createScore() {
        Score score1 = ScoreFixture.createScore();
        Score score2 = ScoreFixture.createScore();

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        Optional<List<Score>> scores = scoreRepository.findByEvaluatorId(EVALUATOR_ID);

        assertThat(scores.get().size()).isEqualTo(2);

    }

    @Test
    @Transactional
    @DisplayName("QueryDsl : memberId의 모든 평가를 조회한다")
    void findByMemberId() {
        Score score1 = ScoreFixture.createScore();
        Score score2 = ScoreFixture.createScore();

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        assertThat(scoreRepository.findAllScores(MEMBER_ID).size())
            .isEqualTo(2);
    }

}
