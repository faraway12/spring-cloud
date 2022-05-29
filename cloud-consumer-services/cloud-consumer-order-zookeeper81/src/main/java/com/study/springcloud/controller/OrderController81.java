package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p> Title: OrderController81 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/16
 */
@RestController
@Slf4j
public class OrderController81 {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/zookeeper")
    public CommonResult<Payment> zookeeper(){
        return new CommonResult(200,"spring cloud zookeeper port:"+serverPort, UUID.randomUUID().toString());
    }
}
