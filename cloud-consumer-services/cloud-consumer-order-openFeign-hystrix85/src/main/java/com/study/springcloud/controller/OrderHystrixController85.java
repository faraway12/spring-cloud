package com.study.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> Title: OrderHystrixController85 </p>
 * <p> Description: </p>
 * 经测试，若提供方宕机的异常：
 * @FeignClient(value = "cloud-payment-hystrix-service",fallback = OrderHystrixFallbackService.class)的作用强于@HystrixCommand
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

    @HystrixCommand(fallbackMethod = "orderInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        return orderHystrixService.paymentInfo_TimeOut(id);
    }

    @GetMapping("/consumer/payment/hystrix/feign/timeout/{id}")
    public String paymentInfo_FeignTimeOut(@PathVariable("id") Long id){
        return orderHystrixService.paymentInfo_FeignTimeOut(id);
    }

    public String orderInfo_TimeOutHandler(Long id){
        return "线程池： "+Thread.currentThread().getName()+" 消费端85系统繁忙，请稍后重试,id: "+id+"\t"+"┭┮﹏┭┮";
    }

}
