package kr.co.popool.bblpayment.domain.dto.payment;

import lombok.*;

public class KakaoPayDTO {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ORDER {

        private String partner_user_id;
        private String item_id;
        private String item_name;
        private String quantity;
        private String total_amount;
        private String tax_free_amount;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class READY_REQUEST {

        private String cid;
        private String partner_order_id;
        private String partner_user_id;
        private String item_name;
        private String quantity;
        private String total_amount;
        private String tax_free_amount;
        private String approval_url;
        private String fail_url;
        private String cancel_url;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class READY_RESPONSE {

        private String tid;
        private String next_redirect_app_url;
        private String next_redirect_mobile_url;
        private String next_redirect_pc_url;
        private String android_app_scheme;
        private String ios_app_scheme;
        private String created_at;
    }


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class APPROVAL_REQUEST {

        private String cid;
        private String tid;
        private String partner_order_id;
        private String partner_user_id;
        private String pg_token;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class APPROVAL_RESPONSE {

        private String aid;
        private String tid;
        private String cid;
        private String partner_order_id;
        private String partner_user_id;
    }
}
