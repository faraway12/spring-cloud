package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p> Title: OrderMain60 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/9
 */
@SpringBootApplication()
@EnableDiscoveryClient
public class OrderMain60 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain60.class,args);
    }
}
