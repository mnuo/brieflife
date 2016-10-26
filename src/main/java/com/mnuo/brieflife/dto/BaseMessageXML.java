/**
 * BaseMessage.java created at 2016年10月22日 下午8:17:48
 */
package com.mnuo.brieflife.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author saxon
 */
public class BaseMessageXML {
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型（text/music/news）
	private String MsgType;
	
	public String getToUserName() {
		return ToUserName;
	}
	@XmlElement(name="ToUserName")
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	@XmlElement(name="FromUserName")
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	@XmlElement(name="CreateTime")
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	@XmlElement(name="MsgType")
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
