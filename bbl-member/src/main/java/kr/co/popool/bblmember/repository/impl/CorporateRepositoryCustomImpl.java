package kr.co.popool.bblmember.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.popool.bblmember.domain.dto.QQueryDto_CORPORATE_INFO;
import kr.co.popool.bblmember.domain.dto.QueryDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.entity.QCorporateEntity;
import kr.co.popool.bblmember.domain.entity.QMemberEntity;
import kr.co.popool.bblmember.repository.CorporateRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CorporateRepositoryCustomImpl extends QuerydslRepositorySupport implements CorporateRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QCorporateEntity qCorporateEntity = QCorporateEntity.corporateEntity;
    private final QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

    public CorporateRepositoryCustomImpl(Class<?> domainClass, JPAQueryFactory jpaQueryFactory) {
        super(domainClass);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<QueryDto.CORPORATE_INFO> findDtoByCorporateInfo(MemberEntity memberEntity) {
        return Optional.ofNullable(
                jpaQueryFactory.select(new QQueryDto_CORPORATE_INFO(
                        qCorporateEntity.ceoName,
                        qCorporateEntity.businessNumber,
                        qCorporateEntity.businessName
                ))
                .from(qCorporateEntity)
                .where(eqCorporate(memberEntity.getCorporateEntity()))
                .fetchOne()
        );
    }

    private BooleanExpression eqCorporate(CorporateEntity corporateEntity){
        return this.qMemberEntity.corporateEntity.eq(corporateEntity);
    }

}
