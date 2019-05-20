package com.cn.controller.login;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.config.CommonConf;
import com.cn.config.ConstantConf;
import com.cn.mapper.login.LoginMapper;
import com.cn.model.login.UserBean;

import com.cn.utils.HttpClientUtil;
import com.cn.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    RedisTemplate redisTemplate;
    //根据手机号发送验证码 2
    @RequestMapping("findSmsCode")
    @ResponseBody
    public String findSmsCode(HttpSession session, String account ) throws Exception{

        HashMap<String, Object> result = new HashMap<>();
        //判断第二次请求时有没有加 一分钟内不能二次请求的锁
        Object lock = redisTemplate.opsForValue().get(ConstantConf.SMS_LOGIN_LOCK+account);
        if(lock!=null) {
            result.put("code", 2);
            result.put("msg","距离上次获取验证码不能超过一分钟!!!");
            return "距离上次获取验证码不能超过一分钟!!!" ;
        }

        HashMap<String, Object> params = new HashMap<>();
        //接受短信
        params.put("accountSid", ConstantConf.ACCOUNTSID);
        params.put("to",account);

        //验证码格式时间戳
        String timestamop= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        params.put("timestamp",timestamop);
        //Md5签名
        String  sig = Md5Util.getMd532(ConstantConf.ACCOUNTSID+ConstantConf.AUTH_TOKEN+timestamop);
        params.put("sig", sig);
        //模板ID TEMPLATEID
        params.put("templateid",ConstantConf.TEMPLATEID);
        //随机生成验证码
        Integer random = (int) (Math.random()*899999+100000);
        //存验证码 用手机号取
        //session.setAttribute(account, random);//存入session
        //获取短信验证码 存到缓存中
        redisTemplate.opsForValue().set(ConstantConf.SMS_LOGIN_CODE+account, random, ConstantConf.SMS_LOGIN_CODE_TIMES_OUT, TimeUnit.MINUTES);
        System.out.println("输出随机验证码"+random);
        params.put("param",random);
        String string = HttpClientUtil.post(ConstantConf.SMS_URL,params);////
        JSONObject parseObject = JSON.parseObject(string);
        String respCode = parseObject.getString("respCode");
        if(ConstantConf.SMS_SUCCESS.equals(respCode)) {
            //给当前用户加一个一分钟不能获取的锁
            redisTemplate.opsForValue().set(ConstantConf.SMS_LOGIN_LOCK+account, "lock",ConstantConf.SMS_LOGIN_LOCK_TIMES_OUT,TimeUnit.MINUTES);

            result.put("code", 0);
            result.put("msg","发送成功");
            return "发送短信验证码成功";
        }else {
            result.put("code", 1);
            result.put("msg","--发送失败");
            return "--发送失败";
        }

    }
    //获取短信验证码2
    @RequestMapping("kjLogin")
    @ResponseBody
    public String  kjLogin(String account,String smsCode,HttpSession session){

        HashMap<String,Object> result = new HashMap<>();
        //Object attribute = session.getAttribute(account);
        Object attribute = redisTemplate.opsForValue().get(ConstantConf.SMS_LOGIN_CODE+account);
        if(attribute ==null) {
            result.put("code", 1);
            result.put("msg", "验证码错误");
            return "验证码错误";
        }
        // 判断验证码是否正确
        //String string = session.getAttribute(account).toString();
        /*if(!smsCode.equals(attribute.toString())) {
            result.put("code",1);
            result.put("msg", "验证码错误");
            return "验证码错误";
        }*/

        //判断账号是否存在 根据手机号查询用户信息
        UserBean findUserByLogNumber = loginMapper.findUserInfo(account);

        if( findUserByLogNumber == null ) {
            result.put("code", 2);
            result.put("msg", "账号异常");
            return "账号异常";
        }
        //将用户信息保存到session中
        session.setAttribute(session.getId(), findUserByLogNumber);
        result.put("code", 0);
        result.put("msg", "登陆成功");

        return "登陆成功";
    }



