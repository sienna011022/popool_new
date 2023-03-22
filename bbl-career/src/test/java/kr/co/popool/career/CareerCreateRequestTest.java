package kr.co.popool.career;

import kr.co.popool.domain.dto.career.CareerCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kr.co.popool.career.CareerFixture.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CareerCreateRequestTest {
    @Test
    @DisplayName("아이디를 입력하지 않으면 예외 발생")
    public void career_생성_예외1() {

        CareerCreateRequest errorRequest = CareerCreateRequest.builder()
            .memberId(null)
            .name(NAME)
            .email(EMAIL)
            .period(PERIOD)
            .selfDescription(SELF_DESCRIPTION)
            .build();

        assertThatThrownBy(() -> errorRequest.toCareer()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름을 입력하지 않으면 예외 발생")
    public void career_생성_예외2() {
        CareerCreateRequest errorRequest = CareerCreateRequest.builder()
            .memberId(MEMBER_ID)
            .name(null)
            .email(EMAIL)
            .period(PERIOD)
            .selfDescription(SELF_DESCRIPTION)
            .build();

        assertThatThrownBy(() -> errorRequest.toCareer()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이메일을 입력하지 않으면 예외 발생")
    public void career_생성_예외3() {

        CareerCreateRequest errorRequest = CareerCreateRequest.builder()
            .memberId(MEMBER_ID)
            .name(NAME)
            .email(null)
            .period(PERIOD)
            .selfDescription(SELF_DESCRIPTION)
            .build();

        assertThatThrownBy(() -> errorRequest.toCareer()).isInstanceOf(IllegalArgumentException.class);
    }
}
