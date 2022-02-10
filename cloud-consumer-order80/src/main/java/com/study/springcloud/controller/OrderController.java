package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p> Title: OrderController </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/query/{id}")
    public CommonResult<Payment> query(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/queryById/"+id,CommonResult.class);
    }
}
