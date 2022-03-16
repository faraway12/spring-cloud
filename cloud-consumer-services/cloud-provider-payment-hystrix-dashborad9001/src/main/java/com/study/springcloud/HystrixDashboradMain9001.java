package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * <p> Title: HystrixDashboradMain9001 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-16
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboradMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboradMain9001.class,args);
    }
}
