package com.warrior.eem.entity.constant;

/**
 * 套餐类型的枚举
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public enum PackageType {
	FIXED_RETURN("固定回报"), MARKET_LINKAGE("市场联动"), DEVIATION_CHECK("偏差考核");

	private String desc;

	private PackageType(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return desc;
	}

	/**
	 * 转换为对应的套餐类型
	 * 
	 * @param type
	 * @return
	 */
	public static PackageType convert(int type) {
		switch (type) {
		case 0:
			return FIXED_RETURN;
		case 1:
			return MARKET_LINKAGE;
		default:
			return DEVIATION_CHECK;
		}
	}
}
