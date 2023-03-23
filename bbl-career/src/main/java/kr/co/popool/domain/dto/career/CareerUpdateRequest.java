package kr.co.popool.domain.dto.career;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CareerUpdateRequest {

    private String name;

    private String email;

    private String period;

    private String selfDescription;

    @Builder
    public CareerUpdateRequest(String name, String email, String period, String selfDescription) {
        this.name = name;
        this.email = email;
        this.period = period;
        this.selfDescription = selfDescription;
    }
}
