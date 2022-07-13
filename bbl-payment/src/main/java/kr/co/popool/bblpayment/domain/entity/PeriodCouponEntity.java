package kr.co.popool.bblpayment.domain.entity;

import kr.co.popool.bblpayment.domain.shared.enums.CouponPeriod;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@DiscriminatorValue("P")
@Entity
@NoArgsConstructor
public class PeriodCouponEntity extends ItemMstEntity {

    @Column(nullable = true)
    private CouponPeriod period;
}
