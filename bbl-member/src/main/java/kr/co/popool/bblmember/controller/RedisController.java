package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import kr.co.popool.bblmember.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;
    private final JwtProvider jwtProvider;

    @ApiOperation("AccessToken 재발급")
    @GetMapping("/refresh")
    public ResponseFormat<String> resetRefreshToken(@RequestHeader("token") String refreshToken){
        return ResponseFormat.ok(jwtProvider.createAccessToken(refreshToken));
    }

    @ApiOperation("Redis Data 삭제")
    @DeleteMapping("/refresh/delete")
    public ResponseFormat deleteRefreshToken(@RequestParam("identity") String identity){
        redisService.deleteData(identity);
        return ResponseFormat.ok();
    }
}
