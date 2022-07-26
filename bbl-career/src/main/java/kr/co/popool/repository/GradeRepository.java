package kr.co.popool.repository;


import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.domain.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GradeRepository extends JpaRepository<GradeEntity,Long> {



     GradeEntity findByCareerEntity (CareerEntity careerEntity);
}
