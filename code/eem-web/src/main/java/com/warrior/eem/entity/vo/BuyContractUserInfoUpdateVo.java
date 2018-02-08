package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.warrior.eem.annotation.FieldChecker;

public class BuyContractUserInfoUpdateVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long buyContractUserInfoId;
	@FieldChecker(name = "电力用户名", minVal = 1, maxVal = Long.MAX_VALUE)
	private Long powerUserId;

	@FieldChecker(name = "购电量", minVal = 1, maxVal = Double.MAX_VALUE)
	private BigDecimal quantity;

	public Long getBuyContractUserInfoId() {
		return buyContractUserInfoId;
	}

	public void setBuyContractUserInfoId(Long buyContractUserInfoId) {
		this.buyContractUserInfoId = buyContractUserInfoId;
	}

	public Long getPowerUserId() {
		return powerUserId;
	}

	public void setPowerUserId(Long powerUserId) {
		this.powerUserId = powerUserId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
}
