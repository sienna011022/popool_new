package kr.co.popool.service;
import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            throw new BadRequestException("이미 인사 내역이 등록된 아이디입니다.");
        }
    }

}