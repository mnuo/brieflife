/**
 * XmlUtil.java created at 2016年11月5日 下午12:00:09
 */
package com.mnuo.brieflife.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author saxon
 */
public class XmlUtil {
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
	   
}
