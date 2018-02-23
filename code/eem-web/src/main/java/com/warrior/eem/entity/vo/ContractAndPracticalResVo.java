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
	private String contractData;
	
	/**
	 * 实际数据
	 */
	private String practicalData;
	
	public ContractAndPracticalResVo() {
		
	}

	public String getDate() {
		return date;
	}

	public String getContractData() {
		return contractData;
	}

	public String getPracticalData() {
		return practicalData;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setContractData(String contractData) {
		this.contractData = contractData;
	}

	public void setPracticalData(String practicalData) {
		this.practicalData = practicalData;
	}
}
