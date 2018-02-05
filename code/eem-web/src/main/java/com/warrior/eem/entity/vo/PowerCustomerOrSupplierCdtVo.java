package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 电力客户搜索条件
 * @author seangan
 *
 */
public class PowerCustomerOrSupplierCdtVo implements Serializable {
	
	private static final long serialVersionUID = 621349478829993951L;
	
	/**
	 * 名称
	 */
	@FieldChecker(name = "电力用户名称", minLen = 0, maxLen = 30)
	private String name;
	
	/**
	 * 省
	 */
	@FieldChecker(name = "省份", minLen = 0, maxLen = 10)
	private String province;
	 
	/**
	 * 城市
	 */
	@FieldChecker(name = "市区", minLen = 0, maxLen = 20)
	private String city;
	
	public PowerCustomerOrSupplierCdtVo() {
		
	}

	public String getName() {
		return name;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
