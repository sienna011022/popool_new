package kr.co.popool.bblmember.error.exception.jwt;

import kr.co.popool.bblmember.error.exception.BusinessLogicException;

public class JwtTokenExpiredException extends BusinessLogicException {
    public JwtTokenExpiredException() {
        super("JWT Token Expired");
    }
}
