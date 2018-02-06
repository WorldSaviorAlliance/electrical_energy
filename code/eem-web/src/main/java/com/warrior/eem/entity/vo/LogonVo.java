package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 页面登录信息
 * @author seangan
 *
 */
public class LogonVo implements Serializable {

	private static final long serialVersionUID = -282078236979763939L;

	@FieldChecker(name="用户名", minLen = 1, maxLen = 30)
	public String userName;
	
	@FieldChecker(name="密码", minLen = 6, maxLen = 16)
	public String password;
	
	public LogonVo() {
		
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
