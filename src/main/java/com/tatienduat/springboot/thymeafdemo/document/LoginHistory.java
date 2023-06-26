package com.tatienduat.springboot.thymeafdemo.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "login_hitory")
public class LoginHistory {

	@Id
	private int id;

	private int userId;
	private String userName;
	private String email;
	private boolean successful;
	private LocalDateTime loginTime;
	private String ipAddress;


	// define constructor
	public LoginHistory() {

	}


public LoginHistory(int id, int userId, String userName, String email, boolean successful, LocalDateTime loginTime,
			String ipAddress) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.successful = successful;
		this.loginTime = loginTime;
		this.ipAddress = ipAddress;
	}


//	setter and getter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}



	@Override
	public String toString() {
		return "LoginHistory [id=" + id + ", userId=" + userId + ", userName=" + userName + ", email=" + email
				+ ", successful=" + successful + ", loginTime=" + loginTime
				+ ", ipAddress=" + ipAddress;
	}



}
