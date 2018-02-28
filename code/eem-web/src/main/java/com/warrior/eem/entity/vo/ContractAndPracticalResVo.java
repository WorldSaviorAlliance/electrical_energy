package com.warrior.eem.entity.vo;

import java.io.Serializable;

/**
 * 月度实际 预测 电量响应信息
 * 
 * @author seangan
 *
 */
public class ContractAndPracticalResVo implements Serializable {
	
	private static final long serialVersionUID = 5326170209850091367L;
	
	/**
	 * 时间
	 */
	private String date;
	
	/**
	 * 预测数据
	 */
	private double contractData;
	
	/**
	 * 实际数据
	 */
	private double practicalData;
	
	public ContractAndPracticalResVo() {
		
	}

	public double getContractData() {
		return contractData;
	}

	public double getPracticalData() {
		return practicalData;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setContractData(double contractData) {
		this.contractData = contractData;
	}

	public void setPracticalData(double practicalData) {
		this.practicalData = practicalData;
	}
}
