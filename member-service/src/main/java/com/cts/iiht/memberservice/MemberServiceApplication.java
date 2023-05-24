package com.cts.iiht.memberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
@EnableEurekaClient
public class MemberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApplication.class, args);
	}

}
