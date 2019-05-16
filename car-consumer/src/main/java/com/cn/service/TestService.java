package com.cn.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "car-provider")
public interface TestService extends TestServiceApi {
}
