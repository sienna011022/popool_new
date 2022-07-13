package kr.co.popool.bblmember.domain.entity;

import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_corporate")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "corporate_id"))
@Getter
public class CorporateEntity extends BaseEntity {

    @Column(name = "ceo_name", nullable = false, length = 100)
    private String ceoName;
<<<<<<<<< Temporary merge branch 1

    @Column(name = "business_number", unique = true, nullable = false, length = 100)
    private Long businessNumber;

    @Column(name = "business_name", unique = true, nullable = false, length = 100)
=========
    @Column(name = "business_number", nullable = false, length = 100)
    private Long businessNumber;
    @Column(name = "business_name", nullable = false, length = 100)
>>>>>>>>> Temporary merge branch 2
    private String businessName;

    @OneToOne
    @JoinColumn(name = "mst_id")
    private MemberMstEntity memberMstEntity;

    @Builder
    public CorporateEntity(String ceoName, Long businessNumber
            , String businessName, MemberMstEntity memberMstEntity) {
        this.ceoName = ceoName;
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.memberMstEntity = memberMstEntity;
    }

    public void updateCorporate(CorporateDto.UPDATE update){
<<<<<<<<< Temporary merge branch 1
        //TODO : 기업 회원 수정 메소드
=========
        //TODO : 기업 회원 정보 수정
>>>>>>>>> Temporary merge branch 2
    }
}

