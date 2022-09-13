package kr.co.popool.bblpayment.domain.entity.item;

import kr.co.popool.bblpayment.domain.shared.BaseEntity;
import kr.co.popool.bblpayment.domain.shared.enums.CouponPeriod;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Table(name = "tbl_inventory")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "inventory_id"))
public class InventoryEntity extends BaseEntity {

    @Column(nullable = false)
    private Long memberId; //기업 회원과 일대일 관계

    @Column(nullable = false)
    private int remainCouponCnt;

    @Column
    private LocalDate endPeriodDate;

    @Column
    private String sid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscribe_id")
    private SubscribeEntity subscribe;


    //== coupon ==//
    public void useRemainCoupon(int usedCouponNum) {
        if (this.remainCouponCnt - usedCouponNum < 0)
            throw new RuntimeException("쿠폰이 충분하지 않습니다.");

        this.remainCouponCnt -= usedCouponNum;
    }

    public void addCoupon(int addCouponNum) {
        this.remainCouponCnt += addCouponNum;
    }


    //== period ==//
    public boolean isPeriodValid() {
        if (endPeriodDate.isAfter(LocalDate.now()) || endPeriodDate.isEqual(LocalDate.now()))
            return true;
        return false;
    }

    public void addPeriod(CouponPeriod couponPeriod) {
        if (isPeriodValid()) {
            this.endPeriodDate = this.endPeriodDate.plusMonths(couponPeriod.getPeriod());
            return;
        }

        if (!isPeriodValid()) {
            this.endPeriodDate = LocalDate.now().plusMonths(couponPeriod.getPeriod());
            return;
        }
    }

    //TODO: Common 모듈 에러 추가
    //== subscription ==//
    public void doSubscription(SubscribeEntity subscribe, String sid) {
        if (isSubscription())
            throw new RuntimeException("이미 구독중입니다.");

        if (isPeriodValid())
            throw new RuntimeException("기간권 사용중입니다.");

        this.sid = sid;
    }

    public boolean isSubscription() {
        if (this.sid == null)
            return false;
        return true;
    }

    public void cancelSubscription() {
        this.sid = null;
    }
}
