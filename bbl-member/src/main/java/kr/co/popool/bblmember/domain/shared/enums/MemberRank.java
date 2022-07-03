package kr.co.popool.bblmember.domain.shared.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MemberRank {

    RANK_NORMAL("일반"),
    RANK_CORPORATE("기업");

    private String member_rank;

    public static MemberRank of(String rank) {
        return Arrays.stream(MemberRank.values())
                .filter(r -> r.toString().equalsIgnoreCase(rank))
                .findAny().orElseThrow(() -> new RuntimeException("해당 Rank 항목을 찾을 수 없습니다."));
    }
}
