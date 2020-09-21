package com.zx.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 用户实体类
 * @author 图灵教育
 *
 */

@Component
public class User implements Serializable{
	private String userName;	//用户名
	private String password;	//用户密码
	private String email;		//用户邮件
	
	public User() {
	}

	public User(String username, String password, String email) {
		this.userName = username;
		this.password = password;
		this.email = email;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

