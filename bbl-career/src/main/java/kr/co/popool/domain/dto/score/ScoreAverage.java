package kr.co.popool.domain.dto.score;

import com.querydsl.core.Tuple;
import com.querydsl.core.annotations.QueryProjection;
import kr.co.popool.domain.entity.Score;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

@RequiredArgsConstructor
@Getter
public class ScoreAverage {
    private final static double DEFAULT = 0.0;
    private double attendance;

    private double sincerity;

    private double positiveness;

    private double technical;

    private double cooperative;

    private long amount;

    @QueryProjection
    @Builder
    private ScoreAverage(double attendance, double sincerity, double positiveness, double technical, double cooperative, long amount) {
        this.attendance = attendance;
        this.sincerity = sincerity;
        this.positiveness = positiveness;
        this.technical = technical;
        this.cooperative = cooperative;
        this.amount = amount;
    }


    public BigDecimal getAvg() {
        BigDecimal sumOfAvg = valueOf(DEFAULT);
        List<BigDecimal> decimals = asList(valueOf(attendance), valueOf(cooperative), valueOf(positiveness), valueOf(sincerity), valueOf(technical));
        for (BigDecimal avg : decimals) {
            sumOfAvg.add(avg);
        }
        return sumOfAvg.divide(valueOf(amount));
    }
}
