package kr.co.popool.bblmember.error.exception.jwt;

import kr.co.popool.bblmember.error.exception.UserDefineException;

public class JwtTokenInvalidException extends UserDefineException {
    public JwtTokenInvalidException(String message) {
        super(message);
    }
}
