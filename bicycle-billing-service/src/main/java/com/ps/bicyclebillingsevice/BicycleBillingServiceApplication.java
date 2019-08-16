package com.ps.bicyclebillingsevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author VP
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BicycleBillingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BicycleBillingServiceApplication.class,args);
    }
}
