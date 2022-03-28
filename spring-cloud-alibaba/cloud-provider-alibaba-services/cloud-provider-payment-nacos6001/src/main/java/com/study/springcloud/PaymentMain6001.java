package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p> Title: PaymentMain6001 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain6001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain6001.class,args);
    }
}
