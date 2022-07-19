package kr.co.popool.bbleureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BblEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BblEurekaApplication.class, args);
    }

}
