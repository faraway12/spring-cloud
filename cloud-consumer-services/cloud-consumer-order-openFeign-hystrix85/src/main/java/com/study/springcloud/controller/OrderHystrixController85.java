package com.study.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    //此处是一个坑！！ feign+hystrix必须在yml中配置默认hystrix超时时间，在@HystrixProperty注解中配置的时间才起作用，否则最大超时时间只能为1秒
    @HystrixCommand(fallbackMethod = "orderInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        return orderHystrixService.paymentInfo_TimeOut(id);
    }

    public String orderInfo_TimeOutHandler(Long id){
        return "线程池： "+Thread.currentThread().getName()+" 消费端85系统繁忙，请稍后重试,id: "+id+"\t"+"┭┮﹏┭┮";
    }
}
