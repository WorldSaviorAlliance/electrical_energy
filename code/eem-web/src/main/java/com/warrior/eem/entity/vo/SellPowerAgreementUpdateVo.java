package com.warrior.eem.entity.vo;


/**
 * 售电合约更新模型
 * @author seangan
 *
 */
public class SellPowerAgreementUpdateVo extends SellPowerAgreementVo {

	private static final long serialVersionUID = 7741791027666246628L;
	
	private String id;

	public SellPowerAgreementUpdateVo() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
