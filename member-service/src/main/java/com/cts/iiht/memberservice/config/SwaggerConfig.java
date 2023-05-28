package com.cts.iiht.memberservice.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cts.iiht.memberservice"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {

        return new ApiInfo("Member Service APIs for Project tracker Application",
                "Project Tracker Application",
                "1.0",
                "free to use",
                new Contact("Anoop Pandey", "https://projectTracker.iiht.in", "anoop31jan2@gmail.com"),
                "API License",
                "",
                Collections.emptyList()
        );


    }
}
