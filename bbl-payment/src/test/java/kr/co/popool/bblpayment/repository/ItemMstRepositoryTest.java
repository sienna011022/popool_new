package kr.co.popool.bblpayment.repository;

import kr.co.popool.bblpayment.domain.entity.CouponEntity;
import kr.co.popool.bblpayment.domain.entity.ItemMstEntity;
import kr.co.popool.bblpayment.domain.entity.PeriodCouponEntity;
import kr.co.popool.bblpayment.domain.entity.SubscribeEntity;
import kr.co.popool.bblpayment.domain.shared.enums.CouponPeriod;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
class ItemMstRepositoryTest {

    @Autowired ItemMstRepository itemMstRepository;

    @Test
    void save() {
        ItemMstEntity coupon = new CouponEntity(1000, "coupon",10);
        ItemMstEntity periodCoupon = new PeriodCouponEntity(10000, "period-coupon", "1개월");
        ItemMstEntity subscribe = new SubscribeEntity(3000, "subscribe", LocalDate.now());

        itemMstRepository.save(coupon);
        itemMstRepository.save(periodCoupon);
        itemMstRepository.save(subscribe);
    }

}