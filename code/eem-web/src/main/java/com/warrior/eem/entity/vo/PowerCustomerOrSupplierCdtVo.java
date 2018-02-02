package com.warrior.eem.entity.vo;

import java.io.Serializable;

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
	private String name;
	
	/**
	 * 省
	 */
	private String province;
	 
	/**
	 * 城市
	 */
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
