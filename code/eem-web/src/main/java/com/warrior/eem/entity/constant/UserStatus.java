package com.warrior.eem.entity.constant;

/**
 * 用户状态:激活/禁用
 * 
 * @author cold_blade
 */
public enum UserStatus {

	ACTIVE("active"), DISABLE("disable"), INVALID("");

	private UserStatus(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return desc;
	}

	private String desc;

	/**
	 * 将int型值转换为对应的User状态
	 * 
	 * @param status
	 * @return
	 */
	public static UserStatus convert2UserStatus(int status) {
		switch (status) {
		case 0:
			return ACTIVE;
		case 1:
			return DISABLE;
		default:
			return INVALID;
		}
	}
}
