package com.study.springcloud.service.impl;

import com.study.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p> Title: PaymentHystrixServiceImpl </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Long id) {
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        //三秒以内，正常；五秒以内，超时；五秒以上，异常。
        int time = 5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id: "+id+"\t"+"/(ㄒoㄒ)/~~"+" 耗时"+time+"秒";
    }
}
