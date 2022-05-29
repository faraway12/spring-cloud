package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p> Title: PaymentMain6003 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-04-06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain6003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain6003.class,args);
    }
}
