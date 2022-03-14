package com.study.springcloud.controller;

import com.study.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> Title: PaymentController </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("***result: "+result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("***result: "+result);
        return result;
    }

    @GetMapping("/payment/hystrix/circuitBreaker/{id}")
    public String paymentInfo_CircuitBreaker(@PathVariable("id") Long id){
        String result = paymentHystrixService.paymentInfo_CircuitBreaker(id);
        log.info("***result: "+result);
        return result;
    }
}