package com.Jan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long UserToke_id;
	@Column
	public String opendId = "";
	@Column
	public String access_token = "";
	@Column
	public String jsapi_ticket = "";

	public long getUserToke_id() {
		return UserToke_id;
	}

	public void setUserToke_id(long userToke_id) {
		UserToke_id = userToke_id;
	}

	public String getOpendId() {
		return opendId;
	}

	public void setOpendId(String opendId) {
		this.opendId = opendId;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}

}
