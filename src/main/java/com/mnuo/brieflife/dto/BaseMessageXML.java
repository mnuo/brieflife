/**
 * BaseMessage.java created at 2016年10月22日 下午8:17:48
 */
package com.mnuo.brieflife.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author saxon
 */
@XmlRootElement(name="xml")
public class BaseMessageXML {
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	
	public String getToUserName() {
		return toUserName;
	}
	@XmlElement(name="ToUserName")
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
