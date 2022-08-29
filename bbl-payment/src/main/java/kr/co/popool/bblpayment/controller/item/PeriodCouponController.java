package kr.co.popool.bblpayment.controller.item;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblpayment.domain.dto.item.CouponDTO;
import kr.co.popool.bblpayment.domain.dto.item.PeriodCouponDTO;
import kr.co.popool.bblpayment.service.item.PeriodCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PeriodCouponController {

    private final PeriodCouponService periodCouponService;

    @PostMapping("/items/periodCoupon/create")
    public ResponseFormat createCoupon(@RequestBody PeriodCouponDTO.CREATE createDTO) {

        try {
            periodCouponService.createPeriodCoupon(createDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @GetMapping("/items/periodCoupon/{periodCouponId}")
    public ResponseFormat<CouponDTO.DETAIL> readCouponDetail(@PathVariable("periodCouponId") Long periodCouponId) {

        PeriodCouponDTO.DETAIL periodCouponDetail;

        try {
            periodCouponDetail = periodCouponService.readPeriodCouponDetail(periodCouponId);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok(periodCouponDetail);
    }

    @PatchMapping("/items/periodCoupon/update")
    public ResponseFormat updateCoupon(@RequestBody PeriodCouponDTO.UPDATE updateDTO) {

        try {
            periodCouponService.updatePeriodCoupon(updateDTO);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }

    @DeleteMapping("/items/periodCoupon/delete/{periodCouponId}")
    public ResponseFormat deleteCoupon(@PathVariable("periodCouponId") Long periodCouponId) {

        try {
            periodCouponService.deletePeriodCoupon(periodCouponId);
        }
        catch (Exception e) {
            return ResponseFormat.fail(e.getMessage());
        }

        return ResponseFormat.ok();
    }
}
