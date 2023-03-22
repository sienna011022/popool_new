package kr.co.popool.service.career;

import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerDto.CAREERINFO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CareerService {


  List<CAREERINFO> showAll();

  @Transactional
  Career createCareer(CareerCreateRequest request);
}

