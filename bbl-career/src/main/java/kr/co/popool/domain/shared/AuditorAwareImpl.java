package kr.co.popool.domain.shared;

import kr.co.popool.infra.interceptor.MemberThreadLocal;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String identity = MemberThreadLocal.get();

        if(identity == null){
            return null;
        }

        return Optional.of(identity);
    }
}
