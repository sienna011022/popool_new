package kr.co.popool.bblcommon.error.exception;

import kr.co.popool.bblcommon.error.model.ErrorCode;

public class UnauthorizedException extends BusinessLogicException{

    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED_USER);
    }
}