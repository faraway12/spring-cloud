package com.study.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * <p> Title: LoadBalancer </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-07
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}
