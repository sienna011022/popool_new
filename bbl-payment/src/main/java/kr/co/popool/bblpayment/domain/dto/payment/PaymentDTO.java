package kr.co.popool.bblpayment.domain.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PaymentDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class REQUEST {

        private Long memberId;
        private Long itemId;
        private int quantity;
        private int totalAmount;
        private int taxFreeAmount;
        private String paymentStatus;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class DETAIL {

        private String itemName;
        private int itemPrice;
        private int quantity;
        private int totalAmount;
        private int taxFreeAmount;
        private String paymentStatus;
        private String paymentDate;
    }
}
