package kr.co.popool.bblmember.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MemberMstDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class LOGIN{

        @ApiModelProperty(example = "사용자 아이디")
        @NotBlank(message = "아이디를 입력해주세요")
        private String identity;

        @ApiModelProperty(example = "사용자 비밀번호")
        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TOKEN{
        @ApiModelProperty(example = "사용자 인증을 위한 accessToken")
        private String accessToken;
        @ApiModelProperty(example = "자동 로그인을 위한 refreshToken")
        private String refreshToken;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE{

        @ApiModelProperty(example = "example@email.com")
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @ApiModelProperty(example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @ApiModelProperty(example = "12345")
        @NotBlank(message = "우편번호를 입력해주세요")
        private String zipCode;

        @ApiModelProperty(example = "서울특별시 강남구 선릉로 627")
        @NotBlank(message = "기본 주소를 입력해주세요")
        private String addr1;

        @ApiModelProperty(example = "101호")
        @NotBlank(message = "상세 주소를 입력해주세요")
        private String addr2;

        @ApiModelProperty(example = "010-XXXX-XXXX")
        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        private String phone;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE_PASSWORD {
        @ApiModelProperty(example = "현재 비밀번호")
        @NotBlank(message = "현재 비밀번호를 입력해주세요")
        private String originalPassword;

        @ApiModelProperty(example = "변경할 비밀번호")
        @NotBlank(message = "변경할 비밀번호를 입력해주세요")
        private String newPassword;

        @ApiModelProperty(example = "변경 비밀번호 확인")
        @NotBlank(message = "확인 비밀번호를 입력해주세요")
        private String newCheckPassword;
    }


    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE_ADDRESS {
        @ApiModelProperty(example = "12345")
        @NotBlank(message = "우편번호를 입력해주세요")
        private String zipCode;

        @ApiModelProperty(example = "서울특별시 강남구 선릉로 627")
        @NotBlank(message = "기본 주소를 입력해주세요")
        private String addr1;

        @ApiModelProperty(example = "101호")
        @NotBlank(message = "상세 주소를 입력해주세요")
        private String addr2;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UPDATE_PHONE {
        @ApiModelProperty(example = "010-XXXX-XXXX")
        @NotBlank(message = "휴대폰 번호를 입력해주세요")
        private String newPhoneNumber;
    }
}
