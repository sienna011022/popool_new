package kr.co.popool.service;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

}