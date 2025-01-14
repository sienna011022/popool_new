package kr.co.popool.service.score;

import kr.co.popool.bblcommon.error.exception.NotFoundCareerException;
import kr.co.popool.bblcommon.error.exception.NotFoundScoreException;
import kr.co.popool.domain.dto.score.ScoreAverage;
import kr.co.popool.domain.dto.score.ScoreCreateRequest;
import kr.co.popool.domain.dto.score.ScoreResponse;
import kr.co.popool.domain.dto.score.ScoreResponses;
import kr.co.popool.domain.entity.Career;
import kr.co.popool.domain.entity.Grade;
import kr.co.popool.domain.entity.Score;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.repository.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final CareerRepository careerRepository;

    @Transactional
    @Override
    public Score newScore(ScoreCreateRequest request) {
        Career career = careerRepository.findByMemberId(request.getMemberId())
            .orElseThrow(NotFoundCareerException::new);
        Score requestScore = request.toScore(career);
        Score newScore = scoreRepository.save(requestScore);
        career.updateGrade(getGrade(request.getMemberId()));
        careerRepository.save(career);
        return newScore;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Score> showScoreAllByEvaluator(String evaluatorId) {
        return scoreRepository.findByEvaluatorId(evaluatorId)
            .orElseThrow(() -> new NotFoundScoreException());
    }

    @Transactional(readOnly = true)
    @Override
    public ScoreResponses showMyAllScore(String targetId) {
        List<ScoreResponse> scoreResponses = scoreRepository.findAllScores(targetId);
        return ScoreResponses.of(scoreResponses);
    }

    @Override
    @Transactional(readOnly = true)
    public Grade getGrade(String memberId) {
        ScoreAverage averageInfo = scoreRepository.getAverage(memberId);
        return Grade.getMedal(averageInfo.getAvg());
    }

}


