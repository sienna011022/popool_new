package kr.co.popool.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.career.CareerCreateRequest;
import kr.co.popool.domain.dto.career.CareerResponse;
import kr.co.popool.service.career.CareerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/careers")
public class CareerController {
	private final CareerServiceImpl careerService;

	@ApiOperation("개인 인사 내역 조회")
	@GetMapping("/{member_id}")
	public ResponseEntity showCareer(@PathVariable(value = "member_id") String memberId) {
		CareerResponse response = CareerResponse.of(careerService.showCareer(memberId));
		return ResponseEntity.ok(response);
	}
	@ApiOperation("개인 인사 내역 등록")
	@PostMapping
	public ResponseEntity newCareer(@RequestBody CareerCreateRequest request) {
		CareerResponse response = CareerResponse.of(careerService.createCareer(request));
		return new ResponseEntity(response, HttpStatus.CREATED);
	}
	@ApiOperation("전체 인사 내역 조회")
	@GetMapping("/all")
	public List<CareerDto.CAREERINFO> showAll() {
		return careerService.showAll();
	}

	@ApiOperation("개인 인사 내역 수정")
	@PatchMapping("/{memberIdentity}")
	public ResponseFormat updateCareer(@RequestBody CareerDto.UPDATE careerDto) {
		careerService.update(careerDto);
		return ResponseFormat.ok();

	}

	@ApiOperation("개인 인사 내역 삭제")
	@DeleteMapping("/delete")
	public ResponseFormat delete(@RequestBody CareerDto.DELETE careerDto){
		careerService.delete(careerDto);
		return ResponseFormat.ok();
	}

}
