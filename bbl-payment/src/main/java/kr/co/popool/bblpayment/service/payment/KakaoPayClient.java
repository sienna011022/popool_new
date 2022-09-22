package kr.co.popool.bblpayment.service.payment;

import kr.co.popool.bblpayment.domain.dto.payment.KakaoPayDTO;
import kr.co.popool.bblpayment.domain.dto.payment.KakaoSubscribeDTO;
import kr.co.popool.bblpayment.infra.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao-pay", url = "https://kapi.kakao.com", configuration = {FeignClientConfig.class})
public interface KakaoPayClient {

    @PostMapping(value = "/v1/payment/ready", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    KakaoPayDTO.READY_RESPONSE requestPayForReady(
            @RequestHeader("Authorization") String adminKey,
            @RequestBody KakaoPayDTO.READY_REQUEST request);

    @PostMapping(value = "/v1/payment/approve", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    KakaoPayDTO.APPROVAL_RESPONSE requestPayForApproval(
            @RequestHeader("Authorization") String adminKey,
            @RequestBody KakaoPayDTO.APPROVAL_REQUEST request);


    @PostMapping(value = "/v1/payment/ready", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    KakaoSubscribeDTO.FIRST_READY_RESPONSE requestFirstSubscribeForReady(
            @RequestHeader("Authorization") String adminKey,
            @RequestBody KakaoSubscribeDTO.FIRST_READY_REQUEST request);

    @PostMapping(value = "/v1/payment/approve", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    KakaoSubscribeDTO.FIRST_APPROVAL_RESPONSE requestFirstSubscribeForApproval(
            @RequestHeader("Authorization") String adminKey,
            @RequestBody KakaoSubscribeDTO.FIRST_APPROVAL_REQUEST request);

}
