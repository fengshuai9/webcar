package com.cn.controller;

import com.cn.mapper.CarMapper;
import com.cn.mapper.TestMapper;
import com.cn.model.CarBean;
import com.cn.model.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarMapper carMapper;

    @GetMapping("/soleCar")
    public List<CarBean> soleCar() {
        return carMapper.soleCar();
    }
    @RequestMapping("/addCar")
    @ResponseBody
    public void addCar(@RequestBody CarBean carBean){
        System.out.println(carBean);

        carMapper.addCar(carBean);
    }

}