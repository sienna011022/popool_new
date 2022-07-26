package kr.co.popool.bblmember.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_NAME = "Popool API";
    private static final String API_VERSION = "2.0";
    private static final String API_DESCRIPTION = "Popool 서버 API 문서";

    //API 정보, 보안 컨텍스트 및 보안 체계를 포함하도록 API Docket 빈을 구성합니다.
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())                                 // API 문서에 대한 내용
                .securityContexts(Arrays.asList(securityContext())) // swagger에서 jwt 토큰값 넣기위한 설정
                .securitySchemes(Arrays.asList(apiKey()))           // swagger에서 jwt 토큰값 넣기위한 설정
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_NAME)                // API 이름지정
                .version(API_VERSION)           // API 버전
                .description(API_DESCRIPTION)   // API 설명
                .contact(new Contact("Popool", "https://github.com/HyuckjuneHong", "jjj8337721@naver.com"))
                .build();
    }

    // swagger에서 jwt 토큰값 넣기위한 설정( JWT를 인증 헤더로 포함하도록 ApiKey 를 정의해야 함)
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    //전역 AuthorizationScope 를 사용하여 JWT SecurityContext 를 구성.
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
