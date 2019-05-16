package com.cn.controller;


import com.cn.mapper.TestMapper;
import com.cn.model.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;


    @GetMapping("/queryTest")
    public List<TestBean> queryTest() {
          System.out.println("查找userList");
        return testMapper.queryTest()   ;
    }



}
