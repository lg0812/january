package com.Jan.service;

import com.Jan.model.User;

public interface LoginService {
	public User login(String username, String password);

	public boolean register(String username, String password, String email, String verification);

	// public boolean sendMail(String email);

	public boolean sendMailSave(String email);
}
