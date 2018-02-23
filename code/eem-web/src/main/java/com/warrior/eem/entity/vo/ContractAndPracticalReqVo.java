package com.warrior.eem.entity.vo;

import java.io.Serializable;

/**
 * 月度实际 预测 电量请求参数
 * 
 * @author seangan
 *
 */
public class ContractAndPracticalReqVo implements Serializable {
	
	private static final long serialVersionUID = 5326170209850091367L;
	
	/**
	 * 月度统计
	 */
	public static final int MONTH_STATIC = 0;
	
	/**
	 * 年度统计
	 */
	public static final int YEAR_STATIC = 1;
	
	// 0 按照月  1 按照年
	private int type;
	
	// 开始时间
	private String startTime;
	
	// 结束时间
	private String endTime;
	
	// 户号
	private String customerNo;
	
	// 电力用户
	private String customerId;
	
	// 电压类型
	private String voltageType;
	
	// 交易类型
	private String tradeType;
	
	public ContractAndPracticalReqVo() {
		
	}

	public int getType() {
		return type;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getVoltageType() {
		return voltageType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setVoltageType(String voltageType) {
		this.voltageType = voltageType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
}
