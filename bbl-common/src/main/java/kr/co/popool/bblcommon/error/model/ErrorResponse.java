package kr.co.popool.bblcommon.error.model;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;

    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

    public static ErrorResponse of(String errorMessage){
        return ErrorResponse.builder()
                .status(400)
                .message(errorMessage)
                .build();
    }
}
