package kr.co.popool.bblpayment.domain.entity.payment;

import kr.co.popool.bblpayment.domain.dto.payment.KakaoPayDTO;
import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
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
@DiscriminatorValue("Kakao")
@Entity
public class KakaoPayLogEntity extends PaymentLogMstEntity {

    @Column
    private String tid;

    @Column
    private String sid;

    @Column(nullable = false)
    private String itemName;

    @Builder
    public KakaoPayLogEntity(Long memberId,
                             ItemMstEntity item,
                             int quantity,
                             int totalAmount,
                             String itemName) {

        super(memberId, item, quantity, totalAmount);
        this.itemName = itemName;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
