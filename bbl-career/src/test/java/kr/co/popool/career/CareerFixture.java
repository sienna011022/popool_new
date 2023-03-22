package kr.co.popool.career;


import kr.co.popool.domain.entity.Career;

public class CareerFixture {

    public final static String MEMBER_ID = "sienna1022";
    public final static String NAME = "김성윤";
    public final static String EMAIL = "sienna011022@naver.com";
    public final static String PERIOD = "0years";
    public final static String SELF_DESCRIPTION = "안녕하세요 백엔드 인턴을 준비중인 김성윤입니다";

    public final static String UPDATE_SELF_DESCRIPTION = "안녕하세요 백엔드 주니어 개발자 김성윤입니다";

    public static Career createCareer() {
        return Career.builder()
            .memberId(MEMBER_ID)
            .name(NAME)
            .email(EMAIL)
            .period(PERIOD)
            .selfDescription(SELF_DESCRIPTION)
            .build();
    }

}

