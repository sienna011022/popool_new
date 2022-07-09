package kr.co.popool.bblmember.domain.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MemberRole {

    ROLE_MEMBER("멤버"),
    ROLE_ADMIN("관리자");

    private String member_role;

    public static MemberRole of(String role) {
        return Arrays.stream(MemberRole.values())
                .filter(r -> r.toString().equalsIgnoreCase(role))
                .findAny().orElseThrow(() -> new RuntimeException("해당 Role 항목을 찾을 수 없습니다."));
    }
}
