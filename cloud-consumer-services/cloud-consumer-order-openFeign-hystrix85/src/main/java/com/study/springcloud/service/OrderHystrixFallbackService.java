package com.study.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * <p> Title: OrderHystrixFallbackService </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-11
 */
@Component
public class OrderHystrixFallbackService implements OrderHystrixService{
    @Override
    public String paymentInfo_OK(Long id) {
        return "--OrderHystrixFallbackService-- fall back paymentInfo_OK /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "--OrderHystrixFallbackService-- fall back paymentInfo_TimeOut ┭┮﹏┭┮";
    }

    @Override
    public String paymentInfo_FeignTimeOut(Long id) {
        return "--OrderHystrixFallbackService-- fall back paymentInfo_FeignTimeOut ┭┮﹏┭┮";
    }
}
