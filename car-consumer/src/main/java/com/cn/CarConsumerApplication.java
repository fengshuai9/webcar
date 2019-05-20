package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient //代表eureka客户端注册到注册中心
@EnableDiscoveryClient
@EnableFeignClients //开启feign
@EnableHystrix
public class CarConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    //开启负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
