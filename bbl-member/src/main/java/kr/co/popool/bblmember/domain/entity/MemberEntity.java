package kr.co.memberservice.domain.entity;

import kr.co.memberservice.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@Getter
public class MemberEntity extends BaseEntity {

    /*
        To do...
     */

    @OneToOne
    @JoinColumn(name = "member_mst_id")
    private MemberMstEntity memberMstEntity;
}
