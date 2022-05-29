package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * <p> Title: OrderController86 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/16
 */
@RestController
@Slf4j
public class OrderController86 {

    public static final String PAYMENT_URL = "http://cloud-payment-service-sleuth-zipkin";

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/eureka")
    public CommonResult<Payment> eureka(){
        return new CommonResult(200,"spring cloud eureka port:"+serverPort, UUID.randomUUID().toString());
    }

    @GetMapping("/sleuth-zipkin/consumer/payment/query/{id}")
    public CommonResult<Payment> query(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/sleuth-zipkin/payment/queryById/"+id,CommonResult.class);
    }
}
