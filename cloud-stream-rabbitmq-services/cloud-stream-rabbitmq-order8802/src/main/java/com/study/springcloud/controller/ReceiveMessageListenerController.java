package com.study.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * <p> Title: ReceiveMessageListenerController </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-25
 */
@Slf4j
@Component
@EnableBinding(Sink.class) //Sink.class会注入指定名为input的通道 我们可以参考这个接口进行自定义
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        log.info("消费者1号------->接收到信息： "+message.getPayload()+"\t port: "+serverPort);
    }
}
