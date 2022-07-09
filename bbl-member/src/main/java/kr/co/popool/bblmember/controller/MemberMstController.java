package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.error.model.ResponseFormat;
import kr.co.popool.bblmember.service.MemberMstServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberMstController {

    private final MemberMstServiceImpl memberMstService;

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public ResponseFormat signUp(@RequestBody @Valid MemberMstDto.CREATE create){
        memberMstService.signUp(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("아이디 중복 체크")
    @PostMapping("/signUp/check")
    public ResponseFormat<Boolean> checkIdentity(@RequestParam("identity") String identity){
        return ResponseFormat.ok(memberMstService.checkIdentity(identity));
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<MemberMstDto.TOKEN> login(@RequestBody @Valid MemberMstDto.LOGIN login){
        return ResponseFormat.ok(memberMstService.login(login));
    }
}
