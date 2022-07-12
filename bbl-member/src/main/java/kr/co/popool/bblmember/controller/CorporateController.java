package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.service.CorporateServiceImpl;
import kr.co.popool.bblmember.service.MemberMstServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class CorporateController {

    private final MemberMstServiceImpl memberMstService;

    @ApiOperation("기업 회원가입")
    @PostMapping("/corporate/signUp")
    public ResponseFormat corporateSignUp(@RequestBody @Valid MemberMstDto.CREATE_CORPORATE create){
        memberMstService.corporateSignUp(create);
        return ResponseFormat.ok();
    }
}
