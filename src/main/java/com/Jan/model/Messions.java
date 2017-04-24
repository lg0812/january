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
public class Messions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long messionId;
	@Column
	public String title = "";
	@Column
	public String mession_shared = "";
	@Column
	public String mission_condition = "";
	@Column
	public String openId;
	@Column
	public int mession_times;
	@Column
	public String mission_href = "";
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date insert_time=new Date();
	public long getMessionId() {
		return messionId;
	}
	public void setMessionId(long messionId) {
		this.messionId = messionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMession_shared() {
		return mession_shared;
	}
	public void setMession_shared(String mession_shared) {
		this.mession_shared = mession_shared;
	}
	public String getMission_condition() {
		return mission_condition;
	}
	public void setMission_condition(String mission_condition) {
		this.mission_condition = mission_condition;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getMession_times() {
		return mession_times;
	}
	public void setMession_times(int mession_times) {
		this.mession_times = mession_times;
	}
	public String getMission_href() {
		return mission_href;
	}
	public void setMission_href(String mission_href) {
		this.mission_href = mission_href;
	}
	public Date getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}



}
