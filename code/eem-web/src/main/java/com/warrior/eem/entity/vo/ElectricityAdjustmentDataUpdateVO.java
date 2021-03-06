package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.constant.PowerConsts;

public class ElectricityAdjustmentDataUpdateVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long customerId;
	private String customerName;
	@FieldChecker(name = "用户户号", minLen = 1, maxLen = 30)
	private String customerNumber;
	private String contractName;
	private Long contractId;
	@FieldChecker(name = "合同编号", minLen = 1, maxLen = 30)
	private String contractNumber;
	@FieldChecker(name = "有效年份", minLen = 1, maxLen = 4)
	private String validYear;
	@FieldChecker(name = "电压类型", minLen = 1, maxLen = 15)
	private String voltageType;
	@FieldChecker(name = "调整数量", minVal = 0.000001, maxVal = PowerConsts.MAX_POWER_VALUE)
	private BigDecimal quantity;
	private Integer adjustmentType;
	@FieldChecker(name = "调整月份", minLen = 1, maxLen = 6)
	private String month;
	@FieldChecker(name = "交易品种", minLen = 1, maxLen = 10)
	private String tradeType;
	private Long userId;
	private String userName;
	private String createTime;
	@FieldChecker(name = "交易价格", minVal = 0.000001, maxVal = PowerConsts.MAX_POWER_VALUE)
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String curstomerName) {
		this.customerName = curstomerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getValidYear() {
		return validYear;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}

	public String getVoltageType() {
		return voltageType;
	}

	public void setVoltageType(String voltageType) {
		this.voltageType = voltageType;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Integer getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(Integer adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
