package kr.co.popool.bblmember.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.popool.bblmember.domain.entity.QCorporateEntity;
import kr.co.popool.bblmember.repository.CorporateRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

@Component
public class CorporateRepositoryCustomImpl extends QuerydslRepositorySupport implements CorporateRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QCorporateEntity qCorporateEntity = QCorporateEntity.corporateEntity;

    public CorporateRepositoryCustomImpl(Class<?> domainClass, JPAQueryFactory jpaQueryFactory) {
        super(domainClass);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
