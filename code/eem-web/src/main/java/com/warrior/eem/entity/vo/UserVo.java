package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.constant.UserStatus;

/**
 * 用户的界面端数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class UserVo implements Serializable {
	private static final long serialVersionUID = -6249511157842780035L;

	private long id = -1;

	@FieldChecker(name = "用户名", minLen = 6, maxLen = 20)
	private String name;

	@FieldChecker(name = "用户昵称", minLen = 6, maxLen = 20)
	private String nickName;

	@FieldChecker(name = "用户密码", minLen = 6, maxLen = 12)
	private String password;

	@FieldChecker(name = "用户状态") // 0表示激活 1表示禁用
	private int status;

	@FieldChecker(name = "用户角色")
	private long roleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
