package kr.co.popool.bblpayment.service.item;

import kr.co.popool.bblpayment.domain.dto.item.PeriodCouponDTO;

public interface PeriodCouponService {

    public void createPeriodCoupon(PeriodCouponDTO.CREATE create) throws Exception;
    public void updatePeriodCoupon(PeriodCouponDTO.UPDATE update) throws Exception;
    public PeriodCouponDTO.DETAIL readPeriodCouponDetail(Long periodCouponId) throws Exception;
    public void deletePeriodCoupon(Long periodCouponId) throws Exception;
}
