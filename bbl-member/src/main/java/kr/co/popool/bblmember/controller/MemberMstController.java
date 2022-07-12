package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.service.MemberMstServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberMstController {

    private final MemberMstServiceImpl memberMstService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<MemberMstDto.TOKEN> login(@RequestBody @Valid MemberMstDto.LOGIN login){
        return ResponseFormat.ok(memberMstService.login(login));
    }

    @ApiOperation("아이디 중복 체크")
    @PostMapping("/{rank}/signUp/check")
    public ResponseFormat<Boolean> checkIdentity(
            @PathVariable("rank") String rank,
            @RequestParam("identity") String identity
    ) {
        return ResponseFormat.ok(memberMstService.checkIdentity(identity));
    }

    @ApiOperation("이메일 중복 체크")
    @PostMapping("/{rank}/update/check")
    public ResponseFormat<Boolean> checkEmail(
            @PathVariable("rank") String rank,
            @RequestParam("email") String email
    ) {
        return ResponseFormat.ok(memberMstService.checkEmail(email));
    }

    @ApiOperation("전화번호 중복 체크")
    @PostMapping("/{rank}/{api}/check")
    public ResponseFormat<Boolean> checkPhone(
            @PathVariable("rank") String rank,
            @PathVariable("api") String api,
            @RequestParam("phone") String phone
    ) {
        return ResponseFormat.ok(memberMstService.checkPhone(new Phone(phone)));
    }

}
