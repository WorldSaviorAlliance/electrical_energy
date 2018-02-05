package com.warrior.eem.entity.vo;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 售电合约月更新数据
 * 
 * @author seangan
 *
 */
public class SellPowerAgreementMonthDataUpateVo extends SellPowerAgreementMonthDataVo {

	private static final long serialVersionUID = 7741791027666246628L;

	@FieldChecker(name = "id", minVal = 1, maxVal = Long.MAX_VALUE)
	private Long id;

	public SellPowerAgreementMonthDataUpateVo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
