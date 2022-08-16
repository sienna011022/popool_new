package kr.co.popool.service;

import static kr.co.popool.bblcommon.error.model.ErrorCode.DUPLICATED_MEMBERIDENTITY;

import java.util.ArrayList;
import java.util.List;
import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

  private final CareerRepository careerRepository;

  /**
   * 전체 인사 내역 조회
   *
   * @return : 인사 내역을 담은 DTO 객체 리스트
   */

  @Override
  public List<CareerDto.CAREERINFO> showAll() {
    //TODO: 등급 내역 null인 경우 처리하기
    List<CareerEntity> careerEntityList = careerRepository.findAll();
    List<CareerDto.CAREERINFO> CareerDtoList = new ArrayList<>();

    for (CareerEntity list : careerEntityList) {
      CareerDto.CAREERINFO careerDto = CareerDto.of(list);
      CareerDtoList.add(careerDto);
    }

    return CareerDtoList;
  }

  /**
   * 개인 인사 내역 조회
   *
   * @param memberIdentity : 로그인 시 사용하는 멤버 아이디
   * @return : 인사 내역을 담은 DTO 객체
   * @Exception NotFoundException : 아이디에 해당하는 인사 내역이 없을 경우
   */

  public CareerDto.CAREERINFO show(String memberIdentity) {
    CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
        .orElseThrow(() -> new NotFoundException(memberIdentity));

    return CareerDto.of(careerEntity);
  }


  /**
   * 인사 내역 등록
   *
   * @param newCareer : 새로운 인사 내역을 담은 DTO객체
   * @return : void
   * @Exception DuplicatedException : 인사 내역이 이미 등록된 경우
   */

  @Override
  @Transactional
  //TODO : 예외 처리 세분화
  public void newCareer(CareerDto.CREATE newCareer) {

    CareerEntity careerEntity = CareerEntity.of(newCareer);

    try {
      careerRepository.save(careerEntity);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicatedException(DUPLICATED_MEMBERIDENTITY);
    }

  }

  /**
   * 개인 인사 내역 수정
   *
   * @param careerDto : 수정한 정보를 담은 DTO 객체
   * @return : void
   * @Exception NotFoundException : 아이디에 해당하는 인사 내역이 없을 경우
   */

  @Override
  @Transactional
  //TODO:수정 예외 처리
  public void update(CareerDto.UPDATE careerDto) {
    CareerEntity careerEntity = careerRepository.findByMemberIdentity(careerDto.getMemberIdentity())
        .orElseThrow(() -> new NotFoundException(careerDto.getMemberIdentity()));
    careerEntity.updateCareer(careerDto);
    careerRepository.save(careerEntity);

  }
}