package kr.co.popool.bblcommon.error.model;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //common
    UNAUTHORIZED_USER("권한이 없습니다.", 403),
    RE_LOGIN("로그인을 다시 해주세요.", 403),

    //member
    WRONG_PASSWORD("비밀번호를 다시 확인해주세요", 400),
    WRONG_CORPORATE("기업 회원이 아닙니다.", 400),
    DUPLICATED_ID("중복된 아이디를 사용할 수 없습니다.", 400),
    DUPLICATED_PHONE("중복된 전화번호를 사용할 수 없습니다.", 400),
    DUPLICATED_EMAIL("중복된 이메일을 사용할 수 없습니다.", 400),

    //TODO : career, score 등등

    DUPLICATED_MEMBERIDENTITY("이미 인사 내역이 등록된 아이디입니다",400),



    //TODO : item, payment 등등

    private String message;
    private int status;

    ErrorCode(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
