/**
 * WeixinController.java created at 2016年10月22日 下午4:34:39
 */
package com.mnuo.brieflife.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnuo.brieflife.common.WeixinCheck;
import com.mnuo.brieflife.common.WeixinMessage;
import com.mnuo.brieflife.common.utils.StringUtil;
import com.mnuo.brieflife.service.WeixinService;
/**
 * @author saxon
 */
@Controller
public class WeixinController {
	
	@Autowired
	private WeixinService weixinService;
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
        if(StringUtil.isNotEmpty(echostr) && WeixinCheck.checkFromWeixinOne(new String[]{WeixinMessage.TOKEN, timestamp, nonce}, signature)){
        	return echostr;
        }
        return "";
	}
	
	@RequestMapping(value="/weixin", method={RequestMethod.POST})
	@ResponseBody
	public Object weixin(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
         
        Map<String,String> message=WeixinMessage.parseXml(request);
        
        return weixinService.unifyHandler(message);
	}

	public WeixinService getWeixinService() {
		return weixinService;
	}

	public void setWeixinService(WeixinService weixinService) {
		this.weixinService = weixinService;
	}
}
