package com.study.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p> Title: OrderHystrixService </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-08
 */
@FeignClient("cloud-payment-hystrix-service")
public interface OrderHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id);
}
