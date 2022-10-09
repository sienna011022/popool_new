package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import kr.co.popool.bblmember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<MemberDto.TOKEN> login(@RequestBody @Valid MemberDto.LOGIN login){
        return ResponseFormat.ok(memberService.login(login));
    }

    @ApiOperation("일반 회원가입")
    @PostMapping("/signUp")
    public ResponseFormat signUp(@RequestBody @Valid MemberDto.CREATE create){
        memberService.signUp(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("아이디 찾기")
    @PostMapping("/identity")
    public ResponseFormat<String> findIdentity(@RequestBody @Valid MemberDto.READ_ID read_id){
        return ResponseFormat.ok(memberService.findIdentity(read_id));
    }

    @ApiOperation("회원 정보 복구")
    @PostMapping("/reCreate")
    public ResponseFormat reCreate(@RequestBody @Valid MemberDto.RE_CREATE re_create){
        memberService.reCreate(re_create);
        return ResponseFormat.ok();
    }

    @ApiOperation("아이디 중복 체크")
    @PostMapping("/signUp/check")
    public ResponseFormat checkIdentity(@RequestParam("identity") String identity) {
        memberService.checkIdentity(identity);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping
    public ResponseFormat update(@RequestBody @Valid MemberDto.UPDATE update){
        memberService.update(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("비밀번호 수정")
    @PutMapping("/password")
    public ResponseFormat updatePassword(@RequestBody @Valid MemberDto.UPDATE_PASSWORD update){
        memberService.updatePassword(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("주소 수정")
    @PutMapping("/address")
    public ResponseFormat updateAddress(@RequestBody @Valid MemberDto.UPDATE_ADDRESS update){
        memberService.updateAddress(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("전화번호 수정")
    @PutMapping("/phone")
    public ResponseFormat updatePhone(@RequestBody @Valid MemberDto.UPDATE_PHONE update){
        memberService.updatePhone(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 자동 결제 여부 수정")
    @PutMapping("/payment")
    public ResponseFormat paymentAgreeUpdate(){
        memberService.paymentAgreeUpdate();
        return ResponseFormat.ok();
    }

    @ApiOperation("자신 회원 정보 조회")
    @GetMapping()
    public ResponseFormat<MemberDto.READ> get(){
        return ResponseFormat.ok(memberService.get());
    }


    @ApiOperation("주소 등록 여부 조회")
    @GetMapping("/address")
    public ResponseFormat<Boolean> getAddress(){
        return ResponseFormat.ok(memberService.getAddress());
    }

    @ApiOperation("자동 결제 동의 여부 조회")
    @GetMapping("/payment")
    public ResponseFormat<Boolean> getPaymentAgree(){
        return ResponseFormat.ok(memberService.getPaymentAgree());
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping
    public ResponseFormat delete(@RequestParam("password") String password){
        memberService.delete(password);
        return ResponseFormat.ok();
    }


    @ApiOperation("이메일 중복 체크")
    @PostMapping("/update/check")
    public ResponseFormat checkEmail(@RequestParam("email") String email) {
        memberService.checkEmail(email);
        return ResponseFormat.ok();
    }

    @ApiOperation("전화번호 중복 체크, {api} => signUp or update")
    @PostMapping("/{api}/check")
    public ResponseFormat checkPhone(@PathVariable("api") String api,
                                     @RequestParam("phone") String phone) {
        memberService.checkPhone(new Phone(phone));
        return ResponseFormat.ok();
    }
    
    @ApiOperation("AccessToken 재발급")
    @GetMapping("/refresh")
    public ResponseFormat<String> resetRefreshToken(@RequestHeader("refreshToken") String refreshToken){
        return ResponseFormat.ok(jwtProvider.reCreateAccessToken(refreshToken));
    }

    @ApiOperation("Redis Data 삭제")
    @DeleteMapping("/refresh/delete")
    public ResponseFormat deleteRefreshToken(@RequestParam("identity") String identity){
        memberService.deleteRefreshToken(identity);
        return ResponseFormat.ok();
    }

}
