package kr.co.popool.repository.grade;


import kr.co.popool.domain.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GradeRepository extends JpaRepository<GradeEntity, Long> , GradeRepositoryCustom {


}
