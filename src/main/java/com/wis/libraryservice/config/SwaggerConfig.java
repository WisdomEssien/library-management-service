package com.wis.libraryservice.config;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wis.libraryservice"))
                .paths(PathSelectors.any())
                .build();
    }
	
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Book Library Application",
                "Spring Boot REST API for a Book Library Application",
                "1.0",
                "Terms of service",
                new Contact("Wisdom Essien", "https://github.com/WisdomEssien/library-management-service.git", "sirwise88@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
    
}
