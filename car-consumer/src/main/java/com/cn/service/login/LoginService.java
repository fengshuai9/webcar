package com.cn.service.login;

import com.cn.model.login.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@FeignClient("car-provider")
public interface LoginService {

    @RequestMapping("findSmsCode")
    String findSmsCode(@RequestParam String account);
    @RequestMapping("kjLogin")
    String kjLogin(@RequestParam String account, @RequestParam  String smsCode);


    @RequestMapping("gainMessgerCode")
    String gainMessgerCode(@RequestParam String account);

    @RequestMapping("messageLogin")
     String messageLogin(@RequestParam String account, @RequestParam String messageCode);
    //注册
    @RequestMapping("reg")
    Boolean SaveUser(@RequestBody UserBean userBean);
    //登录
    @RequestMapping("login")
    HashMap<String, Object> login(@RequestBody UserBean userBean);


}
