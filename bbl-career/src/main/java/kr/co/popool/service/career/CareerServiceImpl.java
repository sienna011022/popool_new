package kr.co.popool.service.career;


import kr.co.popool.bblcommon.error.exception.NotFoundCareerException;
import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.entity.Career;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.service.score.ScoreService;
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
    private final ScoreService scoreService;

    @Override
    @Transactional(readOnly = true)
    public List<Career> showAll() {
        return careerRepository.findAll();
    }

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

    @Transactional
    @Override
    public Career updateCareer(String memberId, CareerUpdateRequest request) {
        Career career = findCareer(memberId);
        career.updateCareer(request);
        return careerRepository.save(career);
    }

    @Transactional
    public void deleteCareer(String memberId) {
        careerRepository.deleteByMemberId(memberId);
    }
//    /**
//     * 매핑된 Grade 존재 여부 확인 후 Entity to DTO
//     *
//     * @param careerEntity : 인사 Entity
//     * @return CAREERINFO : 인사 정보 DTO
//     */
//
//    /**
//     * 최초 평가 등록 시, 인사 - 등급 매핑
//     *
//     * @param memberIdentity , gradeEntity : 인사 아이디 , 매핑할 등급 entity
//     * @return void
//     */
//
//
//    /**
//     * 인사 객체와 매핑된 성적 entity 가져오기
//     *
//     * @param memberIdentity : 인사 아이디
//     * @return List<ScoreEntity>
//     */
//
//
//    /**
//     * 평가 등록 시, 인사 - 등급 매핑
//     *
//     * @param memberIdentity , List<ScoreEntity> , gradeEntity: 인사 아이디 , 성적 entity list, 매핑할 등급
//     *                       entity
//     * @return void
//     */


    private Career findCareer(String memberId) {
        return careerRepository.findByMemberId(memberId)
            .orElseThrow(NotFoundCareerException::new);
    }

}
