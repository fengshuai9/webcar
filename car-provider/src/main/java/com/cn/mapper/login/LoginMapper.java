package com.cn.mapper.login;

import com.cn.model.login.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface LoginMapper {

    @Select("select * from ez_user where account = #{value}")
    UserBean findUserInfo(String account);

    //登陆
    @Select("select * from ez_user where account = #{value}")
    UserBean findUserByAccount(String account);


    //注册
    @Select("select count(1) from ez_user where account = #{value}")
    int findUserCountByAccount(String account);

    @Insert("insert into ez_user(account , password , status ,createTime)values(#{account} , #{password} , 1 , #{createTime})")
    void SaveUser(UserBean userBean);



}
