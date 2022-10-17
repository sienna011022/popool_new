package kr.co.popool.bblmember.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final Environment environment;

    @GetMapping("/config")
    public String configCheck(){
        return String.format("It's Working in Member Service %s \n %s"
                , environment.getProperty("jwt.secret")
                , environment.getProperty("jwt.description"));
    }
}
