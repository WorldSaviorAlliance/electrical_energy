package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 电量月度清算列表返回数据
 * 
 * @author seangan
 *
 */
public class PowerMonthPriceInfoItemVo implements Serializable {
	
	private static final long serialVersionUID = -1242435088158768025L;

	// 电力用户名称
	private String customerName;
	
	// 用户户号
	private String customerNo;
	
	// 电表编号
	private String emNo;
	
	// 月份
	private String month;
	
	// 电压等级
	private String voltageType;
	
	// 交易品种
	private String tradeType;
	
	// 交易价格
	private BigDecimal tradePrice;
	
	// 高峰电费
	private BigDecimal peakPrice;

	// 平段电费
	private BigDecimal flatPrice;

	// 低谷电费
	private BigDecimal troughPrice;

	// 无功电费
	private BigDecimal validPrice;

	// 电费总计
	private double totalPrice;
	
	public PowerMonthPriceInfoItemVo() {
		
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public String getEmNo() {
		return emNo;
	}

	public String getMonth() {
		return month;
	}

	public String getVoltageType() {
		return voltageType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public BigDecimal getPeakPrice() {
		return peakPrice;
	}

	public BigDecimal getFlatPrice() {
		return flatPrice;
	}

	public BigDecimal getTroughPrice() {
		return troughPrice;
	}

	public BigDecimal getValidPrice() {
		return validPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public void setEmNo(String emNo) {
		this.emNo = emNo;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setVoltageType(String voltageType) {
		this.voltageType = voltageType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public void setPeakPrice(BigDecimal peakPrice) {
		this.peakPrice = peakPrice;
	}

	public void setFlatPrice(BigDecimal flatPrice) {
		this.flatPrice = flatPrice;
	}

	public void setTroughPrice(BigDecimal troughPrice) {
		this.troughPrice = troughPrice;
	}

	public void setValidPrice(BigDecimal validPrice) {
		this.validPrice = validPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
