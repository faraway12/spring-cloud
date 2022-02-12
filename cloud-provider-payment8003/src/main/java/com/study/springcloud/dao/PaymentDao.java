package com.study.springcloud.dao;

import com.study.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p> Title: PaymentDao </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
@Mapper
public interface PaymentDao {
    int add(Payment payment);
    Payment findById(@Param("id")Long id);
}
