package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 电量月度清算列表返回数据
 * 
 * @author seangan
 *
 */
public class PowerMonthPriceReqVo implements Serializable {

	private static final long serialVersionUID = -1242435088158768025L;

	// 电力用户名称
	@FieldChecker(name = "电力用户简称", maxLen = 20)
	private String customerName;

	// 开始时间
	@FieldChecker(name = "月份开始时间", minLen = 6, maxLen = 6)
	private String startTime;

	// 结束时间
	@FieldChecker(name = "月份结束时间", minLen = 6, maxLen = 6)
	private String endTime;

	public PowerMonthPriceReqVo() {

	}

	public String getCustomerName() {
		return customerName;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
