package kr.co.popool.bblmember.domain.entity;

import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.shared.Address;
import kr.co.popool.bblmember.domain.shared.BaseEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member")
@Getter
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    @Column(name = "identity", unique = true, nullable = false, length = 100)
    private String identity;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "provider")
    private String provider;

    @Column(name = "birth", length = 100)
    private String birth;

    @Column(name = "refresh_token", length = 600)
    private String refreshToken;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number"
                    , column = @Column(name = "phone", unique = true))
    })
    private Phone phone;

    @Embedded
    private Address address;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "member_role")
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    @Column(name = "member_rank")
    @Enumerated(value = EnumType.STRING)
    private MemberRank memberRank;

    @Column(name = "payment_agree", length = 1)
    private String paymentAgree_yn = "N";

    @OneToOne
    @JoinColumn(name = "corporate_id")
    private CorporateEntity corporateEntity = null;

    @Builder
    public MemberEntity(String identity,
                        String password,
                        String name,
                        String birth,
                        Phone phone,
                        Gender gender,
                        MemberRole memberRole,
                        MemberRank memberRank,
                        CorporateEntity corporateEntity) {
        this.identity = identity;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.gender = gender;
        this.memberRole = memberRole;
        this.memberRank = memberRank;
        this.corporateEntity = corporateEntity;
    }

    public void updateMember(MemberDto.UPDATE memberUpdate){
        this.name = memberUpdate.getName();
        this.address = new Address(memberUpdate.getZipCode(), memberUpdate.getAddr1(), memberUpdate.getAddr2());
        this.phone = new Phone(memberUpdate.getPhone());
        this.email = memberUpdate.getEmail();
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateAddress(MemberDto.UPDATE_ADDRESS update_address){
        this.address = new Address(update_address.getZipCode(), update_address.getAddr1(), update_address.getAddr2());
    }

    public void updatePhone(MemberDto.UPDATE_PHONE update_phone){
        this.phone = new Phone(update_phone.getNewPhoneNumber());
    }

    public void agree() {
        this.paymentAgree_yn = "Y";
    }

    public void disagree(){
        this.paymentAgree_yn = "N";
    }

    public void saveOauth(String email, String provider, String name) {
        this.email = email;
        this.provider = provider;
        this.name = name;
    }
}
