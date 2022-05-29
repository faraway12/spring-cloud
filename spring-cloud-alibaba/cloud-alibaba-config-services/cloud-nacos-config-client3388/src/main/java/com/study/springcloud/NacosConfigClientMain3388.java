package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p> Title: NacosConfigClientMain3388 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClientMain3388 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3388.class,args);
    }
}
