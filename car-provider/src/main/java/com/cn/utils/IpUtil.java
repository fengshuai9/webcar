/** 
 * <pre>项目名称:ssm-wjj 
 * 文件名称:IpUtil.java 
 * 包名:com.jk.wjj.utils 
 * 创建日期:2019年3月7日下午4:10:12 
 * Copyright (c) 2019, 1207727105@qq.com All Rights Reserved.</pre> 
 */  
package com.cn.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/** 
 * <pre>项目名称：ssm-wjj    
 * 类名称：IpUtil    
 * 类描述：    
 * 创建人：王京京  
 * 创建时间：2019年3月7日 下午4:10:12    
 * 修改人：王京京
 * 修改时间：2019年3月7日 下午4:10:12    
 * 修改备注：       
 * @version </pre>    
 */
public class IpUtil {
	//获取当前网络IP
	public static String getIpAddr(HttpServletRequest request){ 
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) { 
			ipAddress = request.getHeader("Proxy-Client-IP");              }      
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {                  ipAddress = request.getHeader("WL-Proxy-Client-IP");              }              if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
			ipAddress = request.getRemoteAddr();                
			if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){ 
				//根据网卡取本机配置的IP      
				InetAddress inet=null;                     
				try {              
				   inet = InetAddress.getLocalHost(); 
				} catch (UnknownHostException e) {                
					e.printStackTrace();      
					}                     
				ipAddress= inet.getHostAddress();                 
				}         
			}  
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割         
		if(ipAddress!=null && ipAddress.length()>15){ 
			//"***.***.***.***".length() = 15             
			if(ipAddress.indexOf(",")>0){ 
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));         
				}          
			}             
		return ipAddress;     
		}
}
