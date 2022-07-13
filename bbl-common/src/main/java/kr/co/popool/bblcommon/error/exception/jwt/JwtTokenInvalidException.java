package kr.co.popool.bblcommon.error.exception.jwt;


import kr.co.popool.bblcommon.error.exception.UserDefineException;

public class JwtTokenInvalidException extends UserDefineException {
    public JwtTokenInvalidException(String message) {
        super(message);
    }
}
