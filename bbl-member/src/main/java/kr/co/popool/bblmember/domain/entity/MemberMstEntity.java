package kr.co.popool.bblmember.domain.entity;

import kr.co.popool.bblmember.domain.dto.MemberMstDto;
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
@Table(name = "tbl_member_mst")
@Getter
@AttributeOverride(name = "id", column = @Column(name = "mst_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMstEntity extends BaseEntity {

    @Column(name = "identity", unique = true, nullable = false, length = 100)
    private String identity;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "birth", nullable = false, length = 100)
    private String birth;

    @Column(name = "refresh_token", length = 600)
    private String refreshToken;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number"
                    , column = @Column(name = "phone", unique = true, nullable = false))
    })
    private Phone phone;

    @Embedded
    private Address address;

    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "member_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole;

    @Column(name = "member_rank", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRank memberRank;

    @Builder
    public MemberMstEntity(String identity, String password, String name, String birth
            , Phone phone, Gender gender, MemberRole memberRole, MemberRank memberRank) {
        this.identity = identity;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.gender = gender;
        this.memberRole = memberRole;
        this.memberRank = memberRank;
    }

    public void updateMemberMst(MemberMstDto.UPDATE memberUpdate){
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

    public  void updateAddress(MemberMstDto.UPDATE_ADDRESS update_address){
        this.address = new Address(update_address.getZipCode(), update_address.getAddr1(), update_address.getAddr2());
    }

    public void updatePhone(MemberMstDto.UPDATE_PHONE update_phone){
        this.phone = new Phone(update_phone.getNewPhoneNumber());
    }
}
