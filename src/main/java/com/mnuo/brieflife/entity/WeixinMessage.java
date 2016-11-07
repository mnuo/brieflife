/**
 * BaseMessage.java created at 2016年10月23日 上午10:28:01
 */
package com.mnuo.brieflife.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author saxon
 */
public abstract class WeixinMessage implements Serializable {
	private static final long serialVersionUID = -4750357245401972497L;
	public String fromUserName;
	public Date createTime;
	public String msgId;
	public String msgType;
	
	public BlPosition blPosition;
	
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public BlPosition getBlPosition() {
		return blPosition;
	}
	public void setBlPosition(BlPosition blPosition) {
		this.blPosition = blPosition;
	}
}
