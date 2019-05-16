package com.cn.mapper;


import com.cn.model.TestBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestMapper {

    @Select("select * from t_test")
    List<TestBean> queryTest();
}
