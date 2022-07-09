package kr.co.popool.bblmember.error.exception;


import kr.co.popool.bblmember.error.model.ErrorCode;

public class DuplicatedException extends BusinessLogicException {
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}