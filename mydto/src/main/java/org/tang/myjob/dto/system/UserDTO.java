package org.tang.myjob.dto.system;

import java.io.Serializable;

public class UserDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -187482008493620835L;
	private String userId;//系统用户唯一标识符
	private String userName;
	private String userPwd;

	private String LastLoginTime; //最后一次登陆时间

	public String getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
