package kr.co.popool.bblpayment.domain.entity;

import kr.co.popool.bblpayment.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
@Table(name = "tbl_item_mst")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@AttributeOverride(name = "id", column = @Column(name = "item_id"))
@NoArgsConstructor
public abstract class ItemMstEntity extends BaseEntity {

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String name;

    public ItemMstEntity(int price, String name) {
        this.price = price;
        this.name = name;
    }
}
