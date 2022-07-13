package kr.co.popool.bblcommon.error.exception;


import kr.co.popool.bblcommon.error.model.ErrorCode;

public class DuplicatedException extends BusinessLogicException {
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}