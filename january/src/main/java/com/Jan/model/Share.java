package com.Jan.model;

import java.util.Date;

import javax.annotation.Generated;
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
public class Share {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long shareId;

	@Column
	public long messionId;
	@Column
	public long openId;
	@Column
	public long sharedId;
	@Column
	public int count = 0;
	@Column
	public boolean leader = false;
	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date click_time = new Date();

	public long getShareId() {
		return shareId;
	}

	public void setShareId(long shareId) {
		this.shareId = shareId;
	}

	public long getMessionId() {
		return messionId;
	}

	public void setMessionId(long messionId) {
		this.messionId = messionId;
	}

	public long getOpenId() {
		return openId;
	}

	public void setOpenId(long openId) {
		this.openId = openId;
	}

	public long getSharedId() {
		return sharedId;
	}

	public void setSharedId(long sharedId) {
		this.sharedId = sharedId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getClick_time() {
		return click_time;
	}

	public void setClick_time(Date click_time) {
		this.click_time = click_time;
	}

}
