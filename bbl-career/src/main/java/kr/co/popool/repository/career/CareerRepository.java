package kr.co.popool.repository.career;

import kr.co.popool.domain.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {

  Optional<Career> findByMemberIdentity(String memberIdentity);


}
