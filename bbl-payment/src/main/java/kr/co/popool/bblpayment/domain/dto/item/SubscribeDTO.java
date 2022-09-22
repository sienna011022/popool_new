package kr.co.popool.bblpayment.domain.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class SubscribeDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CREATE {

        private int price;
        private String name;
        private String payDatePerMonth;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class READ {

        private Long subscribeId;
        private int price;
        private String name;
        private String payDatePerMonth;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE {

        private Long subscribeId;
        private int price;
        private String name;
        private String payDatePerMonth;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class DETAIL {

        private Long subscribeId;
        private int price;
        private String name;
        private String payDatePerMonth;

    }
}
