package kr.co.popool.bblpayment.repository.item;

import kr.co.popool.bblpayment.domain.entity.item.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
}
