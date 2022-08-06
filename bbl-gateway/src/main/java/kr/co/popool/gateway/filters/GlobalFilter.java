package kr.co.popool.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<Config> {
    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config){
        return(exchange,chain) ->{
            log.info("GlobalFilter baseMessage: {}",config.getBaseMessage());

            if(config.isPreLogger()) {
                log.info("GlobalFilter Start: {}", exchange.getRequest());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.isPostLogger()) {
                    log.info("GlobalFilter End:{}", exchange.getResponse());
                }
            }));
        };
    }
}
//AbstraceGatewayFilterFactory : Gateway를 구현하기 위한 추상 클래스
//config : application.yml에 선언한 각 filter의 args 사용을 위한 클래스
//exchange : 서비스 요청/응답값을 담고있는 변수로, 요청/응답값을 출력하거나 변환할 때 사용한다.
// 요청값은 (exchange, chain) -> 구문 이후에 얻을 수 있으며, 서비스로부터 리턴받은 응답값은 Mono.fromRunnable(()-> 구문 이후부터 얻을 수 있다.
// > 서비스 요청/응답값을 담고있는 변수, 요청/응답값을 출력하거나 반환할 때 사용

