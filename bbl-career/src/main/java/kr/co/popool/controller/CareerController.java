package kr.co.popool.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.domain.dto.CareerDto;
import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.service.CareerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j //로깅을 위함
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
@RequestMapping(value="/careers")
public class CareerController {

    private final CareerServiceImpl careerService;

    @ApiOperation("전체 인사 내역 조회")
    @GetMapping()
    public ResponseFormat showAll(){
        List<CareerDto.CAREERINFO> careerInfoList = careerService.showAll();
        return ResponseFormat.ok(careerInfoList);
    }

    @ApiOperation("개인 인사 내역 조회")
    @GetMapping("/{memberIdentity}")
    public ResponseFormat show(@PathVariable String memberIdentity){
        CareerDto.CAREERINFO careerInfo = careerService.show(memberIdentity);
        return ResponseFormat.ok(careerInfo);
    }

    @ApiOperation("개인 인사 내역 등록")
    @PostMapping()
    public ResponseFormat createCareer(@RequestBody @Valid CareerDto.CREATE careerDto){
       careerService.newCareer(careerDto);
        return ResponseFormat.ok();

    }

    @ApiOperation("개인 인사 내역 수정")
    @PatchMapping("/{memberIdentity}")
    public ResponseFormat updateCareer(@PathVariable String memberIdentity, @RequestBody CareerDto.UPDATE careerDto){
        careerService.update(memberIdentity,careerDto);
        return ResponseFormat.ok();

    }

}

