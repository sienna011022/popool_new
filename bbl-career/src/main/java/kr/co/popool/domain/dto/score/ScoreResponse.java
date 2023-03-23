package kr.co.popool.domain.dto.score;


import com.querydsl.core.annotations.QueryProjection;
import kr.co.popool.domain.entity.Score;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class ScoreResponse {
    private String memberId;

    private int attendance;

    private int sincerity;

    private int positiveness;

    private int technical;

    private int cooperative;

    public static ScoreResponse of(Score score) {
        return ScoreResponse.builder()
            .memberId(score.getCareer().getMemberId())
            .positiveness(score.getPositiveness())
            .cooperative(score.getCooperative())
            .technical(score.getTechnical())
            .sincerity(score.getSincerity())
            .attendance(score.getAttendance())
            .build();
    }

    @QueryProjection
    @Builder
    public ScoreResponse(String memberId, int attendance, int sincerity, int positiveness, int technical, int cooperative) {
        this.memberId = memberId;
        this.attendance = attendance;
        this.sincerity = sincerity;
        this.positiveness = positiveness;
        this.technical = technical;
        this.cooperative = cooperative;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreResponse that = (ScoreResponse) o;
        return attendance == that.attendance && sincerity == that.sincerity && positiveness == that.positiveness && technical == that.technical && cooperative == that.cooperative && Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, attendance, sincerity, positiveness, technical, cooperative);
    }
}
