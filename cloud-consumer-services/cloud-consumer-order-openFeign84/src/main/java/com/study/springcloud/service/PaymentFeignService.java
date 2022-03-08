package com.study.springcloud.service;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p> Title: PaymentFeignService </p>
 * <p> Description: </p>
 * Feign基础了ribbon和restTemplate
 *
 * @author lijialin
 * @since 2022-03-08
 */
@FeignClient("cloud-payment-service")
public interface PaymentFeignService {
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/queryById/{id}")
    public CommonResult queryById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
