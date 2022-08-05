package kr.co.popool.bblmember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BblMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(BblMemberApplication.class, args);
    }

}
