package com.study.springcloud.controller;

import com.study.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> Title: SendMessageController </p>
 * <p> Description: </p>
 *
 * 微服务集群应用放置于同一个group中，能够保证消息只会被其中一个应用消费一次。
 * 不同的组是可以多次消费的(topic模式)
 *
 * @author lijialin
 * @since 2022/3/24
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
