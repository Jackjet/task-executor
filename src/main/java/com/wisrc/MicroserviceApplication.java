package com.wisrc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableDiscoveryClient
@EnableJpaRepositories(
        basePackages = {"com.wisrc.microservice.dao","com.wisrc.batch.dao"}
)
@EnableFeignClients(
        basePackages = {"com.wisrc.microservice.feign"}
)
public class MicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

}