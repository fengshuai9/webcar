/** 
 * <pre>项目名称:mav_1 
 * 文件名称:CommonConf.java 
 * 包名:com.jk 
 * 创建日期:2019年3月8日下午3:44:28 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.cn.config;

/** 
 * <pre>项目名称：mav_1    
 * 类名称：CommonConf    
 * 类描述：    
 * 创建人：于汇川 
 * 创建时间：2019年3月8日 下午3:44:28    
 * 修改人：于汇川 
 * 修改时间：2019年3月8日 下午3:44:28    
 * 修改备注：       
 * @version </pre>    
 */
//公共常量类
public class CommonConf {
public static final String WEATHER_PATH ="http://t.weather.sojson.com/api/weather/city";
public static  String	WEATHER_RESULT ="今天是{0},{1},天气{2},{3},{4},风向{5},风力{6},日出时间{7},日落时间{8},注意：{9}";
public static final String WULIU_PATH ="http://v.juhe.cn/exp/index";
public static final String JIQIREN_PATH ="http://api.qingyunke.com/api.php?key=free&appid=0&msg=";
public static final String JINTIAN_PATH ="http://api.juheapi.com/japi/toh";
public static final String SMS_LOGIN_LOCK ="lock";
//天气
public static final String WEATHER_LIST="weatherList";
//天气缓存时间
public static final Integer WEATHER_LIST_TIME_OUT=1;
//短信验证码锁  1 分钟
public static final Integer SMS_LOGIN_LOCK_TIME_OUT =1;
//用户权限列表
public static final String USER_POWER_LIST ="userPowerList";
//左侧树列表
public static final String USER_NAV_LIST ="userNavList";

//头条接口
public static final String TOUTIAO_PATH ="http://v.juhe.cn/toutiao/index";

}
