package kr.co.popool.bblmember.error.exception;

import kr.co.popool.bblmember.error.model.ErrorCode;

public class UnauthorizedException extends BusinessLogicException{

    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED_USER);
    }
}