package kr.co.popool.repository;

import kr.co.popool.domain.entity.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<CareerEntity, Long> {

  Optional<CareerEntity> findByMemberIdentity(String memberIdentity);


}