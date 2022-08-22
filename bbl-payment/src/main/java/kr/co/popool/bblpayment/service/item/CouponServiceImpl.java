package kr.co.popool.bblpayment.service.item;

import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblpayment.domain.dto.item.CouponDTO;
import kr.co.popool.bblpayment.domain.entity.item.CouponEntity;
import kr.co.popool.bblpayment.repository.item.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService{

    private final CouponRepository couponRepository;

    @Override
    @Transactional
    public void createCoupon(CouponDTO.CREATE createDTO) throws Exception {

        CouponEntity newCoupon = CouponEntity.builder()
                .name(createDTO.getName())
                .price(createDTO.getPrice())
                .amount(createDTO.getAmount())
                .build();

        couponRepository.save(newCoupon);
    }

    @Override
    @Transactional
    public void updateCoupon(CouponDTO.UPDATE updateDTO) throws Exception {

        CouponEntity findCoupon = couponRepository.findById(updateDTO.getCouponId()).orElseThrow(() -> new NotFoundException("Coupon"));
        findCoupon.update(updateDTO);
    }

    @Override
    public CouponDTO.READ readCouponDetail(Long couponId) throws Exception {

        CouponEntity findCoupon = couponRepository.findById(couponId).orElseThrow(() -> new NotFoundException("Coupon"));

        return CouponDTO.READ.builder()
                .couponId(findCoupon.getId())
                .name(findCoupon.getName())
                .amount(findCoupon.getAmount())
                .price(findCoupon.getPrice())
                .build();
    }

    @Override
    @Transactional
    public void deleteCoupon(Long couponId) throws Exception {
        couponRepository.deleteById(couponId);
    }
}
