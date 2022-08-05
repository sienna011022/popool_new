package kr.co.popool.bblmember.domain.entity;

import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.dto.OauthDto;
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

    @Column(name = "business_number", nullable = false, length = 100)
    private String businessNumber;

    @Column(name = "business_name", nullable = false, length = 100)
    private String businessName;

    @Builder
    public CorporateEntity(String ceoName,
                           String businessNumber,
                           String businessName) {
        this.ceoName = ceoName;
        this.businessNumber = businessNumber;
        this.businessName = businessName;
    }

    public void corporateUpdate(CorporateDto.UPDATE_CORPORATE update_corporate){
        this.ceoName = update_corporate.getCeoName();
        this.businessName = update_corporate.getBusinessName();
        this.businessNumber = update_corporate.getBusinessNumber();
    }

    public static CorporateEntity of(CorporateDto.CREATE_CORPORATE create_corporate) {
        return CorporateEntity.builder()
                .ceoName(create_corporate.getCeoName())
                .businessName(create_corporate.getBusinessName())
                .businessNumber(create_corporate.getBusinessNumber())
                .build();
    }

    public static CorporateEntity of(OauthDto.CREATE_CORPORATE create_corporate) {
        return CorporateEntity.builder()
                .ceoName(create_corporate.getCeoName())
                .businessName(create_corporate.getBusinessName())
                .businessNumber(create_corporate.getBusinessNumber())
                .build();
    }
}
