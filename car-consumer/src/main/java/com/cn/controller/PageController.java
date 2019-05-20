package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {
    //主页面(个人中心页面)
    @RequestMapping("toMain")
    public String  toMain(){
        return "main";
    }

    //卖车页面
    @RequestMapping("toMaiche")
    public String toMaiche(){
        return "SoleCar";
    }

    //卖车成功页面
    @RequestMapping("toMai")
    public String toMai(){
        return "success";
    }
}
