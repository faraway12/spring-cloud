package com.study.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.study.springcloud.entities.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * <p> Title: OrderController83 </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022/2/16
 */
@RestController
@Slf4j
public class OrderController83 {

    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    @Qualifier("restTemplateRibbon")
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/payment/query/getForEntity/{id}")
    public CommonResult<Payment> query(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/queryById/"+id,CommonResult.class);
        log.info(JSON.toJSONString(entity));
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/consumer/payment/discovery")
    public Object discovery(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("cloud-payment-service");
        for(ServiceInstance instance : serviceInstanceList){
            log.info(JSON.toJSONString(instance));
        }
        return this.discoveryClient;
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("cloud-payment-service");
        if(serviceInstanceList==null || serviceInstanceList.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(serviceInstanceList);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
