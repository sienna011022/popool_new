package kr.co.popool.bblmember.infra.error.handler;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import kr.co.popool.bblmember.infra.error.jwt.JwtTokenExpiredException;
import kr.co.popool.bblmember.infra.error.jwt.JwtTokenInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestControllerAdvice
public class FilterExceptionHandler {

    @ExceptionHandler(JwtTokenExpiredException.class)
    public ResponseEntity handleJwtTokenExpiredException(JwtTokenExpiredException e){
        ResponseFormat responseFormat = ResponseFormat.expire();

        return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtTokenInvalidException.class)
    public ResponseEntity handleJwtTokenInvalidException(JwtTokenInvalidException e){
        ResponseFormat responseFormat = ResponseFormat.fail(e.getMessage());

        return new ResponseEntity(responseFormat, HttpStatus.OK);
    }
}
