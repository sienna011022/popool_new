package kr.co.popool.bblmember.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.popool.bblmember.domain.entity.QMemberEntity;
import kr.co.popool.bblmember.repository.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

@Component
public class MemberRepositoryCustomImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberEntity memberEntity = QMemberEntity.memberEntity;

    public MemberRepositoryCustomImpl(Class<?> domainClass, JPAQueryFactory jpaQueryFactory) {
        super(domainClass);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
