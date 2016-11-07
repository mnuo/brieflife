/**
 * WeixinHttpClient.java created at 2016年10月23日 下午1:34:05
 */
package com.mnuo.brieflife.common.http;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mnuo.brieflife.common.utils.StringUtil;
import com.mnuo.brieflife.common.weixin.WeixinMessageConstant;

/**
 * @author saxon
 */
public class WeixinHttpClient {
	public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	public static final String USER_INFO_URL ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	/**
	 * 获取wexin token
	 * @return
	 */
	public static String getAccessTocken(){
		try {
			String result = HttpClientInvoke.httpStreamGetString(TOKEN_URL+"&appid="+WeixinMessageConstant.APP_ID+"&secret="+ WeixinMessageConstant.APP_SECRET);
			
			JSONObject json = JSONObject.parseObject(result);
			
			return json.getString("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getUserInfo(String openId, Class<?> clazz){
		Map<String, String> map = new HashMap<String, String>();
		try {
			String url=String.format(USER_INFO_URL, getAccessTocken(), openId);
			String result = HttpClientInvoke.httpStreamGetString(url);
			JSONObject json = JSONObject.parseObject(result);
			Object obj = clazz.newInstance();
			/* 
	        * 得到类中的所有属性集合 
	        */  
	        Field[] fs = clazz.getDeclaredFields();  
	        for(Field field : fs){  
	    	   field.setAccessible(true); //设置些属性是可以访问的  
	            
	           String type = field.getType().toString();//得到此属性的类型  
	           String name = field.getName();
	           if(!name.equals("serialVersionUID")){
	        	   if (type.endsWith("String")) {  
	        		   if(name.equals("tagidList") && json.get("tagid_list") != null){
	        			   
	        			   JSONArray arr = json.getJSONArray("tagid_list");
	        			   field.set(obj, arr.toArray().toString());
	        		   }else{
	        			   field.set(obj,map.get(map.get(StringUtil.toLowerCase(name)))) ;        //给属性设值  
	        		   }
	        	   }else if(type.endsWith("Date")){  
	        		   new Date(Long.valueOf(map.get(StringUtil.toLowerCase(name))));       //给属性设值  
	        	   } 
	           }
	       }
			
			//map = WeixinMessage.parseXml(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
