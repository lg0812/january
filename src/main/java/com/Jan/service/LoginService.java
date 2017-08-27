package com.Jan.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.Jan.constant.BaseResp;
import com.Jan.model.User;

public interface LoginService {
	public User login(String email, String password, BaseResp baseResp);

	public boolean register(String username, String password, String email, String verification, BaseResp baseResp);

	// public boolean sendMail(String email);

	public boolean sendMailSave(String email);

	public User updateProfile(String accessToken,String username, MultipartFile userLogo,HttpServletRequest req) throws IOException;

	public boolean resetPW(String password, String email, String verification, BaseResp baseResp);
}
