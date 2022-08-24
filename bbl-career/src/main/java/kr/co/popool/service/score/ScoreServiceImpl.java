package kr.co.popool.service.score;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.domain.dto.score.QueryScoreDto.SHOWSCORE;
import kr.co.popool.domain.dto.score.ScoreDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.ScoreEntity;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.repository.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j //로깅을 위함
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

  private final ScoreRepository scoreRepository;

  private final CareerRepository careerRepository;

  /**
   * 평가 조회
   *
   * @param memberIdentity : 인사 아이디
   * @return : Optional<List<SHOWSCORE>> Q_DTO 객체 리스트
   * @Exception NotFoundException : 아이디에 해당하는 평가 내역을 찾지 못함.
   */
  @Override
  public List<SHOWSCORE> showScores(String memberIdentity) {

    return scoreRepository.showAllScores(memberIdentity)
        .orElseThrow(() -> new NotFoundException("memberIdentity"));
  }

  /**
   * 평가 등록
   *
   * @param newScore : 평가 DTO
   * @return : void
   * @Exception BadRequestException : 평가 생성 실패 - 아이디에 해당하는 인사 내역이 존재하지 않습니다
   */
  @Override
  @Transactional
  public void createScore(ScoreDto.SCOREINFO newScore) {

    //TODO : careerRepository 주입 안받고 할 수 있는 방법
    CareerEntity careerEntity = careerRepository.findByMemberIdentity(newScore.getMemberIdentity())
        .orElseThrow(() -> new BadRequestException("인사 내역이 존재하지 않습니다"));

    scoreRepository.save(ScoreEntity.of(newScore, careerEntity));

  }

  /**
   * 평가 수정
   *
   * @param updateScoreDto : 평가 수정 DTO
   * @return : void
   * @Exception BadRequestException : 평가 수정 실패 - 아이디에 해당하는 인사 내역이 존재하지 않습니다
   */
  @Override
  @Transactional
  public void updateScore(ScoreDto.UPDATE updateScoreDto) {

    ScoreEntity scoreEntity = scoreRepository.findByEvaluatorIdentity(
            updateScoreDto.getEvaluatorIdentity())
        .orElseThrow(() -> new BadRequestException("평가 수정 실패 - 아이디에 해당하는 인사 내역이 존재하지 않습니다"));

    scoreEntity.updateScore(updateScoreDto);

    scoreRepository.save(scoreEntity);

  }

  /**
   * 모든 평가 조회
   *
   * @param memberIdentity : 인사 아이디
   * @return : Optional<List<ScoreEntity>> scoreEntity List
   * @Exception NotFoundException : 아이디에 해당하는 평가 내역을 찾지 못함.
   */
  @Override
  public List<ScoreEntity> findAllScore(String memberIdentity) {

    return scoreRepository.getAllScoreList(memberIdentity)
        .orElseThrow(() -> new NotFoundException("아이디에 해당하는 평가 내역이 존재하지 않습니다"));


  }


}



