package kr.co.popool.service;


import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.entity.CareerEntity;

import java.util.List;
import java.util.Optional;

public interface CareerService {

  List<CareerDto.CAREERINFO> showAll();

  CareerDto.CAREERINFO show(String memberIdentity);

  void newCareer(CareerDto.CREATE newCareer);

  void update(CareerDto.UPDATE careerDto);


}
