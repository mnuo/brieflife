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
	private String content;
	
	public String getToUserName() {
		return toUserName;
	}
	@XmlElement(name="ToUserName")
	public void setToUserName(String toUserName) {
		this.toUserName = "<![CDATA["+toUserName+"]]>";
	}
	public String getFromUserName() {
		return fromUserName;
	}
	@XmlElement(name="FromUserName")
	public void setFromUserName(String fromUserName) {
		this.fromUserName = "<![CDATA["+fromUserName+"]]>";
	}
	public String getCreateTime() {
		return createTime;
	}
	@XmlElement(name="CreateTime")
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	@XmlElement(name="MsgType")
	public void setMsgType(String msgType) {
		this.msgType = "<![CDATA["+msgType+"]]>";
	}
	public String getContent() {
		return content;
	}
	
	@XmlElement(name="Content")
	public void setContent(String content) {
		this.content = "<![CDATA["+content+"]]>";
	}
}
