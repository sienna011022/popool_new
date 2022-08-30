package kr.co.popool.gateway.filters;

import kr.co.popool.gateway.config.FilterConfig;
import kr.co.popool.gateway.jwt.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtAuthorizationHeaderFilter extends AbstractGatewayFilterFactory<FilterConfig> {

    @Autowired
    private JwtProvider jwtProvider;

    final Logger logger = LoggerFactory.getLogger(JwtAuthorizationHeaderFilter.class);

    public JwtAuthorizationHeaderFilter(){
        super(FilterConfig.class);
    }

    @Override
    public GatewayFilter apply(FilterConfig config) {
        return ((exchange, chain) -> {

            final Optional<String> token
                    = Optional.of(exchange.getRequest()
                    .getHeaders().get("Authorization").get(0)
                    .replace("Bearer", "").trim());
            jwtProvider.isUsable(token.get());

            return chain.filter(exchange);
        });
    }
}
