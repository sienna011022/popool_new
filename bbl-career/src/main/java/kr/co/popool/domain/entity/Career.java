package kr.co.popool.domain.entity;

import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.util.Assert.hasText;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_career")
public class Career extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String memberId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(length = 10)
    private String period;
    @Lob
    private String selfDescription;

    private Grade grade;

    @Builder
    private Career(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String memberId, String name, String email, String period, String selfDescription,Grade grade) {
        super(id, createdAt, updatedAt);

        hasText(memberId, "아이디를 입력하세요");
        hasText(name, "이름을 입력하세요");
        hasText(email, "이메일을 입력하세요");

        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.period = period;
        this.selfDescription = selfDescription;
        this.grade = Grade.WHITE;
    }

    public void updateCareer(CareerUpdateRequest request) {
        name = request.getName();
        email = request.getEmail();
        period = request.getPeriod();
        selfDescription = request.getSelfDescription();
    }

    public void updateGrade(Grade grade){
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(memberId, career.memberId) && Objects.equals(name, career.name) && Objects.equals(email, career.email) && Objects.equals(period, career.period) && Objects.equals(selfDescription, career.selfDescription) && grade == career.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, email, period, selfDescription, grade);
    }
}
