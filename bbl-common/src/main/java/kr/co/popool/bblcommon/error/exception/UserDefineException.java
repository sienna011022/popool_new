package kr.co.popool.bblcommon.error.exception;

import lombok.Getter;

@Getter
public class UserDefineException extends RuntimeException{

    private String originalMessage;

    public UserDefineException(String message) {
        super(message);
    }

    public UserDefineException(String message, String originalMessage) {
        super(message);
        this.originalMessage = originalMessage;
    }
}
