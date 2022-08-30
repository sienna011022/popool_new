package kr.co.popool.gateway.filters;

import kr.co.popool.gateway.config.FilterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MemberFilter extends AbstractGatewayFilterFactory<FilterConfig> {

    public MemberFilter(){
        super(FilterConfig.class);
    }

    @Override
    public GatewayFilter apply(final FilterConfig filterConfig){
        return ((exchange,chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("MemberFilter baseMessage: {}", filterConfig.getBaseMessage());

            if(filterConfig.isPreLogger()) {
                log.info("MemberFilter Start: {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(filterConfig.isPostLogger()) {
                    log.info("MemberFilter End:{}", response.getStatusCode());
                }
            }));
        });
    }
}