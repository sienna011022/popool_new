package kr.co.popool.bblpayment.domain.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CouponDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CREATE {

        private int price;
        private String name;
        private int amount;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class READ {

        private Long couponId;
        private int price;
        private String name;
        private int amount;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE {

        private Long couponId;
        private int price;
        private String name;
        private int amount;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class DETAIL {

        private Long couponId;
        private int price;
        private String name;
        private int amount;

    }
}
