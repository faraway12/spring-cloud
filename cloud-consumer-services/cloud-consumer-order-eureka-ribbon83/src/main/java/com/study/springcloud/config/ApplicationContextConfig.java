package com.study.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p> Title: ApplicationContextConfig </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/9
 */
@Configuration
public class ApplicationContextConfig {
    @Bean("restTemplateRibbon")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
