package com.study.springcloud;

import com.study.ribbonRule.Rule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * <p> Title: OrderMain81 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/9
 */
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "cloud-payment-service",configuration = Rule.class)
public class OrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain83.class,args);
    }
}
