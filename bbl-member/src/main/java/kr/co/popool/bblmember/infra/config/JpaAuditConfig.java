package kr.co.popool.bblmember.infra.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.popool.bblmember.domain.shared.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@EnableJpaAuditing
@Configuration
public class JpaAuditConfig {
    @Bean
    public AuditorAware<String> defaultAuditorAware(){
        return new AuditorAwareImpl();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
