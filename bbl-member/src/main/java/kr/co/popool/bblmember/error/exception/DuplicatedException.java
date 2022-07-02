package kr.co.memberservice.error.exception;

import kr.co.memberservice.error.model.ErrorCode;

public class DuplicatedException extends BusinessLogicException {
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}