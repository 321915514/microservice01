package com.day01.microservicecloudeureka7003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Microservicecloudeureka7003Application {

    public static void main(String[] args) {
        SpringApplication.run(Microservicecloudeureka7003Application.class, args);
    }

}
