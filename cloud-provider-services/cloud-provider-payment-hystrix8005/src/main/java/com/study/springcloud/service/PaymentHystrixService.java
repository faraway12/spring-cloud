package com.study.springcloud.service;

/**
 * <p> Title: PaymentHystrixService </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
public interface PaymentHystrixService {
    public String paymentInfo_OK(Long id);

    public String paymentInfo_TimeOut(Long id);

    public String paymentInfo_CircuitBreaker(Long id);
}
