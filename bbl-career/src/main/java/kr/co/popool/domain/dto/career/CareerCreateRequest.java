package kr.co.popool.domain.dto.career;


import io.swagger.annotations.ApiModelProperty;
import kr.co.popool.domain.entity.Career;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CareerCreateRequest {

    @ApiModelProperty(example = "member1")
    @NotBlank(message = "본인의 아이디를 입력하세요")
    private String memberId;

    @ApiModelProperty(example = "김아무개")
    @NotBlank(message = "이름를 입력해주세요.")
    private String name;

    @ApiModelProperty(example = "abcabc@gmail.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @ApiModelProperty(example = "3")
    @NotBlank(message = "재직 기간을 입력해주세요")
    private String period;

    @ApiModelProperty(example = "안녕하세요 백엔드를 지망하고 있습니다")
    @NotBlank(message = "간단한 자기 소개를 입력해주세요")
    private String selfDescription;

    @Builder
    private CareerCreateRequest(String memberId, String name, String email, String period, String selfDescription) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.period = period;
        this.selfDescription = selfDescription;
    }

    public Career toCareer() {
        return Career.builder()
            .memberId(memberId)
            .name(name)
            .email(email)
            .period(period).
            selfDescription(selfDescription)
            .build();
    }
}
