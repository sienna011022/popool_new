package kr.co.popool.bblpayment.domain.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PeriodCouponDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CREATE {

        private int price;
        private String name;
        private String period;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class READ {

        private Long periodCouponId;
        private int price;
        private String name;
        private String period;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE {

        private Long periodCouponId;
        private int price;
        private String name;
        private String period;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class DETAIL {

        private Long periodCouponId;
        private int price;
        private String name;
        private String period;
    }
}
