package kr.co.popool.bblpayment.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@DiscriminatorValue("S")
@Entity
@NoArgsConstructor
public class SubscribeEntity extends ItemMstEntity{

    @Column(nullable = true)
    private LocalDate payDatePerMonth;
}
