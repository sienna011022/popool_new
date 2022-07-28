package kr.co.popool.bblmember.domain.shared;

import kr.co.popool.bblmember.domain.dto.AuthenticartionMemberDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        MemberEntity memberEntity = MemberThreadLocal.get();

        if(memberEntity == null){
            return null;
        }

        AuthenticartionMemberDto authenticartionMemberDto = AuthenticartionMemberDto.builder()
                .identity(memberEntity.getIdentity())
                .name(memberEntity.getName())
                .build();

        return Optional.of(authenticartionMemberDto.getIdentity());
    }
}
