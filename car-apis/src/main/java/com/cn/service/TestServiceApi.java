package com.cn.service;

import com.cn.model.TestBean;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface TestServiceApi {

    @GetMapping("/queryTest")
    List<TestBean> queryTest();
}
