package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentFeignHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p> Title: OrderFeignController85 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-08
 */
@RestController
@Slf4j
public class OrderFeignHystrixController85 {

    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        return paymentFeignHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        return paymentFeignHystrixService.paymentInfo_TimeOut(id);
    }
}
