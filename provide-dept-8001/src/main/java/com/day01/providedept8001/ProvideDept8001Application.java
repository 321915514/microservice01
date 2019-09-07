package com.day01.providedept8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProvideDept8001Application {

    public static void main(String[] args) {
        SpringApplication.run(ProvideDept8001Application.class, args);
    }

}
