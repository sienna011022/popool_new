package kr.co.popool.bblpayment.domain.entity.item;

import kr.co.popool.bblpayment.domain.dto.item.SubscribeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@DiscriminatorValue("S")
@Entity
public class SubscribeEntity extends ItemMstEntity{

    @Column(nullable = true)
    private LocalDate payDatePerMonth;

    @Builder
    public SubscribeEntity(int price, String name, LocalDate payDatePerMonth) {
        super(price, name);
        this.payDatePerMonth = payDatePerMonth;
    }

    public void update(SubscribeDTO.UPDATE update) {
        this.name = update.getName();
        this.price = update.getPrice();
        this.payDatePerMonth = update.getPayDatePerMonth();
    }
}
