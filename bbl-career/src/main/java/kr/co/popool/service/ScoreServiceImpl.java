package kr.co.popool.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
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

  /**
   * 평가 조회
   *
   * @param memberIdentity : 인사 아이디
   * @return : List<ScoreDto.SHOWSCORE> DTO 객체 리스트
   * @Exception NotFoundException : 아이디에 해당하는 평가 내역을 찾지 못함.
   */

  public List<ScoreDto.SHOWSCORE> showScores(String memberIdentity) {

    CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
        .orElseThrow(() -> new NotFoundException(memberIdentity));

    List<ScoreEntity> scoreEntityList = scoreRepository.findByCareerEntity(careerEntity);



    List<ScoreDto.SHOWSCORE> ScoreList = new ArrayList<>();

    for (ScoreEntity list : scoreEntityList) {
      ScoreDto.SHOWSCORE score = ScoreDto.of(list);
      ScoreList.add(score);
    }

    return ScoreList;
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

    CareerEntity careerEntity = careerRepository.findByMemberIdentity(newScore.getMemberIdentity())
        .orElseThrow(() -> new BadRequestException("평가 생성 실패 - 아이디에 해당하는 인사 내역이 존재하지 않습니다"));

    ScoreEntity scoreEntity = ScoreEntity.of(newScore, careerEntity);

    scoreRepository.save(scoreEntity);

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


}



