package kr.co.popool.domain.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
@Getter
@AllArgsConstructor
public enum ScoreGrade {

    GOLD("골드"),
    SILVER("실버"),
    BRONZE("브론즈"),
    BLACK("블랙");

    private String grade;

    public static ScoreGrade of(String grade) {
        return Arrays.stream(ScoreGrade.values())
                .filter(r -> r.toString().equalsIgnoreCase(grade))
                .findAny().orElseThrow(() -> new RuntimeException("해당 등급 항목을 찾을 수 없습니다."));
    }
}



