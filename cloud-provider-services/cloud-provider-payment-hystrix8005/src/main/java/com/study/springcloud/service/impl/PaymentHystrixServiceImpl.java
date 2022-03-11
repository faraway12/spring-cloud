package com.study.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Long id) {
//        int num = 10/0;
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_TimeOut,id: "+id+"\t"+"/(ㄒoㄒ)/~~"+" 耗时"+time+"秒";
    }

    public String paymentInfo_TimeOutHandler(Long id){
        return "线程池： "+Thread.currentThread().getName()+" 8005系统繁忙，请稍后重试,id: "+id+"\t"+"┭┮﹏┭┮";
    }
}
