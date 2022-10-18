package kr.co.popool.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class Controller {

    @GetMapping
    public String test(){
        return "test-Gateway";
    }
}
