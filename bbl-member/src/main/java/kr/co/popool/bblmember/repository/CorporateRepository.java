package kr.co.memberservice.repository;

import kr.co.memberservice.domain.entity.CorporateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateRepository extends JpaRepository<CorporateEntity, Long> {
}
