package com.study.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * <p> Title: ApplicationContextConfigNacos </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-29
 */
@Configuration
public class ApplicationContextConfigNacos {
    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate getNacosRestTemplate(){
        return new RestTemplate();
    }
}
