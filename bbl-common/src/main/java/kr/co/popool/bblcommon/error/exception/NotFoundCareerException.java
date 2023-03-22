package kr.co.popool.bblcommon.error.exception;

import kr.co.popool.bblcommon.error.model.ErrorCode;

import javax.lang.model.type.ErrorType;

public class NotFoundCareerException extends BusinessLogicException {

    public NotFoundCareerException() {
        super(ErrorCode.NOT_FOUND_CAREER);
    }
}
