package com.mba.orderservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@SpringBootApplication
@EnableScheduling
@CrossOrigin("*")
@EnableJpaRepositories(basePackages = "com.mba.orderservice.infrastructure.adapter.out.dao")
@EntityScan(basePackages = "com.mba.orderservice.infrastructure.adapter.out.repository.entity")
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
