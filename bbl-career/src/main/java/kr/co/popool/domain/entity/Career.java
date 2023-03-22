package kr.co.popool.domain.entity;

import kr.co.popool.domain.shared.BaseEntity;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_career")
public class Career extends BaseEntity {

  public static final String DELETED = "Y";
  public static final String NOT_DELETED = "N";

  @Column(nullable = false, length = 20)
  private String memberId;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false, length = 20)
  private String email;

  @Column(length = 10)
  private String period;

  @Lob
  private String selfDescription;

  @Column(name = "del_yn", nullable = false)
  private String deleted;

  @Builder
  private Career(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String memberId, String name, String email, String period, String selfDescription) {
    super(id, createdAt, updatedAt);

    hasText(memberId, "아이디를 입력하세요");
    hasText(name, "이름을 입력하세요");
    hasText(email, "이메일을 입력하세요");

    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.period = period;
    this.selfDescription = selfDescription;
    this.deleted = NOT_DELETED;
  }

  public void updateCareer(CareerUpdateRequest request) {
    name = request.getName();
    email = request.getEmail();
    period = request.getPeriod();
    selfDescription = request.getSelfDescription();
  }

  public void delete() {
    deleted = DELETED;
  }

  public boolean isDeleted(String status) {
    return deleted.equals(status);
  }

}
