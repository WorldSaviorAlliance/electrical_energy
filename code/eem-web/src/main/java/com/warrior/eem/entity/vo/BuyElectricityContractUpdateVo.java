package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.constant.PowerConsts;

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
	private Long id;

	@FieldChecker(name = "合约名称", minLen = 1, maxLen = 20)
	private String name;
	
	@FieldChecker(name = "合约编号", minLen = 1, maxLen = 30)
	private String number;
	
	
	private Long supplier;
	
	@FieldChecker(name = "有效年份", minLen = 1, maxLen = 4)
	private String validYear;
	
	@FieldChecker(name = "电压类型", minLen = 1, maxLen = 5)
	private String voltageLevel;
	
	@FieldChecker(name = "购电量", minVal = 0, maxVal = PowerConsts.MAX_POWER_VALUE)
	private BigDecimal quantity;
	
	@FieldChecker(name = "交易品种", minLen = 1, maxLen = 10)
	private String tradeType;
	
	@FieldChecker(name = "电价", minVal = 0.00000001, maxVal = PowerConsts.MAX_POWER_VALUE)
	private BigDecimal price;

	private String createTime;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getSupplier() {
		return supplier;
	}
	public void setSupplier(Long supplier) {
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
	public String getVoltageLevel() {
		return voltageLevel;
	}
	public void setVoltageLevel(String voltageLevel) {
		this.voltageLevel = voltageLevel;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
