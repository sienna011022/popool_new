package kr.co.popool.bblmember.domain.entity;


import kr.co.popool.bblmember.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@Getter
public class MemberEntity extends BaseEntity {

    @Column(name = "payment_agree", nullable = false, length = 1)
    private String paymentAgree_yn = "N";

    @OneToOne
    @JoinColumn(name = "member_mst_id")
    private MemberMstEntity memberMstEntity;

    @Builder
    public MemberEntity(MemberMstEntity memberMstEntity) {
        this.memberMstEntity = memberMstEntity;
    }

    public void agree() {
        this.paymentAgree_yn = "Y";
    }

    public void disagree(){
        this.paymentAgree_yn = "N";
    }
}