package kr.co.popool.bblpayment.repository;

import kr.co.popool.bblpayment.domain.entity.item.CouponEntity;
import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
import kr.co.popool.bblpayment.domain.entity.item.PeriodCouponEntity;
import kr.co.popool.bblpayment.domain.entity.item.SubscribeEntity;
import kr.co.popool.bblpayment.repository.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

//@DataJpaTest
@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void save() {
        ItemMstEntity coupon = new CouponEntity(1000, "coupon",10);
        ItemMstEntity periodCoupon = new PeriodCouponEntity(10000, "period-coupon", "1개월");
        ItemMstEntity subscribe = new SubscribeEntity(3000, "subscribe", LocalDate.now());

        itemRepository.save(coupon);
        itemRepository.save(periodCoupon);
        itemRepository.save(subscribe);
    }

}