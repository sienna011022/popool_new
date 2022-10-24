package kr.co.popool.service.career;


import java.util.ArrayList;
import java.util.List;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.domain.dto.career.CareerDto;
import kr.co.popool.domain.dto.career.CareerDto.CAREERINFO;
import kr.co.popool.domain.dto.career.CareerDto.DELETE;
import kr.co.popool.domain.dto.career.FileDto;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.domain.entity.ScoreEntity;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.service.score.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CareerServiceImpl implements CareerService {

  private final CareerRepository careerRepository;

//  private final FileUploadService fileUploadService;

  private final ScoreService scoreService;


  /**
   * 전체 인사 내역 조회
   *
   * @return : 인사 내역을 담은 DTO 객체 리스트
   */

  @Override
  public List<CAREERINFO> showAll() {

    List<CAREERINFO> CareerDtoList = new ArrayList<>();
    for (CareerEntity list : careerRepository.findAll()) {
      CareerDtoList.add(checkGrade(list));
    }
    return CareerDtoList;

  }

  /**
   * 개인 인사 내역 조회
   *
   * @param memberIdentity : 로그인 시 사용하는 멤버 아이디
   * @return : 인사 내역을 담은 DTO 객체
   * @Exception NotFoundException : 아이디에 해당하는 인사 내역이 없을 경우
   */

  public CAREERINFO show(String memberIdentity) {

    CareerEntity careerEntity = findCareerEntity(memberIdentity);
    return checkGrade(careerEntity);


  }


  /**
   * 인사 내역에 첨부된 파일 조회
   *
   * @param memberIdentity : 인사 아이디
   * @return : ResponseEntity<byte[]>
   *
   */

//  public ResponseEntity<byte[]> showFile(String memberIdentity) {
//
//    CareerEntity careerEntity = findCareerEntity(memberIdentity);
//    return fileUploadService.getFile(careerEntity.getFilePath());
//
//  }
  /**
   * 인사 내역 등록
   *
   * @param newCareer : 새로운 인사 내역을 담은 DTO객체
   * @return : void
   * @Exception DuplicatedException : 인사 내역이 이미 등록된 경우
   */

//  @Override
//  @Transactional
//  public void newCareer(CareerDto.CREATE newCareer, MultipartFile multipartFile) {
//
//    CareerEntity careerEntity = CareerEntity.of(newCareer);
//    if (multipartFile != null) {
//
//      FileDto filePath = fileUploadService.save(multipartFile);
//      careerEntity.updateFile(filePath.getPath());
//      careerRepository.save(careerEntity);
//
//    }
//    careerRepository.save(careerEntity);
//
//  }

  @Override
  @Transactional
  public void update(CareerDto.UPDATE careerDto) {

    CareerEntity careerEntity = findCareerEntity(careerDto.getMemberIdentity());

    careerEntity.updateCareer(careerDto);
    careerRepository.save(careerEntity);

  }

  /**
   * 매핑된 Grade 존재 여부 확인 후 Entity to DTO
   *
   * @param careerEntity : 인사 Entity
   * @return CAREERINFO : 인사 정보 DTO
   */
  @Override
  public CAREERINFO checkGrade(CareerEntity careerEntity) {

    try {
      return CareerDto.of(careerEntity);
    } catch (NullPointerException e) {
      return CareerDto.NoneGradeDto(careerEntity);
    }

  }

  /**
   * 최초 평가 등록 시, 인사 - 등급 매핑
   *
   * @param memberIdentity , gradeEntity : 인사 아이디 , 매핑할 등급 entity
   * @return void
   */
  @Override
  @Transactional
  public void saveGrade(String memberIdentity, GradeEntity gradeEntity) {

    CareerEntity careerEntity = findCareerEntity(memberIdentity);
    careerEntity.createGrade(gradeEntity);
    careerRepository.save(careerEntity);

  }

  /**
   * 인사 객체와 매핑된 성적 entity 가져오기
   *
   * @param memberIdentity : 인사 아이디
   * @return List<ScoreEntity>
   */
  @Override
  public List<ScoreEntity> findScoreList(String memberIdentity) {
    return scoreService.findAllScore(memberIdentity);

  }

  @Override
  public void delete(DELETE careerDto) {

    CareerEntity careerEntity = findCareerEntity(careerDto.getMemberIdentity());
    careerEntity.deleted();
    careerRepository.save(careerEntity);

  }

  /**
   * 평가 등록 시, 인사 - 등급 매핑
   *
   * @param memberIdentity , List<ScoreEntity> , gradeEntity: 인사 아이디 , 성적 entity list, 매핑할 등급
   *                       entity
   * @return void
   */
  @Override
  public void updateGrade(String memberIdentity, GRADEDETAIL gradeDto) {

    CareerEntity careerEntity = findCareerEntity(memberIdentity);
    careerEntity.getGradeEntity().updateGrade(findScoreList(memberIdentity), gradeDto);
    careerRepository.save(careerEntity);

  }

  @Override
  public boolean checkDelete(CareerEntity careerEntity) {
    if (careerEntity.getDel_yn().equals("N")) {
      return false;
    }
    return true;
  }

  /**
   * 인사 아이디로 Entity 반환
   *
   * @param memberIdentity : 인사 아이디
   * @return CareerEntity : 인사 Entity
   * @Exception NotFoundException : 인사 내역이 존재하지 않는 경우
   */
  @Override
  public CareerEntity findCareerEntity(String memberIdentity) {

    CareerEntity careerEntity = careerRepository.findByMemberIdentity(memberIdentity)
        .orElseThrow(() -> new NotFoundException(memberIdentity));

    try {
      checkDelete(careerEntity);
    } catch (Exception e) {
      throw e;
    }
    return careerEntity;
  }

}