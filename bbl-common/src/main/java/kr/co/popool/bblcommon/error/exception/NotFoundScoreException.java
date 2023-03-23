package kr.co.popool.bblcommon.error.exception;

import kr.co.popool.bblcommon.error.model.ErrorCode;

public class NotFoundScoreException extends BusinessLogicException {

    public NotFoundScoreException() {
        super(ErrorCode.NOT_FOUND_SCORE);
    }
}
