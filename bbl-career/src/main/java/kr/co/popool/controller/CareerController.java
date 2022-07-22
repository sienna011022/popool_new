package kr.co.popool.controller;

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


    //인사 조회 - GET
    @GetMapping()
    public List<CareerEntity> index(){
        return careerService.showAll();
    }

    //개인 인사 조회 - GET
    @GetMapping("/{memberIdentity}")
    public Optional<CareerEntity> index(@PathVariable String memberIdentity){
        return careerService.show(memberIdentity);
    }

    //인사 등록 - POST
    @PostMapping()
    public ResponseFormat createCareer(@RequestBody @Valid CareerDto.CREATE careerDto){
        CareerEntity created = careerService.newCareer(careerDto);
        return(created != null) ?
                ResponseFormat.ok(created) :
                ResponseFormat.fail("등록 실패");
    }

    //인사 수정 - PATCH
    @PatchMapping("/{memberIdentity}")
    public ResponseFormat updateCareer(@PathVariable String memberIdentity, @RequestBody CareerDto.UPDATE careerDto){
        CareerEntity updated = careerService.update(memberIdentity,careerDto);
        return (updated != null) ?  ResponseFormat.ok(updated) :
                ResponseFormat.fail("수정 실패");
    }

}

