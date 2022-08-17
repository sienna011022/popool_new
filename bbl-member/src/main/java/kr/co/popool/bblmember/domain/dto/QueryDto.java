package kr.co.popool.bblmember.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;


public class QueryDto {

    @Getter
    public static class CORPORATE_INFO{
        private String ceoName;

        private String businessNumber;

        private String businessName;

        @QueryProjection
        public CORPORATE_INFO(String ceoName, String businessNumber, String businessName) {
            this.ceoName = ceoName;
            this.businessNumber = businessNumber;
            this.businessName = businessName;
        }
    }
}
