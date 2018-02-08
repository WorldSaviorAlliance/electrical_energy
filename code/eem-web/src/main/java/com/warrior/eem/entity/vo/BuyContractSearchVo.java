package com.warrior.eem.entity.vo;

import java.io.Serializable;

/**
 * 购电合同查询条件实体
 *
 */
public class BuyContractSearchVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String validYear;
	private String supplier;
	
	public String getValidYear() {
		return validYear;
	}
	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	
	
}
