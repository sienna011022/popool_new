package kr.co.popool.service.career;

import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerDto.CAREERINFO;
import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.entity.Career;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CareerService {


  @Transactional
  Career createCareer(CareerCreateRequest request);

  @Transactional
  Career updateCareer(String memberId, CareerUpdateRequest request);
}

