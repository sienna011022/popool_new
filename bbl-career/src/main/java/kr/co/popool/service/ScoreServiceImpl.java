package kr.co.popool.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.ScoreEntity;
import kr.co.popool.repository.CareerRepository;
import kr.co.popool.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j //로깅을 위함
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final CareerRepository careerRepository;
    private final ScoreRepository scoreRepository;

    public List<ScoreDto.SCOREINFO> showScores(Long identity) {

        List<ScoreEntity> scoreEntityList = scoreRepository.findByCareerId(identity);

        if (scoreEntityList.isEmpty()) {
            throw new BadRequestException("평가 조회 실패 - 아이디에 해당하는 평가 내역이 존재하지 않습니다");
        }

        List<ScoreDto.SCOREINFO> ScoreList = new ArrayList<>();

        for (ScoreEntity list : scoreEntityList) {
            ScoreDto.SCOREINFO score = ScoreDto.SCOREINFO.builder()
                    .evaluatorId(list.getEvaluatorIdentity())
                    .attendance(list.getAttendance())
                    .sincerity(list.getSincerity())
                    .positiveness(list.getSincerity())
                    .technical(list.getTechnical())
                    .cooperative(list.getCooperative())
                    .build();
            ScoreList.add(score);
        }

        return ScoreList;
}

    @Override
    public void createScore(Long id, ScoreDto.SCOREINFO newScore) {

        Optional<CareerEntity> careerEntity = careerRepository.findById(id);
        if (!careerEntity.isPresent()) {
            throw new BadRequestException("평가 생성 실패 - 아이디에 해당하는 인사 내역이 존재하지 않습니다");
        }

        ScoreEntity scoreEntity = ScoreEntity.builder()
                .careerEntity(careerEntity.get())
                .evaluatorIdentity(newScore.getEvaluatorId())
                .attendance(newScore.getAttendance())
                .sincerity(newScore.getSincerity())
                .positiveness(newScore.getSincerity())
                .technical(newScore.getTechnical())
                .cooperative(newScore.getCooperative())
                .build();

        scoreRepository.save(scoreEntity);


    }

    @Override
    public void updateScore(Long scoreId, ScoreDto.UPDATE updateScoreDto) {

        log.info("등록한 평가의 아이디:{},career:{}",scoreId,updateScoreDto.toString());

        Optional<ScoreEntity> scoreEntity = scoreRepository.findById(scoreId);
        scoreEntity.get().updateScore(updateScoreDto);

        scoreRepository.save(scoreEntity.get());

    }

}



