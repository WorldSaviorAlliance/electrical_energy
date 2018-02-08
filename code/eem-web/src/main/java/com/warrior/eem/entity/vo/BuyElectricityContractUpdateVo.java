package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.constant.Constant;

/**
 * 
 * 购电合同更新VO
 *
 */
public class BuyElectricityContractUpdateVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long buyContractId;

	@FieldChecker(name = "合约名称", minLen = 1, maxLen = 20)
	private String name;
	@FieldChecker(name = "合约编号", minLen = 1, maxLen = 30)
	private String number;
	private String supplier;
	@FieldChecker(name = "有效年份", minLen = 1, maxLen = 4)
	private String validYear;
	private Long voltageLevel;
	@FieldChecker(name = "购电量", minVal = 0, maxVal = Constant.MAX_POWER_VALUE)
	private BigDecimal quantity;
	private Long tradeType;
	@FieldChecker(name = "电价", minVal = 0.00000001, maxVal = Constant.MAX_POWER_VALUE)
	private BigDecimal price;

	public Long getBuyContractId() {
		return buyContractId;
	}
	public void setBuyContractId(Long buyContractId) {
		this.buyContractId = buyContractId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getValidYear() {
		return validYear;
	}
	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public Long getVoltageLevel() {
		return voltageLevel;
	}
	public void setVoltageLevel(Long voltageLevel) {
		this.voltageLevel = voltageLevel;
	}
	public Long getTradeType() {
		return tradeType;
	}
	public void setTradeType(Long tradeType) {
		this.tradeType = tradeType;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
