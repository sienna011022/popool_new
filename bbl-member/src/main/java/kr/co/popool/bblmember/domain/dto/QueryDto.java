package kr.co.popool.bblmember.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import kr.co.popool.bblmember.domain.shared.Address;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import lombok.Getter;

import java.time.LocalDateTime;


public class QueryDto {

    @Getter
    public static class CORPORATE_INFO{
        private String ceoName;

        private String businessNumber;

        private String businessName;

        @QueryProjection
        public CORPORATE_INFO(String ceoName,
                              String businessNumber,
                              String businessName) {
            this.ceoName = ceoName;
            this.businessNumber = businessNumber;
            this.businessName = businessName;
        }
    }

    @Getter
    public static class MEMBER_INFO{
        private String identity;

        private String name;

        private Address address;

        private Phone phone;

        private String email;

        private Gender gender;

        private String birth;

        private MemberRank memberRank;

        private LocalDateTime create_at;

        @QueryProjection
        public MEMBER_INFO(String identity,
                           String name,
                           Address address,
                           Phone phone,
                           String email,
                           Gender gender,
                           String birth,
                           MemberRank memberRank,
                           LocalDateTime create_at) {
            this.identity = identity;
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.gender = gender;
            this.birth = birth;
            this.memberRank = memberRank;
            this.create_at = create_at;
        }
    }
}
