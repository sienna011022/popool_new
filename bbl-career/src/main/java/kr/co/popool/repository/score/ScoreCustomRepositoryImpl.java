package kr.co.popool.repository.score;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.popool.domain.dto.score.QScoreResponse;
import kr.co.popool.domain.dto.score.ScoreResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.co.popool.domain.entity.QScore.score;

@RequiredArgsConstructor
public class ScoreCustomRepositoryImpl implements ScoreCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ScoreResponse> findAllScores(String memberId) {
        return jpaQueryFactory.select(
                new QScoreResponse(
                    score.career.memberId,
                    score.attendance,
                    score.sincerity,
                    score.positiveness,
                    score.technical,
                    score.cooperative))
            .from(score)
            .where(score.career.memberId.eq(memberId))
            .fetch();
    }
}


