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
import com.mnuo.brieflife.common.http.WeixinHttpClient;
import com.mnuo.brieflife.common.utils.StringUtil;
import com.mnuo.brieflife.entity.BlImageMessage;
import com.mnuo.brieflife.entity.BlPosition;
import com.mnuo.brieflife.entity.BlTextMessage;
import com.mnuo.brieflife.entity.BlUser;
import com.mnuo.brieflife.entity.Coffee;
import com.mnuo.brieflife.service.BaseMessageService;
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
	    	BlPosition position = new BlPosition();
	    	position.setId(1);
	    	textMessage.setBlPosition(position);
	    	baseMessageService.save(textMessage);
	    	
	    }else if(WeixinMessage.MESSAGE_TYPE_IMAGE.equals(messageType)){
	    	BlImageMessage imageMessage = new BlImageMessage();
	    	imageMessage.setFromUserName(message.get("FromUserName"));
	    	imageMessage.setMsgId(message.get("MsgId"));
	    	imageMessage.setMsgType(message.get("MsgType"));
	    	imageMessage.setPicUrl(message.get("picUrl"));
	    	imageMessage.setMediaId(message.get("MediaId"));
	    	imageMessage.setCreateTime(new Date(Long.valueOf(message.get("CreateTime"))*1000L));
	    	BlPosition position = new BlPosition();
	    	imageMessage.setBlPosition(position);
	    	//baseMessageService.save(imageMessage);
	    }else if(WeixinMessage.MESSAGE_TYPE_EVENT.equals(messageType)){
	    	event(message);
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
}
