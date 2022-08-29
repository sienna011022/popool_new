package kr.co.popool.bblpayment.domain.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class ItemDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CREATE {

        private Long itemId;
        private int price;
        private String name;
        private int amount;
        private String period;
        private LocalDate payDatePerMonth;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class READ {

        private int price;
        private String name;
        private int amount;
        private String period;
        private LocalDate payDatePerMonth;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE {

        private int price;
        private String name;
        private int amount;
        private String period;
        private LocalDate payDatePerMonth;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class DETAIL {

        private Long itemId;
        private int price;
        private String name;
        private int amount;
        private String period;
        private LocalDate payDatePerMonth;

    }
}
