package kr.co.popool.bblpayment.repository.item;

import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository<T extends ItemMstEntity> extends JpaRepository<T, Long> {
}
