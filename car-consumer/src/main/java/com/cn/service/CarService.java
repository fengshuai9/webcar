package com.cn.service;

import com.cn.model.CarBean;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
@FeignClient(value = "car-provider")
public interface CarService extends CarServiceApi{


}
