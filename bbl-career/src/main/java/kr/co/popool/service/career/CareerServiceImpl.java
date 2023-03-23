package kr.co.popool.service.career;


import java.util.ArrayList;
import java.util.List;

import kr.co.popool.bblcommon.error.exception.DeletedCareerException;
import kr.co.popool.bblcommon.error.exception.NotFoundCareerException;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerDto;
import kr.co.popool.domain.dto.career.CareerDto.CAREERINFO;
import kr.co.popool.domain.dto.career.CareerDto.DELETE;
import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.dto.grade.QueryGradeDto.GRADEDETAIL;
import kr.co.popool.domain.entity.Career;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.domain.entity.GradeEntity;
import kr.co.popool.domain.entity.ScoreEntity;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.service.score.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.popool.domain.entity.Career.DELETED;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CareerServiceImpl implements CareerService {

	private final CareerRepository careerRepository;
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

	@Transactional
	public Career showCareer(String memberId) {
		return findCareer(memberId);
	}

	private Career findCareer(String memberId) {
		Career career = careerRepository.findByMemberId(memberId)
			.orElseThrow(NotFoundCareerException::new);

		if (career.isDeleted(DELETED)) {
			throw new DeletedCareerException();
		}
		return career;
	}

	/**
	 * 인사 내역 등록
	 *
	 * @param request : 새로운 인사 내역을 담은 요청
	 * @return : void
	 * @Exception DuplicatedException : 인사 내역이 이미 등록된 경우
	 */

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


	/**
	 * 매핑된 Grade 존재 여부 확인 후 Entity to DTO
	 *
	 * @param careerEntity : 인사 Entity
	 * @return CAREERINFO : 인사 정보 DTO
	 */
	@Override
	public CAREERINFO checkGrade(Career careerEntity) {

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
