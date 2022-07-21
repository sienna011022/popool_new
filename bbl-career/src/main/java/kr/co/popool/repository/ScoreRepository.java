package kr.co.popool.repository;

import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreEntity,Long> {

    Optional<ScoreEntity> findById(Long id);

    List<ScoreEntity> findByCareerEntity(CareerEntity careerEntity);

    Optional<ScoreEntity> findByEvaluatorIdentity(String evaluatorIdentity);
}
