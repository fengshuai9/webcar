package com.cn.controller.login;

import com.cn.model.login.UserBean;
import com.cn.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("user")
public class LoginController {



    @Autowired
    private LoginService loginService;


    //获取短信验证码2
    @RequestMapping("findSmsCode")
    @ResponseBody
    public String findSmsCode(String account ){
        return loginService.findSmsCode(account);
    }
    //短信验证码登录2
    @RequestMapping("kjLogin")
    @ResponseBody
    public String  kjLogin(String account,String smsCode) {
        return loginService.kjLogin(account,smsCode);
    }



    //获取短信验证码
    @RequestMapping("gainMessgerCode")
    @ResponseBody
    public String gainMessgerCode(String account){
        return loginService.gainMessgerCode(account);
    }
    //短信验证码登录
    @RequestMapping("messageLogin")
    @ResponseBody
    public String messageLogin(String account,String messageCode){
        return loginService.messageLogin(account,messageCode);
    }

    //注册
    @RequestMapping("reg")
    @ResponseBody
    public Boolean SaveUser(UserBean userBean) {
        try {
            Boolean saveUser = loginService.SaveUser(userBean);
            return saveUser;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //登录
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(UserBean userBean){
        return loginService.login(userBean);
    }

}
