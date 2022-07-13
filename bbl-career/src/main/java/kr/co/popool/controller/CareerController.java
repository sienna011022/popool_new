package kr.co.popool.controller;

import kr.co.popool.domain.entity.CareerEntity;
import kr.co.popool.service.CareerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{id}")
    public Optional<CareerEntity> index(@PathVariable Long id){
        return careerService.show(id);
    }


}

