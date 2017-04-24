package com.Jan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Attend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long attendId;
	@Column
	public String attendName = "";
	@Column
	public String attendPhone = "";
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date subitTime = new Date();
	@Column
	public String openId;
	@Column
	public long messionId;
	@Column
	public String shareOpenId = "";

	public String getShareOpenId() {
		return shareOpenId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setShareOpenId(String shareOpenId) {
		this.shareOpenId = shareOpenId;
	}

	public long getAttendId() {
		return attendId;
	}

	public void setAttendId(long attendId) {
		this.attendId = attendId;
	}

	public String getAttendName() {
		return attendName;
	}

	public void setAttendName(String attendName) {
		this.attendName = attendName;
	}

	public String getAttendPhone() {
		return attendPhone;
	}

	public void setAttendPhone(String attendPhone) {
		this.attendPhone = attendPhone;
	}

	public Date getSubitTime() {
		return subitTime;
	}

	public void setSubitTime(Date subitTime) {
		this.subitTime = subitTime;
	}

	public long getMessionId() {
		return messionId;
	}

	public void setMessionId(long messionId) {
		this.messionId = messionId;
	}

}
