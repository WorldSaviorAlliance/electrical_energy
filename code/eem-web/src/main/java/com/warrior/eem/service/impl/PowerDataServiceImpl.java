package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.PowerDataDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.PowerData;
import com.warrior.eem.entity.vo.PowerDataCdtVo;
import com.warrior.eem.entity.vo.PowerDataVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.PowerDataService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;

/**
 * 电量服务
 * 
 * @author seangan
 *
 */
@Service
public class PowerDataServiceImpl extends AbstractServiceImpl<PowerData> implements PowerDataService {

	@Autowired
	private PowerDataDao pdDao;
	
	@Autowired
	private PowerCustomerDao pcDao;

	@Override
	IDao<PowerData> getDao() {
		return pdDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		PowerDataCdtVo cdt = (PowerDataCdtVo) conditions[0];
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		Order order = new Order();
		order.addOrder((String)conditions[3], Order.valueOf((String)conditions[4]));
		SqlRequest req = new SqlRequest();
		req.setPage(page);
		req.setOrder(order);
		if (cdt != null) {
			try {
				EntityValidator.checkEntity(cdt);
			} catch (IllegalAccessException | SecurityException e) {
				throw new EemException("电量列表搜索条件解析失败");
			}
			LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
			if (cdt.getName() != null && cdt.getName().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
			}
			if (cdt.getBeginMonth() != null && cdt.getBeginMonth().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.ge("month", cdt.getBeginMonth()));
			}
			if (cdt.getEndMonth() != null && cdt.getEndMonth().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.le("month", cdt.getEndMonth()));
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
	PowerData convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return mergeProps((PowerData)dbo, (PowerDataVo)vo);
	}

	@Override
	PowerData convertVoToDoForCreate(Serializable vo) {
		PowerData pd = mergeProps(new PowerData(), (PowerDataVo)vo);
		pd.setCreateTime(new Date());
		pd.setCreator(EemSession.getCurrentUser());
		return pd;
		
	}
	
	private PowerData mergeProps(PowerData pd, PowerDataVo pvo) {
		PowerCustomer pc = pcDao.getEntity(pvo.getCustomerId());
		if(pc == null) {
			throw new EemException("不存在的用户id：" + pvo.getCustomerId());
		}
		pd.setCustomer(pc);
		pd.setCustomerNo(pvo.getCustomerNo());
		pd.setEmNo(pvo.getEmNo());
		pd.setFlatKwh(pvo.getFlatKwh());
		pd.setIdleKwh(pvo.getIdleKwh());
		pd.setMonth(pvo.getMonth());
		pd.setPeakKwh(pvo.getPeakKwh());
		pd.setTradeType(pvo.getTradeType());
		pd.setTroughKwh(pvo.getTroughKwh());
		pd.setVoltageType(pvo.getVoltageType());
		return pd;
	}
}
