package kr.co.popool.bblmember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class BblMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(BblMemberApplication.class, args);
    }

}
