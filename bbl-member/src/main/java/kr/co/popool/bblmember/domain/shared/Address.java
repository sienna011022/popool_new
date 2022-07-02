package kr.co.memberservice.domain.shared;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class Address {

    @ApiModelProperty(example = "12345")
    @Column(name = "zipcode")
    private String zipcode;

    @ApiModelProperty(example = "XX특별시 XX구 XX동 78")
    @Column(name = "addr1")
    private String addr1;

    @ApiModelProperty(example = "101동 1103호")
    @Column(name = "addr2")
    private String addr2;

    @Builder
    public Address(String zipcode, String addr1, String addr2) {
        this.zipcode = zipcode;
        this.addr1 = addr1;
        this.addr2 = addr2;
    }
}
