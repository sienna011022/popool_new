package kr.co.popool.bblmember.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OauthDto {

    @Getter
    @AllArgsConstructor
    public static class LOGIN{
        private String code;

        private String provider;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CREATE {
        @ApiModelProperty(example = "사용할 아이디")
        @NotBlank(message = "아이디를 입력해주세요.")
        @Size(min = 5, max = 16, message = "ID는 5 ~ 16자를 입력해주세요")
        private String identity;

        @ApiModelProperty(example = "사용할 비밀번호")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        private String name;
        private String email;
        private String provider;

        @ApiModelProperty(example = "사용할 비밀번호 확인")
        @NotBlank(message = "확인 비밀번호를 입력해주세요.")
        private String checkPassword;

        @ApiModelProperty(example = "YYYYmmDD")
        @NotBlank(message = "생년월일을 입력해주세요.")
        private String birth;

        @ApiModelProperty(example = "010-XXXX-XXXX")
        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        private String phone;

        @ApiModelProperty(example = "MALE or FEMALE")
        @NotBlank(message = "성별을 입력해주세요.")
        private String gender;

        @ApiModelProperty(example = "ROLE_MEMBER")
        @NotBlank(message = "권한을 입력해주세요.")
        private String memberRole;

        @ApiModelProperty(example = "RANK_NORMAL or RANK_CORPORATE")
        @NotBlank(message = "회원 종류를 입력해주세요.")
        private String memberRank;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CREATE_CORPORATE {
        @ApiModelProperty(example = "대표 성함")
        @NotBlank(message = "기업 대표 성함을 입력해주세요.")
        private String ceoName;

        @ApiModelProperty(example = "사업자번호")
        @NotBlank(message = "111-1111-1111")
        private String businessNumber;

        @ApiModelProperty(example = "사업자명")
        @NotBlank(message = "사업자명을 입력해주세요.")
        private String businessName;

        private OauthDto.CREATE create;
    }



    @Builder
    @Getter
    @AllArgsConstructor
    public static class TOKEN_READ {
        @ApiModelProperty(example = "사용자 인증을 위한 AccessToken")
        private String accessToken;

        @ApiModelProperty(example = "자동 로그인을 위한 refreshToken")
        private String refreshToken;

        @ApiModelProperty(example = "첫 로그인이면 True, 아니면 False (최초 접속 사용자인지 판별)")
        private boolean isFirst;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TOKEN_INFO{
        private String accessToken;

        private String refreshToken;

        private String tokenType;

        private long accessExpiresIn;

        private long refreshExpiresIn;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PROFILE{
        private String name;
        private String email;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KAKAO{
        private int id;
        private ACCOUNT account;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ACCOUNT{
            private String name;
            private String email;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class GOOGLE{
        private String name;
        private String email;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NAVER{

        private Response response;

        @Getter
        @NoArgsConstructor
        public static class Response {
            private String id;
            private String name;
            private String email;
        }
    }
}