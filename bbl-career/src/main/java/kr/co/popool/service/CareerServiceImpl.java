package kr.co.popool.service;
import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j //로깅을 위함
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;


    @Override
    public List<CareerEntity> showAll() {
        return careerRepository.findAll();
    }

    public Optional<CareerEntity> show(Long id) {
        return careerRepository.findById(id);
    }

    @Override
    @Transactional
    public CareerEntity newCareer(CareerDto.CREATE newCareer) {

        CareerEntity careerEntity = CareerEntity.builder()
                .identity(newCareer.getIdentity())
                .name(newCareer.getName())
                .period(newCareer.getPeriod())
                .historyId(newCareer.getHistoryId())
                .build();

        if (careerEntity.getIdentity() == null) {
            throw new BadRequestException("본인의 아이디를 입력해주세요");
        }

            try {
                CareerEntity created = careerRepository.save(careerEntity);
                return created;
            }
            //TODO : 예외 처리 세분화
            catch(DataIntegrityViolationException e){
                throw new BadRequestException("해당 인사 내역은 이미 등록 되어 있습니다");
            }

    }
    @Override
    @Transactional
    public CareerEntity update(Long id, CareerDto.UPDATE careerDto) {
        //TODO:수정 예외 처리

        log.info("career id:{},career:{}",id,careerDto.toString());

        Optional<CareerEntity> careerEntity = careerRepository.findById(id);
        careerEntity.get().updateCareer(careerDto);

        CareerEntity updated = careerRepository.save(careerEntity.get());
        return updated;
    }




}