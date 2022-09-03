package kr.co.popool.bblpayment.service.item;


import kr.co.popool.bblpayment.domain.dto.item.CouponDTO;

public interface CouponService {

    public void createCoupon(CouponDTO.CREATE create) throws Exception;
    public void updateCoupon(CouponDTO.UPDATE update) throws Exception;
    public CouponDTO.DETAIL readCouponDetail(Long couponId) throws Exception;
    public void deleteCoupon(Long couponId) throws Exception;
}
