package com.Jan.constant;

public class BaseResp {
	protected String code = ApiConsts.OK;
	protected String message = "";
	protected Object result = new Object();

	public String getCode() { 
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
