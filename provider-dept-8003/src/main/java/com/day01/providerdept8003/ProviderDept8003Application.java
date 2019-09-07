package com.day01.providerdept8003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProviderDept8003Application {

    public static void main(String[] args) {
        SpringApplication.run(ProviderDept8003Application.class, args);
    }

}
