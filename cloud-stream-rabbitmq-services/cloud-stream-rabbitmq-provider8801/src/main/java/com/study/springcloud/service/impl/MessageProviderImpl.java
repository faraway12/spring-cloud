package com.study.springcloud.service.impl;

import com.study.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * <p> Title: MessageProviderImpl </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-24
 */
@EnableBinding(Source.class)//定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        return null;
    }
}
