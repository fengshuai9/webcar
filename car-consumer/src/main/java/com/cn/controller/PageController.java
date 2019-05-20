package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("toceshi")
    public String  toceshi(){
        return "ceshi";
    }
    //登陆
    @RequestMapping("tologin")
    public String  tologin(){
        return "login";
    }
    //注册
    @RequestMapping("toreg")
    public String  toreg(){
        return "reg";
    }
    //快捷登录
    @RequestMapping("tokjdl")
    public String  tokjdl(){
        return "kjdl";
    }
    //展示页面
    @RequestMapping("toshow")
    public String  toshow(){
        return "show";
    }
    @RequestMapping("tokjdl2")
    public String  tokjdl2(){
        return "kjdl2";
    }



}
