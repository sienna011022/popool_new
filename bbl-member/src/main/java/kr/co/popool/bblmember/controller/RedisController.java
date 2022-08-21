package kr.co.popool.bblmember.controller;

import kr.co.popool.bblmember.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;
}
