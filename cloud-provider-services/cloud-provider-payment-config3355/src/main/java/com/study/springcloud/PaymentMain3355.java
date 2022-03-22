package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p> Title: PaymentMain3355 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-22
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain3355.class,args);
    }
}
