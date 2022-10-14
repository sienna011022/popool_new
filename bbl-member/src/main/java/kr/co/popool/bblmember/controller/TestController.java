package kr.co.popool.bblmember.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

    private final Environment environment;

    @GetMapping("/config")
    public String configCheck(){
        return String.format("Member Service %s \n %s \n %s \n %s"
        , environment.getProperty("local.server.port")
        , environment.getProperty("server.port")
        , environment.getProperty("jwt.secret")
        , environment.getProperty("jwt.description"));
    }
}
