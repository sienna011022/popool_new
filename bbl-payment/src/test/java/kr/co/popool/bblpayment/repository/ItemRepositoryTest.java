package kr.co.popool.bblpayment.repository;

import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblpayment.domain.entity.item.CouponEntity;
import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
import kr.co.popool.bblpayment.domain.entity.item.PeriodCouponEntity;
import kr.co.popool.bblpayment.domain.entity.item.SubscribeEntity;
import kr.co.popool.bblpayment.domain.entity.payment.KakaoPayLogEntity;
import kr.co.popool.bblpayment.repository.item.ItemRepository;
import kr.co.popool.bblpayment.repository.payment.KakaoPayLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

//@DataJpaTest
@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository<ItemMstEntity> itemRepository;

    @Autowired
    KakaoPayLogRepository kakaoPayLogRepository;

    @Test
    void save() {
        ItemMstEntity coupon = new CouponEntity(1000, "coupon",10);
        ItemMstEntity periodCoupon = new PeriodCouponEntity(10000, "period-coupon", "1개월");
        ItemMstEntity subscribe = new SubscribeEntity(3000, "subscribe", LocalDate.now());

        itemRepository.save(coupon);
        itemRepository.save(periodCoupon);
        itemRepository.save(subscribe);
    }

    @Test
    void typeCast() {
        
        KakaoPayLogEntity log = kakaoPayLogRepository.findById(1L).orElseThrow(() -> new NotFoundException("log"));

        System.out.println("(item instanceof CouponEntity) = " + (log.getItem() instanceof CouponEntity));
        
    }

}