package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.service.CorporateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class CorporateController {

    private final CorporateServiceImpl corporateService;

    @ApiOperation("기업 회원가입")
    @PostMapping("/corporate/signUp")
    public ResponseFormat corporateSignUp(@RequestBody @Valid CorporateDto.CREATE_CORPORATE create){
        corporateService.corporateSignUp(create);
        return ResponseFormat.ok();
    }
}
