package com.dagm.shorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.dagm.shorter", "com.dagm.api", "com.dagm.devtool"})
public class ShorterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShorterApplication.class, args);
    }
}
