package kr.co.popool.domain.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "del_yn", nullable = true, length = 1)
  protected String del_yn = "N";

  @CreatedDate            //최초 생성 시간
  @Column(name = "created_at", nullable = true, updatable = false)
  protected Timestamp created_at = null;

  @LastModifiedDate       //마지막 수정 시간
  @Column(name = "updated_at", nullable = true)
  protected Timestamp updated_at = null;

  @LastModifiedBy
  @Column(name = "use_career")
  protected String use_Cember = null;

  public void deleted() {
    this.del_yn = "Y";
  }
}


