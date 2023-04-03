package kr.co.popool.repository.score;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.popool.domain.dto.score.QScoreAverage;
import kr.co.popool.domain.dto.score.QScoreResponse;
import kr.co.popool.domain.dto.score.ScoreAverage;
import kr.co.popool.domain.dto.score.ScoreResponse;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
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

    @Override
    public ScoreAverage getAverage(String memberId) {
        List<Tuple> result = jpaQueryFactory.select(
                score.attendance.avg(),
                score.cooperative.avg(),
                score.technical.avg(),
                score.sincerity.avg(),
                score.positiveness.avg(),
                score.count())
            .from(score)
            .where(score.career.memberId.eq(memberId))
            .fetch();
        ScoreAverage scoreAverage = new ScoreAverage();
        for (Tuple tuple : result) {
            scoreAverage = ScoreAverage
                .builder()
                .sincerity(tuple.get(score.sincerity))
                .attendance(tuple.get(score.attendance))
                .cooperative(tuple.get(score.cooperative))
                .positiveness(tuple.get(score.positiveness))
                .technical(tuple.get(score.technical))
                .build();
        }
        return scoreAverage;
    }
}



