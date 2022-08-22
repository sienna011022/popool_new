package kr.co.popool.bblpayment.controller.item;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblpayment.domain.dto.item.CouponDTO;
import kr.co.popool.bblpayment.domain.dto.item.SubscribeDTO;
import kr.co.popool.bblpayment.service.item.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SubscribeController {

    private final SubscribeService subscribeService;


    @PostMapping("/items/subscribe/create")
    public ResponseFormat createCoupon(@RequestBody SubscribeDTO.CREATE createDTO) {

        try {
            subscribeService.createSubscribe(createDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @GetMapping("/items/subscribe/{subscribeId}")
    public ResponseFormat<CouponDTO.DETAIL> readCouponDetail(@PathVariable("subscribeId") Long subscribeId) {

        SubscribeDTO.DETAIL subscribeDetail;

        try {
            subscribeDetail = subscribeService.readSubscribeDetail(subscribeId);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok(subscribeDetail);
    }

    @PatchMapping("/items/subscribe/update")
    public ResponseFormat updateCoupon(@RequestBody SubscribeDTO.UPDATE updateDTO) {

        try {
            subscribeService.updateSubscribe(updateDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @DeleteMapping("/items/subscribe/delete/{subscribeId}")
    public ResponseFormat deleteCoupon(@PathVariable("subscribeId") Long subscribeId) {

        try {
            subscribeService.deleteSubscribe(subscribeId);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }
}
