/**
 * WeixinController.java created at 2016年10月22日 下午4:34:39
 */
package com.mnuo.brieflife.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnuo.brieflife.common.WeixinCheck;
import com.mnuo.brieflife.common.WeixinMessage;
import com.mnuo.brieflife.common.utils.StringUtil;
import com.mnuo.brieflife.dto.BaseMessageXML;
import com.mnuo.brieflife.entity.BlImageMessage;
import com.mnuo.brieflife.entity.BlPosition;
import com.mnuo.brieflife.entity.BlTextMessage;
import com.mnuo.brieflife.entity.BlUser;
import com.mnuo.brieflife.service.BaseMessageService;
import com.mnuo.brieflife.service.PositionService;
import com.mnuo.brieflife.service.WeixinUserService;
/**
 * @author saxon
 */
@Controller
public class WeixinController {
	public static String TOKEN = "blwx20161022mnuo";
	
	@Autowired
	private BaseMessageService baseMessageService;
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private PositionService positionService;
	/**
	 * 微信验证
	 * @param signature 微信加密签名
	 * @param echostr 随机字符串
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	@RequestMapping(value="/weixin", method={RequestMethod.GET})
	@ResponseBody
	public String weixin(String signature, String echostr, String timestamp, String nonce){
        if(StringUtil.isNotEmpty(echostr) && WeixinCheck.checkFromWeixinOne(new String[]{TOKEN, timestamp, nonce}, signature)){
        	return echostr;
        }
        return "";
	}
	
	@RequestMapping(value="/weixin", method={RequestMethod.POST})
	@ResponseBody
	public Object weixin(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
         
        Map<String,String> message=WeixinMessage.parseXml(request);
         
        String messageType=message.get("MsgType");
	    if(WeixinMessage.MESSAGE_TYPE_TEXT.equals(messageType)){
	    	
	    	BlTextMessage textMessage = new BlTextMessage();
	    	textMessage.setFromUserName(message.get("FromUserName"));
	    	textMessage.setMsgId(message.get("MsgId"));
	    	textMessage.setMsgType(message.get("MsgType"));
	    	textMessage.setContent(message.get("Content"));
	    	textMessage.setCreateTime(new Date(Long.valueOf(message.get("CreateTime"))*1000L));
	    	
	    	BlPosition position = positionService.getPosition(message.get("FromUserName"));
	    	position.setStatus(0);
	    	position.setCreatedTime(new Date(Long.valueOf(message.get("CreateTime"))*1000L));
	    	position.setUserId(message.get("FromUserName"));
	    	positionService.saveOrUpdate(position);
	    	
	    	textMessage.setBlPosition(position);
	    	
	    	baseMessageService.save(textMessage);
	    	
	    	return getBaseMessageXml(message);
	    	
	    }else if(WeixinMessage.MESSAGE_TYPE_IMAGE.equals(messageType)){
	    	BlImageMessage imageMessage = new BlImageMessage();
	    	imageMessage.setFromUserName(message.get("FromUserName"));
	    	imageMessage.setMsgId(message.get("MsgId"));
	    	imageMessage.setMsgType(message.get("MsgType"));
	    	imageMessage.setPicUrl(message.get("picUrl"));
	    	imageMessage.setMediaId(message.get("MediaId"));
	    	imageMessage.setCreateTime(new Date(Long.valueOf(message.get("CreateTime"))*1000L));
	    	
	    	BlPosition position = positionService.getPosition(message.get("FromUserName"));
	    	imageMessage.setBlPosition(position);
	    	baseMessageService.save(imageMessage);
	    	return getBaseMessageXml(message);
	    }else if(WeixinMessage.MESSAGE_TYPE_EVENT.equals(messageType)){
	    	event(message);
	    	return getBaseMessageXml(message);
	    }else if(WeixinMessage.MESSAGE_TYPE_LOCATION.equals(messageType)){
	    	BlPosition position = positionService.getPosition(message.get("FromUserName"));
	    	position.setCreatedTime(new Date(Long.valueOf(message.get("CreateTime"))*1000L));
	    	position.setLatitude(message.get("Location_X"));
	    	position.setLongitude(message.get("Location_Y"));
	    	position.setAddress(message.get("Label"));
	    	position.setStatus(0);
	    	positionService.saveOrUpdate(position);
	    	return getBaseMessageXml(message);
	    }
	    return "";
	}
	public void event(Map<String, String> map){
		if(WeixinMessage.EVENT_TYPE_SUBSCRIBE.equals(map.get("Event"))){
//			BlUser user = (BlUser) WeixinHttpClient.getUserInfo(map.get("FromUserName"), BlUser.class);
			BlUser user = new BlUser();
			user.setOpenid(map.get("FromUserName"));
			user.setSubscribeTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
			weixinUserService.save(user);
		}
	}
	
	private BaseMessageXML getBaseMessageXml(Map<String, String> map){
		BaseMessageXML xml = new BaseMessageXML();
		xml.setToUserName(map.get("ToUserName"));
		xml.setCreateTime(map.get("CreateTime"));
		xml.setFromUserName(map.get("FromUserName"));
		xml.setMsgType(map.get("MsgType"));
		xml.setContent("感谢您关注【SaxonWade】"+"\n"+"微信号：SaxonWade"+"\n"+"随便你怎么输入。"+"\n"+"但是记住："+"\n"+"【输入1进行保存下一步】 "+"\n"+"更多内容，敬请期待...");
		return xml;
	}

	public BaseMessageService getBaseMessageService() {
		return baseMessageService;
	}

	public void setBaseMessageService(BaseMessageService baseMessageService) {
		this.baseMessageService = baseMessageService;
	}

	public WeixinUserService getWeixinUserService() {
		return weixinUserService;
	}

	public void setWeixinUserService(WeixinUserService weixinUserService) {
		this.weixinUserService = weixinUserService;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}
}
