package kr.co.popool.bblpayment.repository;

import kr.co.popool.bblpayment.domain.entity.ItemMstEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMstRepository extends JpaRepository<ItemMstEntity, Long> {
}
