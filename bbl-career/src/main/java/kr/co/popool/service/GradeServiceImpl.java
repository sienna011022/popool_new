package kr.co.popool.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.domain.dto.GradeDto;
import kr.co.popool.domain.dto.ScoreDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.domain.shared.enums.ScoreGrade;
import kr.co.popool.repository.CareerRepository;
import kr.co.popool.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j //로깅을 위함
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService{

    public static final int DEFAULT_SIZE = 1;

    private final GradeRepository gradeRepository;
    private final CareerRepository careerRepository;

    @Override
    public void updateGrade(ScoreDto.SCOREINFO newScore) {



        CareerEntity careerEntity = careerRepository.findByMemberIdentity(newScore.getMemberIdentity())
                .orElseThrow(() -> new BadRequestException("아이디에 해당하는 인사 내역이 존재하지 않습니다"));

        GradeEntity gradeEntity = gradeRepository.findByCareerEntity(careerEntity)
                .orElseThrow(() -> new BadRequestException("아이디에 해당하는 평가 내역이 존재하지 않습니다"));
        int totalScore = gradeEntity.getTotalScore();

        totalScore += newScore.getAttendance();
        totalScore += newScore.getCooperative();
        totalScore += newScore.getPositiveness();
        totalScore += newScore.getSincerity();
        totalScore += newScore.getTechnical();

        int totalMember = gradeEntity.getTotalMember() + DEFAULT_SIZE;

        int average = totalScore / totalMember;

        ScoreGrade finalGrade;

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
                .totalMember(totalMember)
                .totalScore(totalScore)
                .average(average)
                .build();

        gradeEntity.updateGrade(updateGradeDto);
        gradeRepository.save(gradeEntity);

        //평가 내역과 인사 내역 mapping
        careerEntity.updateGrade(gradeEntity);
        careerRepository.save(careerEntity);

        //TODO:에러 처리



    }

    @Override
    public GradeDto.GRADEINFO showGradeInfo(String memberIdentity) {
        CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
                .orElseThrow(() -> new BadRequestException("아이디에 해당하는 인사 내역이 존재하지 않습니다"));
        
        GradeEntity gradeEntity = gradeRepository.findByCareerEntity(careerEntity)
                .orElseThrow(() -> new BadRequestException("아이디에 해당하는 등급 내역이 존재하지 않습니다"));

        GradeDto.GRADEINFO gradeInfo = GradeDto.GRADEINFO.builder()
                .average(gradeEntity.getAverage())
                .grade(gradeEntity.getGrade())
                .totalMember(gradeEntity.getTotalMember())
                .totalScore(gradeEntity.getTotalScore())
                .build();

        return gradeInfo;

    }

    @Override
    public GradeDto.ONLYGRADE showGrade(String memberIdentity) {
        CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
                .orElseThrow(() -> new BadRequestException("아이디에 해당하는 인사 내역이 존재하지 않습니다"));

        GradeEntity gradeEntity = gradeRepository.findByCareerEntity(careerEntity)
                .orElseThrow(() -> new BadRequestException("아이디에 해당하는 등급이 존재하지 않습니다"));

        GradeDto.ONLYGRADE onlyGrade = GradeDto.ONLYGRADE.builder()
                .grade(gradeEntity.getGrade())
                .build();

        return onlyGrade;

}
}
