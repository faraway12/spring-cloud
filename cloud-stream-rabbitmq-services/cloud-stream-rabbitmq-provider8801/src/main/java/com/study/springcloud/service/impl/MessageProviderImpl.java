package com.study.springcloud.service.impl;

import com.study.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * <p> Title: MessageProviderImpl </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-24
 */
@Slf4j
@EnableBinding(Source.class) //Source.class会注入指定名为output的通道 我们可以参考这个接口进行自定义
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*****serial: "+serial);
        return null;
    }
}
