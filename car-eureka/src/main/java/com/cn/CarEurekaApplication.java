package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //代表开启eureka的注册中心服务
public class CarEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarEurekaApplication.class, args);
    }

}
