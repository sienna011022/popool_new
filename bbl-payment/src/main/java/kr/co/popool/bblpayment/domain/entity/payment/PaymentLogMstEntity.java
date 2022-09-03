package kr.co.popool.bblpayment.domain.entity.payment;

import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
import kr.co.popool.bblpayment.domain.shared.BaseEntity;
import kr.co.popool.bblpayment.domain.shared.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Table(name = "tbl_payment_log")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@AttributeOverride(name = "id", column = @Column(name = "payment_id"))
@Entity
public class PaymentLogMstEntity extends BaseEntity {

    @Column(nullable = false)
    protected Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    protected ItemMstEntity item;

    @Column(nullable = false)
    protected int quantity;

    @Column(nullable = false)
    protected int totalAmount;

    @Column(nullable = false)
    protected int taxFreeAmount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected PaymentStatus status = PaymentStatus.IN_PROGRESS;

    protected PaymentLogMstEntity(Long memberId, ItemMstEntity item, int quantity, int totalAmount) {
        this.memberId = memberId;
        this.item = item;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public void changePaymentStatusToSuccess() {
        this.status = PaymentStatus.SUCCESS;
    }

    public void changePaymentStatusToFail() {
        this.status = PaymentStatus.FAIL;
    }
}
