package com.Jan.service;

import com.Jan.constant.BaseResp;
import com.Jan.model.User;

public interface LoginService {
	public User login(String email, String password, BaseResp baseResp);

	public boolean register(String username, String password, String email, String verification, BaseResp baseResp);

	// public boolean sendMail(String email);

	public boolean sendMailSave(String email);

	public boolean resetPW(String password, String email, String verification, BaseResp baseResp);
}
