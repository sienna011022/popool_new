package kr.co.popool.bblmember.domain.shared;

import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@RequiredArgsConstructor
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
