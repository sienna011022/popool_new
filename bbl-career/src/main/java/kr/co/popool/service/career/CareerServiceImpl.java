package kr.co.popool.service.career;


import kr.co.popool.bblcommon.error.exception.NotFoundCareerException;
import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.dto.score.ScoreAverage;
import kr.co.popool.domain.entity.Career;
import kr.co.popool.domain.entity.Grade;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.repository.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;
    private final ScoreRepository scoreRepository;

    @Override
    @Transactional
    public List<Career> showAll() {
        return careerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Career showCareer(String memberId) {
        return findCareer(memberId);
    }

    @Override
    @Transactional
    public Career createCareer(CareerCreateRequest request) {
        Career requestCareer = request.toCareer();
        return careerRepository.save(requestCareer);
    }

    @Override
    @Transactional
    public Career updateCareer(String memberId, CareerUpdateRequest request) {
        Career career = findCareer(memberId);
        career.updateCareer(request);
        return careerRepository.save(career);
    }

    @Override
    @Transactional
    public void deleteCareer(String memberId) {
        careerRepository.deleteByMemberId(memberId);
    }

    @Override
    public Grade getGrade(String memberId) {
        return findCareer(memberId).getGrade();
    }


    private Career findCareer(String memberId) {
        return careerRepository.findByMemberId(memberId)
            .orElseThrow(NotFoundCareerException::new);
    }

}
