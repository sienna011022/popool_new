package kr.co.popool.bblpayment.repository.payment;

import kr.co.popool.bblpayment.domain.entity.payment.KakaoPayLogEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoPayLogRepository extends JpaRepository<KakaoPayLogEntity, Long> {

    @EntityGraph(attributePaths = {"item"})
    Optional<KakaoPayLogEntity> findById(Long logId);
}
