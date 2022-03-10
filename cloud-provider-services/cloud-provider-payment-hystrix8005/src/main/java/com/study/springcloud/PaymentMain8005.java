package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p> Title: PaymentMain8005 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-08
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8005.class,args);
    }
}