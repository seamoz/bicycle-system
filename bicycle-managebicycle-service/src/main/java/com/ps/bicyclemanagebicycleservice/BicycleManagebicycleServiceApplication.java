package com.ps.bicyclemanagebicycleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author VP
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BicycleManagebicycleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BicycleManagebicycleServiceApplication.class, args);
    }

}
