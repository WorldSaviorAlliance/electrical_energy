package com.warrior.eem.entity.constant;

/**
 * 电力相关常量
 * 
 * @author seangan
 *
 */
public class PowerConsts {

	/**
	 * 电量/电价最大值
	 */
	public final static double MAX_POWER_VALUE = 9999999999999.999;

	public final static int INVALID_PRICE_COEFFICIENT = -1;// 无效的电价系数

	/**
	 * 允许上传合约文件类型
	 */
	public final static String[] ALLOWED_UPLOAD_CONSTRACT_TYPES = new String[] { 
			"d0cf11e0a1b11ae10000", // doc excel
			"504b0304140006000800", // docx
			"d0cf11e0a1b11ae10000", // wps
			"504B03040A0000000000" // docs
	};
}
