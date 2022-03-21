package com.study.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

/**
 * <p> Title: GatewayConfig </p>
 * <p> Description: </p>
 *
 * @author lijialin
 * @since 2022-03-21
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        return routes.route("path_route_baidu",r->r.path("/guonei").and().after(ZonedDateTime.now()).uri("http://news.baidu.com")).build();

//        return routes.route("path_route_baidu",r->r.path("/guonei").uri("http://news.baidu.com")).build();
    }
}
