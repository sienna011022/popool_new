package kr.co.popool.bblpayment.controller.item;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblpayment.domain.dto.item.CouponDTO;
import kr.co.popool.bblpayment.service.item.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/items/coupon/create")
    public ResponseFormat createCoupon(@RequestBody CouponDTO.CREATE createDTO) {

        try {
            couponService.createCoupon(createDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @GetMapping("/items/coupon/{couponId}")
    public ResponseFormat<CouponDTO.DETAIL> readCouponDetail(@PathVariable("couponId") Long couponId) {

        CouponDTO.DETAIL couponDetail;

        try {
            couponDetail = couponService.readCouponDetail(couponId);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok(couponDetail);
    }

    @PatchMapping("/items/coupon/update")
    public ResponseFormat updateCoupon(@RequestBody CouponDTO.UPDATE updateDTO) {

        try {
            couponService.updateCoupon(updateDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @DeleteMapping("/items/coupon/delete/{couponId}")
    public ResponseFormat deleteCoupon(@PathVariable("couponId") Long couponId) {

        try {
            couponService.deleteCoupon(couponId);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }
}
