package kr.co.popool.score;


import kr.co.popool.career.CareerFixture;
import kr.co.popool.domain.dto.score.ScoreCreateRequest;
import kr.co.popool.domain.dto.score.ScoreResponse;
import kr.co.popool.domain.dto.score.ScoreResponses;
import kr.co.popool.domain.entity.Score;

import java.util.Arrays;

import static kr.co.popool.career.CareerFixture.MEMBER_ID;

public class ScoreFixture {
    public static final String EVALUATOR_ID = "evaluator1022";
    private static final int DEFAULT_TEST_SCORE = 5;

    public static Score createScore() {
        return Score.builder()
            .career(CareerFixture.createCareer())
            .evaluatorId(EVALUATOR_ID)
            .attendance(DEFAULT_TEST_SCORE)
            .cooperative(DEFAULT_TEST_SCORE)
            .positiveness(DEFAULT_TEST_SCORE)
            .sincerity(DEFAULT_TEST_SCORE)
            .technical(DEFAULT_TEST_SCORE)
            .build();
    }

    public static ScoreCreateRequest createScoreRequest() {
        return ScoreCreateRequest.builder()
            .memberId(MEMBER_ID)
            .evaluatorId(EVALUATOR_ID)
            .attendance(DEFAULT_TEST_SCORE)
            .cooperative(DEFAULT_TEST_SCORE)
            .positiveness(DEFAULT_TEST_SCORE)
            .sincerity(DEFAULT_TEST_SCORE)
            .technical(DEFAULT_TEST_SCORE)
            .build();
    }


    public static ScoreResponses createScoreResponses() {
        return ScoreResponses.of(Arrays.asList(
            ScoreResponse.builder()
                .memberId(MEMBER_ID)
                .attendance(DEFAULT_TEST_SCORE)
                .cooperative(DEFAULT_TEST_SCORE)
                .positiveness(DEFAULT_TEST_SCORE)
                .sincerity(DEFAULT_TEST_SCORE)
                .technical(DEFAULT_TEST_SCORE)
                .build()));
    }
}
