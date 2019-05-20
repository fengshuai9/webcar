package com.cn.service.login;

import com.cn.model.login.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@FeignClient("car-provider")
public interface LoginService {
    //获取短信验证码2
    @RequestMapping("findSmsCode")
    String findSmsCode(@RequestParam String account);
    //短信验证码登录2
    @RequestMapping("kjLogin")
    String kjLogin(@RequestParam String account, @RequestParam  String smsCode);



    //获取短信验证码
    @RequestMapping("gainMessgerCode")
    String gainMessgerCode(@RequestParam String account);
    //短信验证码登录
    @RequestMapping("messageLogin")
     String messageLogin(@RequestParam String account, @RequestParam String messageCode);
    //注册
    @RequestMapping("reg")
    Boolean SaveUser(@RequestBody UserBean userBean);
    //登录
    @RequestMapping("login")
    HashMap<String, Object> login(@RequestBody UserBean userBean);


}
