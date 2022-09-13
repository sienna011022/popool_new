package kr.co.popool.bblpayment.domain.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

    IN_PROGRESS("결제 진행중"),
    SUCCESS("결제 성공"),
    FAIL("결제 실패");

    String payment_status;

    public static PaymentStatus of(String status) {
        return Arrays.stream(PaymentStatus.values())
                .filter(s -> s.toString().equalsIgnoreCase(status))
                .findAny().orElseThrow(() -> new RuntimeException("해당 결제 상태 항목을 찾을 수 없습니다."));
    }
}
