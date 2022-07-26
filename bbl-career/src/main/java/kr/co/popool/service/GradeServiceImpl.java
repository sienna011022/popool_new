package kr.co.popool.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.dto.GradeDto;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.domain.entity.ScoreEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import kr.co.popool.repository.CareerRepository;
import kr.co.popool.repository.GradeRepository;
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
public class GradeServiceImpl implements GradeService{

    private final GradeRepository gradeRepository;
    private final CareerRepository careerRepository;

    @Override
    public void updateGrade(ScoreDto.SCOREINFO newScore) {

        CareerEntity careerEntity = careerRepository.findByMemberIdentity(newScore.getMemberIdentity())
                .orElseThrow(() -> new BadRequestException("아이디에 해당하는 인사 내역이 존재하지 않습니다"));

        GradeEntity gradeEntity = gradeRepository.findByCareerEntity(careerEntity);
        int total_score = gradeEntity.getTotal_score();

        total_score += newScore.getAttendance();
        total_score += newScore.getCooperative();
        total_score += newScore.getPositiveness();
        total_score += newScore.getSincerity();
        total_score += newScore.getTechnical();

        int total_member = gradeEntity.getTotal_member() + 1;

        int average = total_score / total_member;

        ScoreGrade finalGrade;
        //등급 산정
        if (20 < average) {
            finalGrade = ScoreGrade.GOLD;

        } else if (16 < average) {
            finalGrade = ScoreGrade.SILVER;

        } else if (10 < average) {
            finalGrade = ScoreGrade.BRONZE;

        } else {
            finalGrade = ScoreGrade.BLACK;
        }

        GradeDto.UPDATEGRADE updateGradeDto = GradeDto.UPDATEGRADE.builder()
                .grade(finalGrade)
                .total_member(total_member)
                .total_score(total_score)
                .average(average)
                .build();

        gradeEntity.updateGrade(updateGradeDto);

        gradeRepository.save(gradeEntity);

        //TODO:최종 인사 내역에 등급 반영
        //TODO:에러 처리



    }
}
