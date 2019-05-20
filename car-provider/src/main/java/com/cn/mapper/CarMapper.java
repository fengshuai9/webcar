package com.cn.mapper;

import com.cn.model.CarBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CarMapper {
    @Select("select * from t_car")
    List<CarBean> soleCar();
    @Insert("insert into t_car(location,btime,mileage,price,image) values(#{location},#{btime},#{mileage},#{price},#{image})")
    void addCar(CarBean carBean);
}
