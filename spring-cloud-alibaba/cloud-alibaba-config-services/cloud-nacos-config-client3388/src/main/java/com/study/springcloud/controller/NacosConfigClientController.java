package com.study.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> Title: NacosConfigClientController </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-31
 */
@RestController
@RefreshScope //支持nacos动态刷新
public class NacosConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/nacos/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
