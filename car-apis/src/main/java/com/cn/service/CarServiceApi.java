package com.cn.service;

import com.cn.model.CarBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CarServiceApi {
    @GetMapping("/soleCar")
    List<CarBean> soleCar();
    @RequestMapping("/addCar")
    Boolean addCar(@RequestBody CarBean carBean);
}
