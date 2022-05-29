package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p> Title: OrderController80 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/16
 */
@RestController
@Slf4j
public class OrderController80 {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/eureka")
    public CommonResult<Payment> eureka(){
        return new CommonResult(200,"spring cloud eureka port:"+serverPort, UUID.randomUUID().toString());
    }
}
