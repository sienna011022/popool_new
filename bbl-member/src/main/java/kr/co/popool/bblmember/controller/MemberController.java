package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.service.MemberMstServiceImpl;
import kr.co.popool.bblmember.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberMstServiceImpl memberMstService;
    private final MemberServiceImpl memberService;

    @ApiOperation("일반 회원가입")
    @PostMapping("/normal/signUp")
    public ResponseFormat signUp(@RequestBody @Valid MemberMstDto.CREATE create){
        memberMstService.signUp(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("일반 회원 정보 수정")
    @PutMapping("/normal/update")
    public ResponseFormat updateNormal(@RequestBody @Valid MemberMstDto.UPDATE memberUpdate){
        memberService.update(memberUpdate);
        return ResponseFormat.ok();
    }
}
