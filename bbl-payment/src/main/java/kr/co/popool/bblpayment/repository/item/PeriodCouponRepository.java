package kr.co.popool.bblpayment.repository.item;

import kr.co.popool.bblpayment.domain.entity.item.PeriodCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodCouponRepository extends JpaRepository<PeriodCouponEntity, Long> {
}
