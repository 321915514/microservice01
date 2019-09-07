package com.day01.microservicecloudeuraka7002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Microservicecloudeuraka7002Application {

    public static void main(String[] args) {
        SpringApplication.run(Microservicecloudeuraka7002Application.class, args);
    }

}
