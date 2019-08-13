package com.ps.bicycleh5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BicycleH5AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BicycleH5AppApplication.class, args);
    }

}
