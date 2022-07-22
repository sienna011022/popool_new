package kr.co.popool.service;



import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.entity.CareerEntity;

import java.util.List;
import java.util.Optional;

public interface CareerService {

    List<CareerEntity> showAll();

    Optional<CareerEntity> show(String memberIdentity);

    CareerEntity newCareer(CareerDto.CREATE newCareer);

    CareerEntity update(String memberIdentity, CareerDto.UPDATE careerDto);


}
