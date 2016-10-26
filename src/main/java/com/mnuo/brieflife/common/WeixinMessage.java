/**
 * WeixinMessage.java created at 2016年10月22日 下午7:32:46
 */
package com.mnuo.brieflife.common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mnuo.brieflife.common.utils.StringUtil;

/**
 * @author saxon
 */
public class WeixinMessage {
	public static final String APP_ID = "";
	public static final String APP_SECRET = "";
	public static String TOKEN = "blwx20161022mnuo";
	/**
     * text
     */
    public static final String MESSAGE_TYPE_TEXT = "text";
 
    /**
     * music
     */
    public static final String MESSAGE_TYPE_MUSIC = "music";
 
    /**
     * news
     */
    public static final String MESSAGE_TYPE_NEWS = "news";
 
 
    /**
     * image
     */
    public static final String MESSAGE_TYPE_IMAGE = "image";
 
    /**
     * link
     */
    public static final String MESSAGE_TYPE_LINK = "link";
 
    /**
     * location
     */
    public static final String MESSAGE_TYPE_LOCATION = "location";
 
    /**
     * voice
     */
    public static final String MESSAGE_TYPE_VOICE = "voice";
     
    /**
     * video
     */
    public static final String MESSAGE_TYPE_VIDEO = "video";
     
    /**
     * shortvideo
     */
    public static final String MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
 
    /**
     * event
     */
    public static final String MESSAGE_TYPE_EVENT = "event";
 
    /**
     * subscribe
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
 
    /**
     * unsubscribe
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
 
    /**
     * CLICK
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
     
    
	/**
	 * 解析request信息 xml转成map
	 * @param request
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public static Map<String,String> parseXml(HttpServletRequest request){
        Map<String,String> messageMap=new HashMap<String, String>();
        InputStream inputStream = null;
        try{
        	//读取request Stream信息
        	inputStream = request.getInputStream();
        	//sax 解析xml
        	SAXReader reader = new SAXReader();
        	Document document = reader.read(inputStream);
        	//获取xml根节点
        	Element root=document.getRootElement();
        	List<Element> elementsList=root.elements();
        	//遍历元素设置map
        	for(Element e:elementsList){
        		messageMap.put(e.getName(),e.getText());
        	}
        }catch (Exception e) {
        	e.printStackTrace();
		}finally{
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
        return messageMap;
    }
    @SuppressWarnings("unchecked")
    public static Map<String,String> parseXml(InputStream inputStream){
    	Map<String,String> messageMap=new HashMap<String, String>();
    	try{
    		//sax 解析xml
    		SAXReader reader = new SAXReader();
    		Document document = reader.read(inputStream);
    		//获取xml根节点
    		Element root=document.getRootElement();
    		List<Element> elementsList=root.elements();
    		//遍历元素设置map
    		for(Element e:elementsList){
    			messageMap.put(e.getName(),e.getText());
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally{
    		if(inputStream != null)
    			try {
    				inputStream.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    	}
    	return messageMap;
    }
    
    public static Object instanceMessage(Map<String, String> map, Class<?> clazz){
    	try {
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
	        		   field.set(obj,map.get(map.get(StringUtil.toLowerCase(name)))) ;        //给属性设值  
	        	   }else if(type.endsWith("Date")){  
	        		   new Date(Long.valueOf(map.get(StringUtil.toLowerCase(name))));       //给属性设值  
	        	   } 
	           }
	       }
    		return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
}
