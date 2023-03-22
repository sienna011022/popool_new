package kr.co.popool.bblcommon.error.exception;

import kr.co.popool.bblcommon.error.model.ErrorCode;

public class DeletedCareerException extends BusinessLogicException{

    public DeletedCareerException(){
        super(ErrorCode.DELETED_CAREER);
    }
}
