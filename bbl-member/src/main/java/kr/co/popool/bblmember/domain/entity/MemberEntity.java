package kr.co.popool.bblmember.domain.entity;


import kr.co.popool.bblmember.domain.shared.BaseEntity;
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
        TODO : 일반 회원 필드
     */

    @OneToOne
    @JoinColumn(name = "member_mst_id")
    private MemberMstEntity memberMstEntity;
}
