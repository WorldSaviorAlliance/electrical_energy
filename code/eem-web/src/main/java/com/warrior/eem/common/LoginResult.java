package com.warrior.eem.common;

import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.entity.Authority;
import com.warrior.eem.entity.User;

/**
 * 登录结果
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class LoginResult {
	private User user;
	private List<Authority> authorities;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = new ArrayList<>(authorities);
	}
}
