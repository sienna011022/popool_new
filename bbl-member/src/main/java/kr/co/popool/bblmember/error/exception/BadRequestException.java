package kr.co.memberservice.error.exception;

public class BadRequestException extends BusinessLogicException{
    public BadRequestException(String message) {
        super(String.format("Bad Request : %s", message));
    }
}
