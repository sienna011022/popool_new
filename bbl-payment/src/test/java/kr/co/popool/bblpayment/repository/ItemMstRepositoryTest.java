package kr.co.popool.bblpayment.repository;

import kr.co.popool.bblpayment.domain.entity.CouponEntity;
import kr.co.popool.bblpayment.domain.entity.ItemMstEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemMstRepositoryTest {

    @Autowired ItemMstRepository itemMstRepository;

    @Test
    void save() {
        ItemMstEntity coupon = new CouponEntity(1000, "ten",10);
        itemMstRepository.save(coupon);

        Optional<ItemMstEntity> findItem = itemMstRepository.findById(1L);
        System.out.println("findItem.get() = " + findItem.get());
    }

}