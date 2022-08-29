package kr.co.popool.bblpayment.domain.entity.item;

import kr.co.popool.bblpayment.domain.dto.item.PeriodCouponDTO;
import kr.co.popool.bblpayment.domain.shared.enums.CouponPeriod;
import lombok.Builder;
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

    @Builder
    public PeriodCouponEntity(int price, String name, String period) {
        super(price, name);
        this.period = CouponPeriod.of(period);
    }

    public void update(PeriodCouponDTO.UPDATE update) {
        this.name = update.getName();
        this.price = update.getPrice();
        this.period = CouponPeriod.of(update.getPeriod());
    }
}
