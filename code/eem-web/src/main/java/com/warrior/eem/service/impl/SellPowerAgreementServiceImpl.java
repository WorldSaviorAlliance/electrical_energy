package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.SellPowerAgreementDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.vo.SellAgreementCdtVo;
import com.warrior.eem.entity.vo.SellPowerAgreementVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.SellPowerAgreementService;
import com.warrior.eem.util.EntityValidator;

/**
 * 售电合约服务
 * 
 * @author seangan
 *
 */
@Service
public class SellPowerAgreementServiceImpl extends AbstractServiceImpl<SellPowerAgreement> implements SellPowerAgreementService {

	@Autowired
	private SellPowerAgreementDao spaDao;
	
	@Autowired
	private PowerCustomerDao pcDao;

	@Override
	IDao<SellPowerAgreement> getDao() {
		return spaDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		SellAgreementCdtVo cdt = (SellAgreementCdtVo) conditions[0];
		try {
			EntityValidator.checkEntity(cdt);
		} catch (IllegalAccessException | SecurityException e) {
			throw new EemException("解析售电合约列表查询条件失败");
		}
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		SqlRequest req = new SqlRequest();
		req.setPage(page);
		LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
		sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
		sqlCdt = sqlCdt.and(SimpleCondition.equal("validYear", cdt.getValidYear()));
		req.setCdt(sqlCdt);
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... condition) {
		return null;
	}

	@Override
	SellPowerAgreement convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		SellPowerAgreement tb = (SellPowerAgreement)dbo;
		SellPowerAgreementVo sb = (SellPowerAgreementVo)vo;
		mergeProps(tb, sb);
		return tb;
	}

	@Override
	SellPowerAgreement convertVoToDoForCreate(Serializable vo) {
		SellPowerAgreement tb = new SellPowerAgreement();
		SellPowerAgreementVo sb = (SellPowerAgreementVo)vo;
		mergeProps(tb, sb);
		tb.setCreateTime(new Date());
		// TODO 当前登录用户
		tb.setCreator(null);
		return tb;
	}
	
	private void mergeProps(SellPowerAgreement tb, SellPowerAgreementVo sb) {
		PowerCustomer pc = pcDao.getEntity(sb.getCustomerId());
		if(pc == null) {
			throw new EemException("无效的电力用户id：" + sb.getCustomerId());
		}
		tb.setAttachment(sb.getAttachment());
		tb.setCustomer(pc);
		tb.setCustomerNo(sb.getCustomerNo());
		tb.setMarginTradePrice(sb.getMarginTradePrice());
		tb.setName(sb.getName());
		tb.setNo(sb.getNo());
		tb.setNormalTradePrice(sb.getNormalTradePrice());
		tb.setReplaceTradePrice(sb.getReplaceTradePrice());
		tb.setSupportTradePrice(sb.getSupportTradePrice());
		tb.setTradePowerQuantity(sb.getTradePowerQuantity());
		tb.setValidYear(sb.getValidYear());
		tb.setVoltageType(sb.getVoltageType());
	}
}
