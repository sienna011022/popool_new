package kr.co.popool.bblpayment.service.item;

import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblpayment.domain.dto.item.PeriodCouponDTO;
import kr.co.popool.bblpayment.domain.entity.item.PeriodCouponEntity;
import kr.co.popool.bblpayment.repository.item.PeriodCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PeriodCouponServiceImpl implements PeriodCouponService{

    private final PeriodCouponRepository periodCouponRepository;

    @Override
    @Transactional
    public void createPeriodCoupon(PeriodCouponDTO.CREATE createDTO) throws Exception {

        PeriodCouponEntity newPeriodCoupon = PeriodCouponEntity.builder()
                .name(createDTO.getName())
                .price(createDTO.getPrice())
                .period(createDTO.getPeriod())
                .build();

        periodCouponRepository.save(newPeriodCoupon);
    }

    @Override
    @Transactional
    public void updatePeriodCoupon(PeriodCouponDTO.UPDATE updateDTO) throws Exception {

        PeriodCouponEntity findPeriodCoupon = periodCouponRepository.findById(updateDTO.getPeriodCouponId()).orElseThrow(() -> new NotFoundException("PeriodCoupon"));
        findPeriodCoupon.update(updateDTO);
    }

    @Override
    public PeriodCouponDTO.DETAIL readPeriodCouponDetail(Long periodCouponId) throws Exception {

        PeriodCouponEntity findPeriodCoupon = periodCouponRepository.findById(periodCouponId).orElseThrow(() -> new NotFoundException("PeriodCoupon"));

        return PeriodCouponDTO.DETAIL.builder()
                .periodCouponId(findPeriodCoupon.getId())
                .name(findPeriodCoupon.getName())
                .price(findPeriodCoupon.getPrice())
                .period(findPeriodCoupon.getPeriod().getPeriod_str())
                .build();
    }

    @Override
    @Transactional
    public void deletePeriodCoupon(Long periodCouponId) throws Exception {
        periodCouponRepository.deleteById(periodCouponId);
    }
}
