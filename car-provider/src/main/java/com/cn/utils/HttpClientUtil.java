package com.cn.utils;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 
 * 类: HttpClient <br>
 * 描述: httpclient工具类 <br>
 * 作者: song<br>
 * 时间: 2017年7月21日 下午3:15:27
 */
public class HttpClientUtil {
	
	static CloseableHttpClient client = null;
	static {
		client = HttpClients.createDefault();
	}
	
	/**
	 * 
	 * 方法: get <br>
	 * 描述: get请求 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年7月21日 下午3:15:25
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String get(String url,HashMap<String, Object> params){
		try {
			HttpGet httpGet = new HttpGet();
			Set<String> keySet = params.keySet();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(url).append("?t=").append(System.currentTimeMillis());
			for (String key : keySet) {
				stringBuffer.append("&").append(key).append("=").append(params.get(key));
			}
			httpGet.setURI(new URI(stringBuffer.toString()));
			CloseableHttpResponse execute = client.execute(httpGet);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * 方法: post <br>
	 * 描述: post请求 <br>
	 * 作者: Teacher song<br>
	 * 时间: 2017年7月21日 下午3:20:31
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String post(String url,HashMap<String, Object> params) {
		try {
			HttpPost httpPost = new HttpPost();
			httpPost.setURI(new URI(url));
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
				parameters.add(e);
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters , "utf-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse execute = client.execute(httpPost);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
/*	public static void main(String[] args) {
		String url = "http://t.weather.sojson.com/api/weather/city/101030100";
		HashMap<String, Object> params = new HashMap<>();
		String string = get(url, params );
		//System.out.println(string);
		JSONObject repdate = JSON.parseObject(string);
		JSONObject data = repdate.getJSONObject("data");
		JSONArray forecast = data.getJSONArray("forecast");
		JSONObject jsonObject = forecast.getJSONObject(0);
		String high = jsonObject.getString("high");
		System.out.println(high);
	}*/
	
	/*public static void main(String[] args) {
		String url = CommonCanstant.SEND_MSG_URL;
		HashMap<String, Object> params = new HashMap<>();
		params.put("accountSid", CommonCanstant.SEND_MSG_ACCOUNT_ID);//开发者主账号ID
		params.put("to","16601237127");//短信接收端手机号码集合
		String time = TimeUtil.format(new Date());
		params.put("timestamp",time);//时间戳
		String sigStr = CommonCanstant.SEND_MSG_ACCOUNT_ID+CommonCanstant.SEND_MSG_TOKEN+time;
		params.put("sig",Md5Util.getMd532(sigStr));//签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）
		params.put("templateid", "1116707112");//短信模板ID
		params.put("param", "123456");//短信模板ID
		String post = post(url, params );
		System.out.println(post);
	}*/
	public static long code(String phone) {
//		String url = CommonCanstant.SEND_MSG_URL;
//		HashMap<String, Object> params = new HashMap<>();
//		params.put("accountSid", CommonCanstant.SEND_MSG_ACCOUNT_ID);//开发者主账号ID
//		params.put("to",phone);//短信接收端手机号码集合
//		String time = TimeUtil.format(new Date());
//		params.put("timestamp",time);//时间戳
//		String sigStr = CommonCanstant.SEND_MSG_ACCOUNT_ID+CommonCanstant.SEND_MSG_TOKEN+time;
//		params.put("sig",Md5Util.getMd532(sigStr));//签名。MD5(ACCOUNT SID + AUTH TOKEN + timestamp)。共32位（小写）
//		params.put("templateid", "1116707112");//短信模板ID
		long str = Math.round(Math.random()*899999+100000);
//		params.put("param", str);//短信模板ID
//		String post = post(url, params );
//		System.out.println(post);
		return str;
	}
	
	public static void main(String[] args) {
		String url="http://localhost:8080/ssm-employee-wdd/book/queryBookType.do";
		HashMap<String, Object> params = new HashMap<>();
		String string = get(url, params);
		JSONArray parseArray = JSONArray.parseArray(string);
		System.out.println(string);
	}


	//直接传参
		public static String get2(String url,Object params){
			try {
				HttpGet httpGet = new HttpGet();		
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(url).append("/").append(params);
				httpGet.setURI(new URI(stringBuffer.toString()));
				CloseableHttpResponse execute = client.execute(httpGet);
				int statusCode = execute.getStatusLine().getStatusCode();
				if (200 != statusCode) {
					return "";
				}
				return EntityUtils.toString(execute.getEntity(), "utf-8");
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
}
