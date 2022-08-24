package kr.co.popool.repository.score;

import kr.co.popool.domain.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> , ScoreRepositoryCustom {

  Optional<ScoreEntity> findById(Long id);

  Optional<ScoreEntity> findByEvaluatorIdentity(String evaluatorIdentity);


}
