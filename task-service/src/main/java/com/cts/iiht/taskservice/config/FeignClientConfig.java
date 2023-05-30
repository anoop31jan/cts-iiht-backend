package com.cts.iiht.taskservice.config;

import feign.auth.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableFeignClients
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "userPass");
    }

}
