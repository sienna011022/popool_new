package kr.co.popool.service;

import java.util.ArrayList;
import java.util.List;
import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j //로깅을 위함
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

  private final CareerRepository careerRepository;

  @Override
  public List<CareerDto.CAREERINFO> showAll() {
    List<CareerEntity> careerEntityList = careerRepository.findAll();
    List<CareerDto.CAREERINFO> CareerDtoList = new ArrayList<>();


    //TODO: 등급 내역 null인 경우 처리하기

    for (CareerEntity list : careerEntityList) {
      CareerDto.CAREERINFO careerInfo = CareerDto.CAREERINFO.builder()
          .memberIdentity(list.getMemberIdentity())
          .name(list.getName())
          .grade(String.valueOf(list.getGradeEntity().getGrade()))
          .period(list.getPeriod())
          .context(list.getContext())
          .historyId(list.getHistoryId())
          .build();
      CareerDtoList.add(careerInfo);
    }

    return CareerDtoList;
  }


  public CareerDto.CAREERINFO show(String memberIdentity) {
    CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
        .orElseThrow(() -> new BadRequestException("아이디에 해당하는 인사 내역이 존재하지 않습니다"));

    return CareerDto.CAREERINFO.builder()
        .memberIdentity(careerEntity.getMemberIdentity())
        .name(careerEntity.getName())
        .grade(String.valueOf(careerEntity.getGradeEntity().getGrade()))
        .period(careerEntity.getPeriod())
        .context(careerEntity.getContext())
        .historyId(careerEntity.getHistoryId())
        .build();

  }

  @Override
  @Transactional
  public void newCareer(CareerDto.CREATE newCareer) {

    CareerEntity careerEntity = CareerEntity.builder()
        .memberIdentity(newCareer.getMemberIdentity())
        .name(newCareer.getName())
        .context(newCareer.getContext())
        .period(newCareer.getPeriod())
        .historyId(newCareer.getHistoryId())
        .build();

    try {
      careerRepository.save(careerEntity);
    }
    //TODO : 예외 처리 세분화
    catch (DataIntegrityViolationException e) {
      throw new BadRequestException("해당 인사 내역은 이미 등록 되어 있습니다");
    }

  }

  @Override
  @Transactional
  public void update(String memberIdentity, CareerDto.UPDATE careerDto) {
    //TODO:수정 예외 처리

    log.info("member id:{},career:{}", memberIdentity, careerDto.toString());

    CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
        .orElseThrow(() -> new BadRequestException("아이디에 해당하는 인사 내역이 존재하지 않습니다"));
    careerEntity.updateCareer(careerDto);
    careerRepository.save(careerEntity);

  }


}