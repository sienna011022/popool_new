package kr.co.popool.bblpayment.domain.entity;

import kr.co.popool.bblpayment.domain.shared.enums.CouponPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DiscriminatorValue("P")
@Entity
public class PeriodCouponEntity extends ItemMstEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private CouponPeriod period;

    public PeriodCouponEntity(int price, String name, String period) {
        super(price, name);
        this.period = CouponPeriod.of(period);
    }
}
