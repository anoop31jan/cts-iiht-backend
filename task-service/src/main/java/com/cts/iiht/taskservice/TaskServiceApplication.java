package com.cts.iiht.taskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.*;

@SpringBootApplication
@EnableFeignClients
public class TaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
