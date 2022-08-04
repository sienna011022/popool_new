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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j //로깅을 위함
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

  private final CareerRepository careerRepository;
  private final ScoreRepository scoreRepository;

  public List<ScoreDto.SHOWSCORE> showScores(String memberIdentity) {

    CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
        .orElseThrow(() -> new BadRequestException("평가 조회 실패 - 아이디에 해당하는 평가 내역이 존재하지 않습니다"));
    ;

    List<ScoreEntity> scoreEntityList = scoreRepository.findByCareerEntity(careerEntity);

    List<ScoreDto.SHOWSCORE> ScoreList = new ArrayList<>();

    for (ScoreEntity list : scoreEntityList) {
      ScoreDto.SHOWSCORE score = ScoreDto.SHOWSCORE.builder()
          .evaluatorIdentity(list.getEvaluatorIdentity())
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
  @Transactional
  public void createScore(ScoreDto.SCOREINFO newScore) {

    CareerEntity careerEntity = careerRepository.findByMemberIdentity(newScore.getMemberIdentity())
        .orElseThrow(() -> new BadRequestException("평가 생성 실패 - 아이디에 해당하는 인사 내역이 존재하지 않습니다"));

    ScoreEntity scoreEntity = ScoreEntity.builder()
        .careerEntity(careerEntity)
        .evaluatorIdentity(newScore.getEvaluatorIdentity())
        .attendance(newScore.getAttendance())
        .sincerity(newScore.getSincerity())
        .positiveness(newScore.getSincerity())
        .technical(newScore.getTechnical())
        .cooperative(newScore.getCooperative())
        .build();

    scoreRepository.save(scoreEntity);

  }

  @Override
  @Transactional
  public void updateScore(ScoreDto.UPDATE updateScoreDto) {

    log.info("등록한 평가자의 아이디:{},career:{}", updateScoreDto.getEvaluatorIdentity(),
        updateScoreDto.toString());

    ScoreEntity scoreEntity = scoreRepository.findByEvaluatorIdentity(
            updateScoreDto.getEvaluatorIdentity())
        .orElseThrow(() -> new BadRequestException("평가 생성 실패 - 아이디에 해당하는 인사 내역이 존재하지 않습니다"));
    scoreEntity.updateScore(updateScoreDto);

    scoreRepository.save(scoreEntity);

  }


}



