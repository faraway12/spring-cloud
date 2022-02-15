package com.study.springcloud.service.impl;

import com.study.springcloud.dao.PaymentDao;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> Title: PaymentServiceImpl </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    //@Autowired会飘红线警告，使用@Resource可以解决
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment queryById(Long id) {
        return paymentDao.findById(id);
    }
}
