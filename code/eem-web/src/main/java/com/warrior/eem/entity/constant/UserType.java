package com.warrior.eem.entity.constant;

/***
 * 用户类型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public enum UserType {
	ELECTRICITY("电力用户"), SYSTEM("系统用户"), INVALID("");

	private String desc;

	private UserType(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return desc;
	}

	/**
	 * 将int型值转换为对应的User类型
	 * 
	 * @param status
	 * @return
	 */
	public static UserType convert2UserStatus(int type) {
		switch (type) {
		case 0:
			return ELECTRICITY;
		case 1:
			return SYSTEM;
		default:
			return INVALID;
		}
	}
}
