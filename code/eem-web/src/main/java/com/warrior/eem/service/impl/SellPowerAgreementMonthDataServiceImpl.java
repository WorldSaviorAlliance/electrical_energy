package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.swing.text.NumberFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.SellPowerAgreementMonthDataDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.SellPowerAgreementMonthData;
import com.warrior.eem.entity.vo.PowerCustomerOrSupplierCdtVo;
import com.warrior.eem.entity.vo.SellPowerAgreementMonthDataVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.SellPowerAgreementMonthDataService;

/**
 * 售电合约每月数据服务
 * 
 * @author seangan
 *
 */
@Service
public class SellPowerAgreementMonthDataServiceImpl extends AbstractServiceImpl<SellPowerAgreementMonthData> implements SellPowerAgreementMonthDataService {

	@Autowired
	private SellPowerAgreementMonthDataDao spamdDao;

	@Override
	IDao<SellPowerAgreementMonthData> getDao() {
		return spamdDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		PowerCustomerOrSupplierCdtVo cdt = (PowerCustomerOrSupplierCdtVo) conditions[0];
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		SqlRequest req = new SqlRequest();
		req.setPage(page);
		if (cdt != null) {
			LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
			if (cdt.getName() != null && cdt.getName().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.like("name", cdt.getName() + "%"));
			}
			if (cdt.getProvince() != null && cdt.getProvince().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.equal("province", cdt.getProvince()));
			}
			if (cdt.getProvince() != null && cdt.getProvince().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.equal("city", cdt.getCity()));
			}
			req.setCdt(sqlCdt);
		}
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... condition) {
		return null;
	}

	@Override
	SellPowerAgreementMonthData convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		SellPowerAgreementMonthData tb = (SellPowerAgreementMonthData)dbo;
		SellPowerAgreementMonthDataVo sb = (SellPowerAgreementMonthDataVo)vo;
		return null;
	}

	@Override
	SellPowerAgreementMonthData convertVoToDoForCreate(Serializable vo) {
		SellPowerAgreementMonthData tb = new SellPowerAgreementMonthData();
		SellPowerAgreementMonthDataVo sb = (SellPowerAgreementMonthDataVo)vo;
		return null;
	}
	
	/**
	 * 整合属性
	 * @param tb
	 * @param sb
	 */
	private void mergeProps(SellPowerAgreementMonthData tb, SellPowerAgreementMonthDataVo sb) {
		tb.setJanuary(handleUiPerMonthData(1, sb.getJanuary()));
		tb.setFebruary(handleUiPerMonthData(2, sb.getFebruary()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
		tb.setApril(handleUiPerMonthData(1, sb.getApril()));
	}
	
	/**
	 * 处理每月具体数据, 格式如下：
	 * (年度常规直购电交易电量;年度精准扶持直购电交易电量;年度自备替代直购电交易电量;年度富于电量);
	 * (常规直购电交易电价;精准扶持直购电交易电价;自备替代直购电交易电价;富余电价);
	 */
	private String handleUiPerMonthData(int index, String sbMonthParam) {
		String[] powerParams = sbMonthParam.split(";");
		if(powerParams.length != 8) {
			throw new EemException(index + "月缺少部分参数");
		}
		for(String powerParam : powerParams) {
			try {
				Double.valueOf(powerParam);
			} catch(NumberFormatException e) {
				throw new EemException(index + "月参数有误，必须为数字");
			}
		}
		return sbMonthParam;
	}
}
