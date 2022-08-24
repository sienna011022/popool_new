package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.service.CorporateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class CorporateController {

    private final CorporateService corporateService;

    @ApiOperation("기업 회원가입")
    @PostMapping("/corporate/signUp")
    public ResponseFormat corporateSignUp(@RequestBody @Valid CorporateDto.CREATE_CORPORATE create){
        corporateService.corporateSignUp(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("기업 정보 수정")
    @PutMapping("/update/corporate")
    public ResponseFormat corporateUpdate(@RequestBody @Valid CorporateDto.UPDATE_CORPORATE update_corporate){
        corporateService.corporateUpdate(update_corporate);
        return ResponseFormat.ok();
    }

    @ApiOperation("기업 정보 조회")
    @GetMapping("/corporate")
    public ResponseFormat<CorporateDto.READ_CORPORATE> getCorporate(){
        return ResponseFormat.ok(corporateService.getCorporate());
    }
}
