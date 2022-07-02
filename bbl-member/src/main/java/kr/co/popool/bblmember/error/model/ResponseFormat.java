package kr.co.memberservice.error.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseFormat<T> {

    @ApiModelProperty(example = "성공은 1 or 실패는 2 or 토큰 만료는 3")
    private int code;
    @ApiModelProperty(example = "true or false")
    private boolean result;
    private T data;
    @ApiModelProperty(example = "성공 or 에러 메세지 or 토큰이 만료되었습니다.")
    private String description;

    public static ResponseFormat fail(String message){
        return ResponseFormat.builder()
                .code(ResponseCode.FAIL.getCode())
                .result(false)
                .data(null)
                .description(message)
                .build();
    }

    public static <T> ResponseFormat ok(T data){
        return ResponseFormat.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .result(true)
                .data(data)
                .description("성공")
                .build();
    }

    public static ResponseFormat ok(){
        return ResponseFormat.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .result(true)
                .data(null)
                .description("성공")
                .build();
    }

    public static ResponseFormat expire(){
        return ResponseFormat.builder()
                .code(ResponseCode.TOKEN_EXPIRED.getCode())
                .result(false)
                .data(null)
                .description("토큰이 만료되었습니다.")
                .build();
    }
}
