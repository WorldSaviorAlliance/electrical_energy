package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 用户的界面端数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class UserVo implements Serializable {
	private static final long serialVersionUID = -6249511157842780035L;

	private long id = -1;

	@FieldChecker(name = "用户名", minLen = 2, maxLen = 20)
	private String name;

	@FieldChecker(name = "用户密码", minLen = 6, maxLen = 12)
	private String password;

	private int type;

	private long customerId;

	private String addTime;

	private String customerName;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
