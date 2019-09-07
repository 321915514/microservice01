package com.day01.microservicecloudeureka7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Microservicecloudeureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Microservicecloudeureka7001Application.class, args);
    }

}
