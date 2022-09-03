package kr.co.popool.bblpayment.controller.payment;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblpayment.domain.dto.payment.KakaoPayDTO;
import kr.co.popool.bblpayment.domain.dto.payment.KakaoSubscribeDTO;
import kr.co.popool.bblpayment.service.payment.KakaoPayService;
import kr.co.popool.bblpayment.service.payment.KakaoSubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final KakaoSubscribeService kakaoSubscribeService;


    @PostMapping("/payments/kakao")
    public ResponseFormat<KakaoPayDTO.READY_RESPONSE> requestPayment(@RequestBody KakaoPayDTO.ORDER requestDTO) {

        try {
            kakaoPayService.requestPayment(requestDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @GetMapping("/payments/kakao/success/{kakaoPayLogId}")
    public ResponseFormat successPayment(@PathVariable("kakaoPayLogId") Long kakaoPayLogId, @RequestParam("pg_token") String pgToken) {
        
        try {
            kakaoPayService.successPayment(kakaoPayLogId, pgToken);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @PostMapping("/payments/kakao/subscription")
    public ResponseFormat<KakaoSubscribeDTO.FIRST_READY_RESPONSE> requestSubscription(@RequestBody KakaoSubscribeDTO.FIRST_ORDER requestDTO) {

        try {
            kakaoSubscribeService.requestSubscription(requestDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @GetMapping("/payments/kakao/subscription/success/{kakaoPayLogId}")
    public ResponseFormat successSubscription(@PathVariable("kakaoPayLogId") Long kakaoPayLogId, @RequestParam("pg_token") String pgToken) {

        try {
            kakaoSubscribeService.successSubscription(kakaoPayLogId, pgToken);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }
}
