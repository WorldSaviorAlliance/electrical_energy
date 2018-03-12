package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;


import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.constant.PowerConsts;

/**
 * 售电合约
 * @author seangan
 *
 */
public class SellPowerAgreementVo implements Serializable {

	private static final long serialVersionUID = 7741791027666246628L;
	
	private String id;

	@FieldChecker(name = "电力用户id", minVal = 1, maxVal = Long.MAX_VALUE)
	public Long customerId; // 客户id
	 
	@FieldChecker(name = "用户户号", minLen = 1, maxLen = 30)
	public String customerNo; // 客户户号
	
	@FieldChecker(name = "合约名称", minLen = 1, maxLen = 20)
	public String name; // 名称
	
	@FieldChecker(name = "合约编号", minLen = 1, maxLen = 30)
	public String No; // 编号
	
	@FieldChecker(name = "合约有效年份", minLen = 1, maxLen = 4)
	public String validYear; // 有效年

	private String attachment; // 附件名
	
	@FieldChecker(name = "电压类型", minLen = 1, maxLen = 15)
	public String voltageType; // 电压类型
	
	@FieldChecker(name = "交易电量", minVal = 1, maxVal = PowerConsts.MAX_POWER_VALUE)
	public BigDecimal tradePowerQuantity;
	
	@FieldChecker(name = "常规直购电量交易价格", minVal = 1, maxVal = PowerConsts.MAX_POWER_VALUE)
	public BigDecimal normalTradePrice;
	
	@FieldChecker(name = "精准扶持直购电量交易价格", minVal = 1, maxVal = PowerConsts.MAX_POWER_VALUE)
	public BigDecimal supportTradePrice;
	
	@FieldChecker(name = "自备替代直购电交易价格", minVal = 1, maxVal = PowerConsts.MAX_POWER_VALUE)
	public BigDecimal replaceTradePrice;
	
	@FieldChecker(name = "富余电量交易价格", minVal = 1, maxVal = PowerConsts.MAX_POWER_VALUE)
	public BigDecimal marginTradePrice;
	
	public SellPowerAgreementVo() {
		
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public String getName() {
		return name;
	}

	public String getNo() {
		return No;
	}

	public String getValidYear() {
		return validYear;
	}

	public String getAttachment() {
		return attachment;
	}

	public String getVoltageType() {
		return voltageType;
	}

	public BigDecimal getTradePowerQuantity() {
		return tradePowerQuantity;
	}

	public BigDecimal getNormalTradePrice() {
		return normalTradePrice;
	}

	public BigDecimal getSupportTradePrice() {
		return supportTradePrice;
	}

	public BigDecimal getReplaceTradePrice() {
		return replaceTradePrice;
	}

	public BigDecimal getMarginTradePrice() {
		return marginTradePrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNo(String no) {
		No = no;
	}

	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public void setVoltageType(String voltageType) {
		this.voltageType = voltageType;
	}

	public void setTradePowerQuantity(BigDecimal tradePowerQuantity) {
		this.tradePowerQuantity = tradePowerQuantity;
	}

	public void setNormalTradePrice(BigDecimal normalTradePrice) {
		this.normalTradePrice = normalTradePrice;
	}

	public void setSupportTradePrice(BigDecimal supportTradePrice) {
		this.supportTradePrice = supportTradePrice;
	}

	public void setReplaceTradePrice(BigDecimal replaceTradePrice) {
		this.replaceTradePrice = replaceTradePrice;
	}

	public void setMarginTradePrice(BigDecimal marginTradePrice) {
		this.marginTradePrice = marginTradePrice;
	}

}
