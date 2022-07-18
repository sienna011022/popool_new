package kr.co.popool.bblmember.repository;

import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorporateRepository extends JpaRepository<CorporateEntity, Long> {

    Optional<CorporateEntity> findByMemberEntity(MemberEntity memberEntity);
}
