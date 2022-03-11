package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p> Title: OrderMain85 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/9
 */

@SpringBootApplication
@EnableFeignClients
//@EnableHystrix继承了@EnableCircuitBreaker
@EnableHystrix
public class OrderMain85 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain85.class,args);
    }
}
