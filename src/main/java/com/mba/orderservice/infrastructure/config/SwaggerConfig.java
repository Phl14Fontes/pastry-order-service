package com.mba.orderservice.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pastry Order Service")
                        .description("Microservice responsible for internally managing a pastry shop’s orders.")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                )
                .servers(Collections.singletonList(new Server().url("http://localhost:7000").description("LOCAL")));
    }
}
