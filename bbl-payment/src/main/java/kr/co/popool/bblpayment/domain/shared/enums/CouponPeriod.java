package kr.co.popool.bblpayment.domain.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CouponPeriod {

    ONE_MONTH(1, "1개월"),
    THREE_MONTH(3, "3개월"),
    SIX_MONTH(6, "6개월"),
    ONE_YEAR(12, "1년");

    private int period;
    private String period_str;

    public static CouponPeriod of(String period_str) {

        return Arrays.stream(CouponPeriod.values())
                .filter(c -> c.getPeriod_str().equalsIgnoreCase(period_str))
                .findAny().orElseThrow(() -> new RuntimeException("해당 기간의 쿠폰 항목을 찾을 수 없습니다."));
    }
}
