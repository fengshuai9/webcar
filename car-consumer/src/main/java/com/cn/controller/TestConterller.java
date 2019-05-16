package com.cn.controller;

import com.cn.model.TestBean;
import com.cn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestConterller {


    @Autowired
    private TestService testService;



    @GetMapping("/queryTest")
    public List<TestBean> queryTest(){
        System.out.println("查找userList");
        List<TestBean> list= testService.queryTest();
        return list;
    };



}
