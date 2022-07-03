package kr.co.popool.bblmember.domain.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("남성"),
    FEMALE("여성");

    String gender;

    public static Gender of(String gender) {

        return Arrays.stream(Gender.values())
                .filter(g -> g.toString().equalsIgnoreCase(gender))
                .findAny().orElseThrow(() -> new RuntimeException("해당 Gender 항목을 찾을 수 없습니다."));
    }

}
