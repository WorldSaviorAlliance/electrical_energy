package com.warrior.eem.entity.vo;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 售电合约更新模型
 * @author seangan
 *
 */
public class SellPowerAgreementUpdateVo extends SellPowerAgreementVo {

	private static final long serialVersionUID = 7741791027666246628L;
	
	@FieldChecker(name = "id", minVal = 1, maxVal = Long.MAX_VALUE)
	private Long id;

	public SellPowerAgreementUpdateVo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
