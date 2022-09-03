package kr.co.popool.bblpayment.repository.payment;

import kr.co.popool.bblpayment.domain.entity.payment.PaymentLogMstEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentLogRepository extends JpaRepository<PaymentLogMstEntity, Long> {

    @EntityGraph(attributePaths = {"item"})
    List<PaymentLogMstEntity> findPaymentLogMstEntitiesByMemberId(Long memberId);
}
