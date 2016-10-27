/**
 * WeixinServiceImpl.java created at 2016年10月26日 上午11:30:32
 */
package com.mnuo.brieflife.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnuo.brieflife.common.WeixinMessage;
import com.mnuo.brieflife.common.sql.QueryFiter;
import com.mnuo.brieflife.common.sql.QueryFiters;
import com.mnuo.brieflife.dao.BaseMessageDao;
import com.mnuo.brieflife.dao.PositionDao;
import com.mnuo.brieflife.dao.WeixinUserDao;
import com.mnuo.brieflife.dto.BaseMessageXML;
import com.mnuo.brieflife.dto.TextMessageXML;
import com.mnuo.brieflife.entity.BaseMessage;
import com.mnuo.brieflife.entity.BlImageMessage;
import com.mnuo.brieflife.entity.BlPosition;
import com.mnuo.brieflife.entity.BlTextMessage;
import com.mnuo.brieflife.entity.BlUser;
import com.mnuo.brieflife.service.WeixinService;

/**
 * @author saxon
 */
@Service
@Transactional
public class WeixinServiceImpl implements WeixinService {
	@Autowired
	PositionDao positionDao;
	@Autowired
	public BaseMessageDao baseMessageDao;
	@Autowired
	private WeixinUserDao weixinUserDao;

	
	@Override
	public BaseMessageXML unifyHandler(Map<String, String> map) {
		String messageType=map.get("MsgType");
	    if(WeixinMessage.MESSAGE_TYPE_TEXT.equals(messageType)){
	    	textHandle(map);
	    }else if(WeixinMessage.MESSAGE_TYPE_IMAGE.equals(messageType)){
	    	imageHandle(map);
	    }else if(WeixinMessage.MESSAGE_TYPE_EVENT.equals(messageType)){
	    	eventHandle(map);
	    }else if(WeixinMessage.MESSAGE_TYPE_LOCATION.equals(messageType)){
	    	locationHandle(map);
	    }
	    
		return unifyReturnXML(map);
	}
	
	public BaseMessageXML unifyReturnXML(Map<String, String> map){
//		if(map.get("MsgType").equals(WeixinMessage.MESSAGE_TYPE_IMAGE)){
//			ImageMessageXML textMessage = new ImageMessageXML();
//	    	textMessage.setImage(new Image(map.get("MediaId")));
//	    	textMessage.setCreateTime(new Date().getTime());
//	    	textMessage.setFromUserName(map.get("ToUserName"));
//	    	textMessage.setMsgType(map.get("MsgType"));
//	    	textMessage.setToUserName(map.get("FromUserName"));
//	    	return textMessage;
//		}else{
			TextMessageXML textMessage = new TextMessageXML();
			textMessage.setContent("感谢您关注【SW】  /yx \n\n告诉我,你从哪里知道的?你为什么会关注XX的微信?\n\n您有什么问题有什么信息都可以输入/yx,但是别忘了输入0结束！");
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setFromUserName(map.get("ToUserName"));
			textMessage.setMsgType("text");
			textMessage.setToUserName(map.get("FromUserName"));
			
			return textMessage;
//		}
    	
	}
	
	public void textHandle(Map<String, String> map){
		if(map.get("Content").equals("0")){
			BlPosition position = this.getPosition(map.get("FromUserName"));
			updateStatus(position);
			return;	
		}
		QueryFiters filters = new QueryFiters();
		filters.getParam().add(new QueryFiter("msgId", map.get("MsgId"), QueryFiter.EQ));
		List<BaseMessage> list = baseMessageDao.queryCriteria(filters, BlTextMessage.class);
		if(list != null && list.size()>0)
			return;
		
		BlTextMessage textMessage = new BlTextMessage();
    	textMessage.setFromUserName(map.get("FromUserName"));
    	textMessage.setMsgId(map.get("MsgId"));
    	textMessage.setMsgType(map.get("MsgType"));
    	textMessage.setContent(map.get("Content"));
    	textMessage.setCreateTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
    	
    	BlPosition position = this.getPosition(map.get("FromUserName"));
//    	position.setStatus(0);
//    	position.setCreatedTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
//    	position.setUserId(map.get("FromUserName"));
//    	this.saveOrUpdate(position);
    	textMessage.setBlPosition(position);
    	baseMessageDao.create(textMessage);
	}
	
	public void imageHandle(Map<String, String> map){
		QueryFiters filters = new QueryFiters();
		filters.getParam().add(new QueryFiter("msgId", map.get("MsgId"), QueryFiter.EQ));
		List<BaseMessage> list = baseMessageDao.queryCriteria(filters, BlTextMessage.class);
		if(list != null && list.size()>0)
			return;
		
		BlImageMessage imageMessage = new BlImageMessage();
    	imageMessage.setFromUserName(map.get("FromUserName"));
    	imageMessage.setMsgId(map.get("MsgId"));
    	imageMessage.setMsgType(map.get("MsgType"));
    	imageMessage.setPicUrl(map.get("PicUrl"));
    	imageMessage.setMediaId(map.get("MediaId"));
    	imageMessage.setCreateTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
    	
    	BlPosition position = this.getPosition(map.get("FromUserName"));
    	imageMessage.setBlPosition(position);
    	baseMessageDao.create(imageMessage);
	}
	
	public void eventHandle(Map<String, String> map){
		if(WeixinMessage.EVENT_TYPE_SUBSCRIBE.equals(map.get("Event"))){
//			BlUser user = (BlUser) WeixinHttpClient.getUserInfo(map.get("FromUserName"), BlUser.class);
			BlUser user = new BlUser();
			user.setOpenid(map.get("FromUserName"));
			user.setSubscribeTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
			weixinUserDao.create(user);
		}
	}
	
	public void locationHandle(Map<String, String> map){
		BlPosition position =  positionDao.queryByUserId(map.get("FromUserName"));
		if(position == null){
			position = new BlPosition();
	    	position.setCreatedTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
		}
    	position.setLatitude(map.get("Location_X"));
    	position.setLongitude(map.get("Location_Y"));
    	position.setAddress(map.get("Label"));
    	position.setStatus(0);
    	this.saveOrUpdate(position);
	}
	
	@Cacheable(value="positionCache")// 使用了一个缓存名叫 accountCache   
	public BlPosition getPosition(String userId) {
		BlPosition entity = positionDao.queryByUserId(userId);
		if(entity == null){
			entity = new BlPosition();
			entity.setStatus(0);
			entity.setCreatedTime(new Date());
			entity.setUserId(userId);
			positionDao.create(entity);
		}
		return entity;
	}
	@CacheEvict(value="positionCache",key="#entity.getUserId()")
	public void updateStatus(BlPosition entity){
		entity.setStatus(1);
		positionDao.update(entity);
	}
	@CachePut(value="positionCache",key="#position.getUserId()")
	public BlPosition saveOrUpdate(BlPosition position) {
		if(position.getId() != null && position.getId() > 0){
			positionDao.create(position);
		}else{
			positionDao.update(position);
		}
		return position;
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public BaseMessageDao getBaseMessageDao() {
		return baseMessageDao;
	}

	public void setBaseMessageDao(BaseMessageDao baseMessageDao) {
		this.baseMessageDao = baseMessageDao;
	}

	public WeixinUserDao getWeixinUserDao() {
		return weixinUserDao;
	}

	public void setWeixinUserDao(WeixinUserDao weixinUserDao) {
		this.weixinUserDao = weixinUserDao;
	}
}
