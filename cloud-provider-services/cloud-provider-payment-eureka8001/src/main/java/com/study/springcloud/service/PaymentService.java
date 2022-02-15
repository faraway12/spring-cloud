package com.study.springcloud.service;

import com.study.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * <p> Title: PaymentService </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
public interface PaymentService {
    int create(Payment payment);
    Payment queryById(@Param("id")Long id);
}
