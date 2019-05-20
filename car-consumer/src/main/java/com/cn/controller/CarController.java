package com.cn.controller;

import com.cn.model.CarBean;
import com.cn.model.TestBean;
import com.cn.service.CarService;
import com.cn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/car")
@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping("/soleCar")
    public List<CarBean> soleCar(){
        List<CarBean> list= carService.soleCar();
        return list;
    }
    @RequestMapping("addCar")
    public Boolean addCar(CarBean carBean){
        try {
            carService.addCar(carBean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
