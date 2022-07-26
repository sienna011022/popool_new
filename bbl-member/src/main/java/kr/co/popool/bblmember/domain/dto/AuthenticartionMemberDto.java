package kr.co.popool.bblmember.domain.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticartionMemberDto {
    private String identity;
    private String name;
}
