package main.java.kr.co.popool.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstraceGatewayFilterFactory<Config> {
    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config){
        return(exchange,chain) ->{
            log.info("GlobalFilter baseMessage: {}",config.getBaseMessage()); //요청값(request)

            if(congif.isPreLogger()) {
                log.info("GlobalFilter Start: {}", exchange.getRequest());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(config.isPostLogger()) {
                    log.info("GlobalFilter End:{}", exchange.getResponse());  //서비스로부터 리턴받은 응답값
                }
            }));
        };
    }
}
//AbstraceGatewayFilterFactory : Gateway를 구현하기 위한 추상 클래스
//config : application.yml에 선언한 각 filter의 args 사용을 위한 클래스
//exchange : serverwebexchange 인스턴스로, http 엑세스를 제공. > 서비스 요청/응답값을 담고있는 변수, 요청/응답값을 출력하거나 반환할 때 사용

