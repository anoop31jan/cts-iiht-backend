package com.cts.iiht.taskservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){

        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("OPTIONS","GET", "POST", "PUT", "DELETE")
                .allowCredentials(false)
                .maxAge(3600);

    }

}
