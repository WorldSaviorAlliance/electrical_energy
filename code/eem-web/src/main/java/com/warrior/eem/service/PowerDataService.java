package com.warrior.eem.service;


import com.warrior.eem.entity.vo.ContractAndPracticalReqVo;
import com.warrior.eem.entity.vo.PageVo;

/**
 * 电量信息服务接口
 * @author seangan
 *
 */
public interface PowerDataService extends IService {
	
	/**
	 * 
	 * 获取预测 实际的数据列表
	 * @param req 请求参数
	 * @return
	 */
	public PageVo listContractAndpracticalData(ContractAndPracticalReqVo req);
}
