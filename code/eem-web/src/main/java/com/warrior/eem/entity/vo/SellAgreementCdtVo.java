package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 售电合约列表查询条件实体
 * @author seangan
 *
 */
public class SellAgreementCdtVo implements Serializable {

	private static final long serialVersionUID = -8306872775556514026L;

	@FieldChecker(name = "电力用户名称", maxLen = 30, minLen = 0)
	private String name;
	
	@FieldChecker(name = "年份", maxLen = 4, minLen = 0)
	private String validYear;
	
	public SellAgreementCdtVo() {
		
	}

	public String getName() {
		return name;
	}

	public String getValidYear() {
		return validYear;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}
}
