package kr.co.popool.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MemberFilter extends AbstractGatewayFilterFactory<Config> {
    public MemberFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config){
        return(exchange,chain) ->{
            log.info("MemberFilter baseMessage: {}",config.getBaseMessage());

            if(config.isPreLogger()) {
                log.info("MemberFilter Start: {}", exchange.getRequest());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.isPostLogger()) {
                    log.info("MemberFilter End:{}", exchange.getResponse());
                }
            }));
        };
    }
}