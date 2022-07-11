package kr.co.popool.bblcommon.error.exception.jwt;


import kr.co.popool.bblcommon.error.exception.BusinessLogicException;

public class JwtTokenExpiredException extends BusinessLogicException {
    public JwtTokenExpiredException() {
        super("JWT Token Expired");
    }
}
