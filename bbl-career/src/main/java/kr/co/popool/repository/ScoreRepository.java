package kr.co.popool.repository;

import kr.co.popool.domain.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreEntity,Long> {

    Optional<ScoreEntity> findById(Long id);
    @Query(value =
            "SELECT * " +
                    "FROM tbl_score " +
                    "WHERE career_id = :identity",
            nativeQuery = true)
    List<ScoreEntity> findByCareerId(Long identity);
}
