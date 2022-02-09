package com.study.springcloud.controller;

import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p> Title: PaymentController </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-02-09
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("创建完成{}",result);
        if(result>0){
            return new CommonResult(200,"创建成功");
        }else{
            return new CommonResult(444,"创建失败");
        }
    }

    @GetMapping("/payment/queryById/{id}")
    public CommonResult queryById(@PathVariable("id") Long id){
        Payment payment = paymentService.queryById(id);
        log.info("查询完成{}",payment);
        if(payment!=null){
            return new CommonResult<Payment>(200,"查询成功",payment);
        }else{
            return new CommonResult(444,"未查询到相应记录");
        }
    }

}
