/**
 * WeixinMessageFactory.java created at 2016年11月4日 下午4:54:49
 */
package com.mnuo.brieflife.common.weixin;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.mnuo.brieflife.common.utils.StringUtil;
import com.mnuo.brieflife.entity.WeixinMessage;

/**
 * @author saxon
 */
public class WeixinMessageFactory {
//	private static final Logger logger = Logger.getLogger(WeixinMessageFactory.class);
	
	private static WeixinMessage weixinMessage;
	
	public static WeixinMessage getWeixinMessageInstance(Map<String, String> map){
		String msgtype = map.get("MsgType");
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName("com.mnuo.brieflife.entity.Bl" + msgtype);
			//“Demo”类必须有默认构造方法，否则会抛出异常
			weixinMessage = (WeixinMessage) clazz.newInstance();
			
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, String> ele = iterator.next();
				String key = ele.getKey();
				String value = ele.getValue();
				if(key.toLowerCase().equals("tousername"))
					continue;
				
				Field field = clazz.getDeclaredField(StringUtil.toFirstLowerCase(key));
				field.setAccessible(true);
				if(key.toLowerCase().equals("createtime")){
					field.set(weixinMessage, new Date(Long.valueOf(value)*1000));
				}else{
					field.set(weixinMessage, value);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return weixinMessage;
	}
}
