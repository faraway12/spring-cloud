package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p> Title: OrderMain84 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/9
 */

@SpringBootApplication
@EnableFeignClients
public class OrderMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain84.class,args);
    }
}
