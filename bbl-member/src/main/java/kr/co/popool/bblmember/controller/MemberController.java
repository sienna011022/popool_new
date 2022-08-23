package kr.co.popool.bblmember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<MemberDto.TOKEN> login(@RequestBody @Valid MemberDto.LOGIN login){
        return ResponseFormat.ok(memberService.login(login));
    }

    @ApiOperation("일반 회원가입")
    @PostMapping("/normal/signUp")
    public ResponseFormat signUp(@RequestBody @Valid MemberDto.CREATE create){
        memberService.signUp(create);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/update")
    public ResponseFormat update(@RequestBody @Valid MemberDto.UPDATE update){
        memberService.update(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("비밀번호 수정")
    @PutMapping("/update/password")
    public ResponseFormat updatePassword(@RequestBody @Valid MemberDto.UPDATE_PASSWORD update){
        memberService.updatePassword(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("주소 수정")
    @PutMapping("/update/address")
    public ResponseFormat updateAddress(@RequestBody @Valid MemberDto.UPDATE_ADDRESS update){
        memberService.updateAddress(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("전화번호 수정")
    @PutMapping("/update/phone")
    public ResponseFormat updatePhone(@RequestBody @Valid MemberDto.UPDATE_PHONE update){
        memberService.updatePhone(update);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 자동 결제 여부 수정")
    @PutMapping("/update/payment")
    public ResponseFormat paymentAgreeUpdate(){
        memberService.paymentAgreeUpdate();
        return ResponseFormat.ok();
    }

    @ApiOperation("자신 회원 정보 조회")
    @GetMapping()
    public ResponseFormat<MemberDto.READ> get(){
        return ResponseFormat.ok(memberService.get());
    }

    @ApiOperation("아이디 찾기")
    @PostMapping("/identity")
    public ResponseFormat<String> findIdentity(@RequestBody @Valid MemberDto.READ_ID read_id){
        return ResponseFormat.ok(memberService.findIdentity(read_id));
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
    @DeleteMapping("/delete")
    public ResponseFormat delete(@RequestParam("password") String password){
        memberService.delete(password);
        return ResponseFormat.ok();
    }

    @ApiOperation("회원 정보 복구")
    @PostMapping("/reCreate")
    public ResponseFormat reCreate(@RequestBody @Valid MemberDto.RE_CREATE re_create){
        memberService.reCreate(re_create);
        return ResponseFormat.ok();
    }

    @ApiOperation("아이디 중복 체크")
    @PostMapping("/{rank}/signUp/check")
    public ResponseFormat<Boolean> checkIdentity(
            @PathVariable("rank") String rank,
            @RequestParam("identity") String identity
    ) {
        return ResponseFormat.ok(memberService.checkIdentity(identity));
    }

    @ApiOperation("이메일 중복 체크")
    @PostMapping("/update/check")
    public ResponseFormat<Boolean> checkEmail(@RequestParam("email") String email) {
        return ResponseFormat.ok(memberService.checkEmail(email));
    }

    @ApiOperation("전화번호 중복 체크, {api} => signUp or update")
    @PostMapping("/{api}/check")
    public ResponseFormat<Boolean> checkPhone(
            @PathVariable("api") String api,
            @RequestParam("phone") String phone
    ) {
        return ResponseFormat.ok(memberService.checkPhone(new Phone(phone)));
    }

    @ApiOperation("로그인 정보 호출")
    @PostMapping("identity/info")
    public ResponseFormat<String> loginInfo(){
        return ResponseFormat.ok(memberService.getLoginInfo());
    }

}
