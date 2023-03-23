package kr.co.popool.domain.dto.score;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class ScoreResponses {

    private List<ScoreResponse> responses;

    public static ScoreResponses of(List<ScoreResponse> responses){
        return new ScoreResponses(responses);
    }

    public ScoreResponses(List<ScoreResponse> responses) {
        this.responses = responses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreResponses that = (ScoreResponses) o;
        return Objects.equals(responses, that.responses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responses);
    }
}
