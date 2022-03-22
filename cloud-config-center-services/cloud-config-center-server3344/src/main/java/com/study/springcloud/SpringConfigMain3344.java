package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p> Title: SpringConfigMain3344 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/3/22
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class SpringConfigMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(SpringConfigMain3344.class,args);
    }
}
