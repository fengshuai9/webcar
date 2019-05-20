package com.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient //代表eureka客户端注册到注册中心
@MapperScan("com.cn.mapper")
public class CarProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarProviderApplication.class, args);
    }


}
