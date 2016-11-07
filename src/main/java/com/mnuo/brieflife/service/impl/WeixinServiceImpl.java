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

import com.mnuo.brieflife.common.sql.QueryFiter;
import com.mnuo.brieflife.common.sql.QueryFiters;
import com.mnuo.brieflife.common.weixin.WeixinMessageConstant;
import com.mnuo.brieflife.common.weixin.WeixinMessageFactory;
import com.mnuo.brieflife.dao.BaseMessageDao;
import com.mnuo.brieflife.dao.PositionDao;
import com.mnuo.brieflife.dao.WeixinUserDao;
import com.mnuo.brieflife.dto.MessageResponseXML;
import com.mnuo.brieflife.entity.BlPosition;
import com.mnuo.brieflife.entity.BlUser;
import com.mnuo.brieflife.entity.WeixinMessage;
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
	public MessageResponseXML unifyHandler(Map<String, String> map) {
		String content = HandleWeixinMessage(map);
		return unifyReturnXML(map, content);
	}
	
	public MessageResponseXML unifyReturnXML(Map<String, String> map, String content){
		MessageResponseXML textMessage = new MessageResponseXML();
		textMessage.setContent(content);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setFromUserName(map.get("ToUserName"));
		textMessage.setMsgType("text");
		textMessage.setToUserName(map.get("FromUserName"));
			
		return textMessage;
	}
	/**
	 * 微信处理类
	 * @param map
	 */
	public String HandleWeixinMessage(Map<String, String> map){
		String content = "";
		if(map.get("Content").equals("0")){
			BlPosition position = this.getPosition(map.get("FromUserName"));
			updateStatus(position);
			return null;	
		}else if(map.get("Content").indexOf("1#") > 0){
			locationHandle(map);
			return null;
		}
		QueryFiters filters = new QueryFiters();
		filters.getParam().add(new QueryFiter("msgId", map.get("MsgId"), QueryFiter.EQ));
		List<WeixinMessage> list = baseMessageDao.queryCriteria(filters, WeixinMessage.class);
		if(list != null && list.size()>0)
			return null;
		
		if(map.get("MsgType").equals(WeixinMessageConstant.MESSAGE_TYPE_EVENT)){//关注
			eventHandle(map);
			content = "感谢您关注【SW】  /yx \n\n告诉我,你从哪里知道的?你为什么会关注XX的微信?\n\n您有什么问题有什么信息都可以输入/yx,但是别忘了输入0结束！";
		}else if(map.get("MsgType").equals(WeixinMessageConstant.MESSAGE_TYPE_LOCATION)){//位置
			locationHandle(map);
			content = "\n0,结束\n 1#,位置描述";
		}else{
			WeixinMessage entity = WeixinMessageFactory.getWeixinMessageInstance(map);
			BlPosition position = this.getPosition(map.get("FromUserName"));
			entity.setBlPosition(position);
			baseMessageDao.create(entity);
			content = "\n0,结束\n 1#,位置描述";;
		}
		return content;
	}
	/**
	 * 关注微信
	 * @param map
	 */
	public void eventHandle(Map<String, String> map){
		if(WeixinMessageConstant.EVENT_TYPE_SUBSCRIBE.equals(map.get("Event"))){
//			BlUser user = (BlUser) WeixinHttpClient.getUserInfo(map.get("FromUserName"), BlUser.class);
			BlUser user = new BlUser();
			user.setOpenid(map.get("FromUserName"));
			user.setSubscribeTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
			weixinUserDao.create(user);
		}
	}
	/**
	 * 位置信息
	 * @param map
	 */
	public void locationHandle(Map<String, String> map){
		BlPosition position =  positionDao.queryByUserId(map.get("FromUserName"));
		if(position == null){
			position = new BlPosition();
	    	position.setCreatedTime(new Date(Long.valueOf(map.get("CreateTime"))*1000L));
	    	position.setLatitude(map.get("Location_X"));
	    	position.setLongitude(map.get("Location_Y"));
	    	position.setAddress(map.get("Label"));
		}
    	position.setStatus(0);
    	if(map.get("Content") != null){
    		position.setDescript(map.get("Content").replaceAll("1#", ""));
    	}
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
