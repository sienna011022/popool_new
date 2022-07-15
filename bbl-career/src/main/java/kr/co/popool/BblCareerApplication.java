package kr.co.popool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BblCareerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BblCareerApplication.class, args);
	}

}
