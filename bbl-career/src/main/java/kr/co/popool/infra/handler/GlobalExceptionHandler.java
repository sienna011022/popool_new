package kr.co.popool.infra.handler;


import kr.co.popool.bblcommon.error.exception.BusinessLogicException;
import kr.co.popool.bblcommon.error.exception.UserDefineException;
import kr.co.popool.bblcommon.error.model.ErrorResponse;
import kr.co.popool.bblcommon.error.model.ResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BusinessLogicException.class, RuntimeException.class})
    public ResponseEntity handleRuntimeException(RuntimeException e){
        ResponseFormat responseFormat = ResponseFormat.fail(e.getMessage());

        return new ResponseEntity(responseFormat, HttpStatus.OK);
    }

    @ExceptionHandler(UserDefineException.class)
    public ResponseEntity<ErrorResponse> handleUserDefineException(UserDefineException e) {
        ResponseFormat responseFormat = ResponseFormat.fail(e.getMessage());

        return new ResponseEntity(responseFormat, HttpStatus.OK);
    }
}
