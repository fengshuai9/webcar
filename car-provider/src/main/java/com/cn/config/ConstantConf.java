package com.cn.config;

public class ConstantConf {

	///左侧菜单 权限树  导航树
	public static final String USER_TREE_LIST = "userTreeList";
	
	//userpower 用户权限缓存key
	public  static final String USER_POWER_CACHE_KEY = "userPowerCache";
	
	//缓存左侧导航树
	public static final String USER_NAV_TREE_ZUO ="zuoNavTree";
	
	//下拉框 和用户看到的没关系  所以写一个死值
	//部门下拉树缓存key
	public static final String DEPT_CACHE_KEY = "dept";
	//
	/*//部门下拉树缓存时间 单位（天）
	public static final Long DEPT_CACHE_TIME = 30L ;*/
	//部门下拉树缓存时间 单位（30分钟）
	public static final Integer DEPT_CACHE_TIME = 30 ;
	
	//二级联动缓存key
    public static final String AREA_CACHE_KEY = "area";
    
    //rolekey
    public static final String ROLE_CACHE_KEY = "role";
    
	/**
	 * 快递鸟接口地址
	 */
	public static final String KDN_URL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
	
	public static final String KDN_EBUSINESSID = "1463503";
	
	public static final String KDN_API_KEY = "170bf7a1-a93e-4ea7-90b5-073c08fee43b";
	
	public static final String KDN_REUEST_TYPE = "1002";
	
	// 天气的缓存key
	public static final String WEATHER_CACHE_KEY = "weather";

	// 天气的缓存时间 单位 小时
	public static final Integer WEATHER_CACHE_TIME = 1;

	public static final String WEATHER_PATH = "http://t.weather.sojson.com/api/weather/city";

	public static String WEATHER_RESULT = "今天是{0},{1},天气{2},{3},{4},风向{5},风力{6},日出时间{7},日落时间{8},注意：{9}";
	// 天气
	public static final String WEATHER_LIST = "weatherList";
	// 天气缓存时间
	public static final Integer WEATHER_LIST_TIME_OUT = 1;
	
	//机器人
	 public static final String SEND_MSG_ROBOT = "http://api.qingyunke.com/api.php";

	  //短信接口
	  public static final String SMS_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	 
	 //短信平台ACCOUNTSID
		public static final String ACCOUNTSID = "0374867b2c1844dbbe0bf019bf0def28";
		
		//短信平台AUTH_TOKEN
		public static final String AUTH_TOKEN = "d05d06f418974fc6aceb9233e38b7539";
		
		//短信模板ID
		public static final String TEMPLATEID = "164547838";;
		
		 public static final String SMS_SUCCESS="00000";
		 
		 //登录验证码
		 public static final String SMS_LOGIN_CODE = "dlyzm";

		 //登录锁
		 public static final String SMS_LOGIN_LOCK = "lock";
		 
		 //短信验证码有效期5min
		 public static final Integer SMS_LOGIN_CODE_TIMES_OUT = 5;
	     
		 //短信验码锁一分钟
		 public static final Integer SMS_LOGIN_LOCK_TIMES_OUT = 1;
		 

}
