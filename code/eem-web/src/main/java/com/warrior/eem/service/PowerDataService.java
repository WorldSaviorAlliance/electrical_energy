package com.warrior.eem.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.warrior.eem.entity.vo.ContractAndPracticalReqVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.PowerMonthPriceReqVo;

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
	public PageVo listContractAndpracticalData(ContractAndPracticalReqVo param);
	
	
	/**
	 * 月度清算列表信息
	 * @param req
	 * @param order
	 * @return
	 */
	public PageVo listPowerMonthPriceData(PowerMonthPriceReqVo param, String order);
	
	
	/**
	 * 能耗管理统计当月电量信息
	 * @return
	 */
	public Map<String, BigDecimal> statisticsCurrentMonthPowerInfo();
	
	/**
	 * 能耗管理统计当年电量信息
	 * @return
	 */
	Map<String, BigDecimal> statisticsYearPowerQuantity();
	
	/**
	 * 能耗管理统计当年电费信息
	 * @return
	 */
	Map<String, BigDecimal> statisticsYearPowerPrice();
	
	/**
	 * 能耗管理月度电量列表展示
	 * @return
	 */
	List<Map<String, Object>> listMonthPowerQuantity();
}
