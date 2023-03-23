package kr.co.popool.domain.dto.score;


import io.swagger.annotations.ApiModelProperty;
import kr.co.popool.domain.entity.Career;
import kr.co.popool.domain.entity.Score;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ScoreCreateRequest {
    @ApiModelProperty(example = "member1")
    @NotBlank(message = "평가하고자 하는 아이디를 입력하세요")
    private String memberId;

    @ApiModelProperty(example = "evaluator1")
    @NotBlank(message = "본인 아이디를 입력하세요")
    private String evaluatorId;

    @ApiModelProperty(example = "5")
    @NotBlank(message = "근태점수를 입력하세요")
    private int attendance;

    @ApiModelProperty(example = "5")
    @NotBlank(message = "성실성 점수를 입력하세요")
    private int sincerity;

    @ApiModelProperty(example = "5")
    @NotBlank(message = "적극성 점수를 입력하세요")
    private int positiveness;

    @ApiModelProperty(example = "5")
    @NotBlank(message = "기술 점수를 입력하세요")
    private int technical;

    @ApiModelProperty(example = "5")
    @NotBlank(message = "협업능력 점수를 입력하세요")
    private int cooperative;

    @Builder
    private ScoreCreateRequest(String memberId,String evaluatorId, int attendance, int sincerity, int positiveness, int technical, int cooperative) {
        this.memberId = memberId;
        this.evaluatorId = evaluatorId;
        this.attendance = attendance;
        this.sincerity = sincerity;
        this.positiveness = positiveness;
        this.technical = technical;
        this.cooperative = cooperative;
    }

    public Score toScore(Career career) {
        return Score.builder()
            .career(career)
            .evaluatorId(evaluatorId)
            .attendance(attendance)
            .cooperative(cooperative)
            .positiveness(positiveness)
            .sincerity(sincerity)
            .technical(technical)
            .build();
    }

}
