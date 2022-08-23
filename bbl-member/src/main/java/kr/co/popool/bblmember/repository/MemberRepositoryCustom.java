package kr.co.popool.bblmember.repository;

import kr.co.popool.bblmember.domain.dto.QueryDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<QueryDto.MEMBER_INFO> findDtoByMemberInfo(MemberEntity memberEntity);

}
