package kr.co.memberservice.domain.entity;

import kr.co.memberservice.domain.dto.CorporateDto;
import kr.co.memberservice.domain.shared.BaseEntity;
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
    @Column(name = "business_number", unique = true, nullable = false, length = 100)
    private Long businessNumber;
    @Column(name = "business_name", unique = true, nullable = false, length = 100)
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
        //to do...
    }
}

