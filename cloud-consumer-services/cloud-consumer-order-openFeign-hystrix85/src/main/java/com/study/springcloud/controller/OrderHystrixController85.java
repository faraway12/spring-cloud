package com.study.springcloud.controller;

import com.study.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p> Title: OrderHystrixController85 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-08
 */
@RestController
@Slf4j
public class OrderHystrixController85 {

    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        return orderHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        return orderHystrixService.paymentInfo_TimeOut(id);
    }
}
