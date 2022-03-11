package com.study.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> Title: OrderHystrixGlobalController85 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-11
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "orderInfo_GlobalHandler",commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")
})
public class OrderHystrixGlobalController85 {

    @Resource
    private OrderHystrixService orderHystrixService;

    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/global/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        return orderHystrixService.paymentInfo_OK(id);
    }

    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/global/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        return orderHystrixService.paymentInfo_TimeOut(id);
    }

    //默认fallback 注意默认fallback只支持无参方法
    public String orderInfo_GlobalHandler(){
        return "线程池： "+Thread.currentThread().getName()+" 消费端85系统繁忙(默认处理)，请稍后重试\t"+"┭┮﹏┭┮";
    }
}
