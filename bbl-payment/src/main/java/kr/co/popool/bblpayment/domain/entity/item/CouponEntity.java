package kr.co.popool.bblpayment.domain.entity.item;

import kr.co.popool.bblpayment.domain.dto.item.CouponDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ToString
@Getter
@NoArgsConstructor
@DiscriminatorValue("C")
@Entity
public class CouponEntity extends ItemMstEntity {

    @Column(nullable = true)
    private int amount;

    @Builder
    public CouponEntity(int price, String name, int amount) {
        super(price, name);
        this.amount = amount;
    }

    public void update(CouponDTO.UPDATE update) {
        this.name = update.getName();
        this.price = update.getPrice();
        this.amount = update.getAmount();
    }
}
