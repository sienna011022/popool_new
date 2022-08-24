package kr.co.popool.bblmember.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CorporateDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CREATE_CORPORATE{

        @ApiModelProperty(example = "대표 성함")
        @NotBlank(message = "기업 대표 성함을 입력해주세요.")
        private String ceoName;

        @ApiModelProperty(example = "사업자번호")
        @NotBlank(message = "111-1111-1111")
        private String businessNumber;

        @ApiModelProperty(example = "사업자명")
        @NotBlank(message = "사업자명을 입력해주세요.")
        private String businessName;

        private MemberDto.CREATE create;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UPDATE_CORPORATE{
        @ApiModelProperty(example = "변경할 대표 성함")
        @NotBlank(message = "기업 대표 성함을 입력해주세요.")
        private String ceoName;

        @ApiModelProperty(example = "변경할 사업자번호")
        @NotBlank(message = "사업자 번호를 입력해주세요.")
        private String businessNumber;

        @ApiModelProperty(example = "변경할 사업자명")
        @NotBlank(message = "사업자명을 입력해주세요.")
        private String businessName;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class READ_CORPORATE{
        @ApiModelProperty(example = "변경할 대표 성함")
        private String ceoName;

        @ApiModelProperty(example = "변경할 사업자번호")
        private String businessNumber;

        @ApiModelProperty(example = "변경할 사업자명")
        private String businessName;
    }

}
