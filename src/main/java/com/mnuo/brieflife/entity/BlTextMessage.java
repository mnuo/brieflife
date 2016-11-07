package com.mnuo.brieflife.entity;
// Generated 2016-10-23 10:26:21 by Hibernate Tools 5.2.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TextMessage generated by hbm2java
 */
@Entity
@Table(name = "bl_text_message", catalog = "brieflife")
public class BlTextMessage extends WeixinMessage {
	private static final long serialVersionUID = 8009812351224444024L;
	private Integer id;
	private String content;

	public BlTextMessage() {
	}

	public BlTextMessage(BlPosition blPosition, String fromUserName, Date createTime, String content, String msgId,
			String msgType) {
		this.blPosition = blPosition;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.content = content;
		this.msgId = msgId;
		this.msgType = msgType;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	public BlPosition getBlPosition() {
		return this.blPosition;
	}

	public void setBlPosition(BlPosition blPosition) {
		this.blPosition = blPosition;
	}

	@Column(name = "from_user_name")
	public String getFromUserName() {
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "msg_id", length = 64)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Column(name = "msg_type", length = 10)
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
