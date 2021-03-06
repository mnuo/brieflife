package com.mnuo.brieflife.entity;
// Generated 2016-10-23 10:26:21 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BlPosition generated by hbm2java
 */
@Entity
@Table(name = "bl_position", catalog = "brieflife")
public class BlPosition implements java.io.Serializable {
	private static final long serialVersionUID = -1017270927773980169L;
	private Integer id;
	private String userId;
	private Date createdTime;
	private String address;
	private String longitude;
	private String latitude;
	private String altitude;
	private int status;
	private String descript;
	private Set<BlImageMessage> blImageMessages = new HashSet<BlImageMessage>(0);
	private Set<BlTextMessage> blTextMessages = new HashSet<BlTextMessage>(0);

	public BlPosition() {
	}

	public BlPosition(Date createdTime, String address, String longitude, String latitude, String altitude,
			Set<BlImageMessage> blImageMessages, Set<BlTextMessage> blTextMessages) {
		this.createdTime = createdTime;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.blImageMessages = blImageMessages;
		this.blTextMessages = blTextMessages;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "address", length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "longitude", length = 20)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", length = 20)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "altitude", length = 20)
	public String getAltitude() {
		return this.altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blPosition")
	public Set<BlImageMessage> getBlImageMessages() {
		return this.blImageMessages;
	}

	public void setBlImageMessages(Set<BlImageMessage> blImageMessages) {
		this.blImageMessages = blImageMessages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blPosition")
	public Set<BlTextMessage> getBlTextMessages() {
		return this.blTextMessages;
	}

	public void setBlTextMessages(Set<BlTextMessage> blTextMessages) {
		this.blTextMessages = blTextMessages;
	}

	public String getUserId() {
		return userId;
	}
	@Column(name = "user_id", length = 64)
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}
	@Column(name = "status")
	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescript() {
		return descript;
	}
	@Column(name = "status", length = 500)
	public void setDescript(String descript) {
		this.descript = descript;
	}

}