////////////////
    //获取短信验证码
    @RequestMapping("gainMessgerCode")
    @ResponseBody
    public String gainMessgerCode(@RequestParam String account) {
        //验证手机号是否存在
        UserBean user = loginMapper.findUserByAccount(account);
        System.out.println(user);
        if(user==null){
            return "手机号不存在";
        }
       /* Object lock = redisTemplate.opsForValue().get(CommonConf.SMS_LOGIN_LOCK+account);
        if(lock!=null){
            return "一分钟内不能重复获取";
        }*/
        String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
        HashMap<String, Object> params = new HashMap<>();
        String accountSid = "0374867b2c1844dbbe0bf019bf0def28";
        params.put("accountSid", accountSid);//开发者主账号ID（ACCOUNT SID）
        params.put("templateid", "164547838");//短信模板ID
        //6位随机数，验证码
        int code = (int) Math.ceil(Math.random()*899999+100000);
        System.out.println("输出验证码"+code);
        /////存验证码


        //String codeStr = String.valueOf(code);
        params.put("param", code);//短信模板中的变量值
        params.put("to", account);//手机号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(new Date());
        params.put("timestamp", time);//时间戳
        String token = "d05d06f418974fc6aceb9233e38b7539";
        String sig = Md5Util.getMd532(accountSid+token+time);
        params.put("sig", sig);//签名
        //发送短信
        String returnStr = HttpClientUtil.post(url, params);
        JSONObject parseObject = JSON.parseObject(returnStr);
        String respCode = parseObject.getString("respCode");
        if(!respCode.equals("00000")){
            return "发送短信验证码失败";
        }
        //存验证码
       /* String key = "msgcode"+account;
        System.out.println(key);
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        //加一个锁60秒不能再次获取
        redisTemplate.opsForValue().set(CommonConf.SMS_LOGIN_LOCK+account,"lock",CommonConf.SMS_LOGIN_LOCK_TIME_OUT,TimeUnit.MINUTES);
*/
        return "发送短信验证码成功";
    }

    //短信验证码登录
    @RequestMapping("messageLogin")
    @ResponseBody
    public String messageLogin(String account, String messageCode, HttpSession session) {
        //验证手机号是否存在
        UserBean user = loginMapper.findUserByAccount(account);
        if(user==null && user.equals("")){
            return "手机号不存在";
        }
        HashMap<String, Object> result=new HashMap<>();


        //验证短信验证码是否正确
        //判断key是否存在
     /*   String key = "msgcode"+account;
        if(!redisTemplate.hasKey(key)){
            return "验证码错误";
        }
        int code = (int) redisTemplate.opsForValue().get(key);
        String codeStr = String.valueOf(code);
        if(!messageCode.equals(codeStr)){
            return "验证码错误";
        }*/
        //登录成功
        session.setAttribute("user", user);
      /*  //清redis缓存
        redisTemplate.delete(key);*/
        return "登录成功";
    }


    //注册
    @RequestMapping("reg")
    @ResponseBody
    public Boolean SaveUser(@RequestBody UserBean userBean) {
        try {
            int getAccount = loginMapper.findUserCountByAccount(userBean.getAccount());
            if (getAccount > 0) {
                return false;
            } else {
                userBean.setPassword(Md5Util.getMd516(userBean.getPassword()));
                userBean.setCreateTime(new Date());
                userBean.setStatus(1);
                loginMapper.SaveUser(userBean);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //登录
    @RequestMapping("login")
    @ResponseBody
  public HashMap<String, Object> login(@RequestBody UserBean userBean, HttpServletRequest request){
        HashMap<String, Object> result=new HashMap<>();
        HttpSession session=request.getSession();
        UserBean Account=loginMapper.findUserByAccount(userBean.getAccount());
        //判断账号是否存在
        if(Account ==null) {
            result.put("code", 2);
            result.put("msg", "账号不存在");
            return result;
        }
        //判断前台输入的密码和数据库的是否一致
        String password = userBean.getPassword();
        String md516 = Md5Util.getMd516(password);
        if(!Account.getPassword().equals(md516)) {
            result.put("code", 3);
            result.put("msg", "密码错误");
            return result;
        }
        session.setAttribute(session.getId(), Account);
        result.put("code", 0);
        result.put("msg", "登录成功");
        return result;
    }

}
