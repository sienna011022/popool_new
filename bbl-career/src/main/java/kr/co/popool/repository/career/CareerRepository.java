package kr.co.popool.repository.career;

import kr.co.popool.domain.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<Career> findByMemberIdentity(String memberIdentity);


    Optional<Career> findByMemberId(String memberId);

    void deleteByMemberId(String memberId);
}
