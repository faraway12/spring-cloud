package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p> Title: OrderMain86 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/9
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderMain86 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain86.class,args);
    }
}
