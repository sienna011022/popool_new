package kr.co.popool.repository.grade;

import java.util.Optional;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GETVALUE;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;

public interface GradeRepositoryCustom {

  GETVALUE getValue(String memberIdentity) ;

  Optional<GRADEDETAIL> makeGradeDto(String memberIdentity, GETVALUE valueDto);

  Optional<GRADEDETAIL> showGradeDetail(String memberIdentity);

}
