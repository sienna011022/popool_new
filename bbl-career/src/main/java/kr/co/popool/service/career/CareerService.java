package kr.co.popool.service.career;

import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.entity.Career;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CareerService {
  List<Career> showAll();

  @Transactional(readOnly = true)
  Career showCareer(String memberId);

  Career createCareer(CareerCreateRequest request);

  Career updateCareer(String memberId, CareerUpdateRequest request);

  @Transactional
  void deleteCareer(String memberId);
}

