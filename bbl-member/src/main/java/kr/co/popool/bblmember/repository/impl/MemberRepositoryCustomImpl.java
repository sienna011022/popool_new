package kr.co.popool.bblmember.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.popool.bblmember.domain.dto.QQueryDto_MEMBER_INFO;
import kr.co.popool.bblmember.domain.dto.QueryDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.entity.QMemberEntity;
import kr.co.popool.bblmember.repository.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberRepositoryCustomImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

    public MemberRepositoryCustomImpl(Class<?> domainClass, JPAQueryFactory jpaQueryFactory) {
        super(domainClass);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<QueryDto.MEMBER_INFO> findDtoByMemberInfo(MemberEntity memberEntity) {
        return Optional.ofNullable(
                jpaQueryFactory.select(new QQueryDto_MEMBER_INFO(
                                qMemberEntity.identity,
                                qMemberEntity.name,
                                qMemberEntity.address,
                                qMemberEntity.phone,
                                qMemberEntity.email,
                                qMemberEntity.gender,
                                qMemberEntity.birth,
                                qMemberEntity.memberRank,
                                qMemberEntity.createdAt
                        ))
                        .from(qMemberEntity)
                        .where(eqMemberId(memberEntity.getId()))
                        .fetchOne()
        );
    }

    private BooleanExpression eqMemberId(Long id){
        return this.qMemberEntity.id.eq(id);
    }

}
