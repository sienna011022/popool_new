package kr.co.popool.gateway.filters;


import kr.co.popool.bblcommon.error.exception.UnauthorizedException;
import kr.co.popool.gateway.config.dto.FilterConfigDto;
import kr.co.popool.gateway.jwt.JwtProviderGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class JwtAuthorizationHeaderFilter extends AbstractGatewayFilterFactory<FilterConfigDto> {

    private final JwtProviderGateway jwtProvider;

    public JwtAuthorizationHeaderFilter(JwtProviderGateway jwtProvider) {
        super(FilterConfigDto.class);
        this.jwtProvider = jwtProvider;
    }

    /**
     * 사용자 Login 요청 -> Token 반
     * 사용자가 Token 과 함께 서비스 요청 -> Header (Include Token)
     * @param configDto
     * @return
     */
    @Override
    public GatewayFilter apply(FilterConfigDto configDto) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            jwtProvider.isRequestHeaders(request);
            String token = jwtProvider.resolveToken(request)
                    .orElseThrow(() -> new UnauthorizedException());

            jwtProvider.isUsable(token);

            return chain.filter(exchange);
        });
    }
}
