package kr.co.popool.bblpayment.repository.inventory;

import kr.co.popool.bblpayment.domain.entity.item.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Optional<InventoryEntity> findInventoryEntityByMemberId(Long memberId);
    List<InventoryEntity> findInventoryEntityBySidIsNotNull();
}
