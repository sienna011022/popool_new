package kr.co.popool.service.career;

import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.entity.Career;
import kr.co.popool.domain.entity.Grade;

import java.util.List;

public interface CareerService {
    List<Career> showAll();

    Career showCareer(String memberId);

    Career createCareer(CareerCreateRequest request);

    Career updateCareer(String memberId, CareerUpdateRequest request);

    void deleteCareer(String memberId);

    Grade getGrade(String memberId);
}

