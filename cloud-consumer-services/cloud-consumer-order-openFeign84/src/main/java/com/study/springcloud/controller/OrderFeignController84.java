package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p> Title: OrderFeignController84 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-08
 */
@RestController
@Slf4j
public class OrderFeignController84 {

    @Resource
    private PaymentFeignService paymentFeignService;

    @PostMapping("/consumer/openFeign/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return paymentFeignService.create(payment);
    }

    @GetMapping("/consumer/openFeign/payment/query/{id}")
    public CommonResult<Payment> query(@PathVariable("id") Long id){
        return paymentFeignService.queryById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon客户端默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
