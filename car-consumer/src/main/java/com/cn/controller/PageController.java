package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("toceshi")
    public String  toceshi(){
        System.out.println("540003");
        return "ceshi";
    }



}
