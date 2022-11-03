package kr.co.popool.bblpayment;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableBatchProcessing
@EnableFeignClients
@SpringBootApplication
public class BblPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BblPaymentApplication.class, args);
    }

}
