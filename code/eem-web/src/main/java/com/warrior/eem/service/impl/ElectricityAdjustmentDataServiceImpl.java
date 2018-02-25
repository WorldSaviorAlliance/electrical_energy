package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.ElectricityAdjustmentDataDao;
import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.SellPowerAgreementDao;
import com.warrior.eem.dao.support.Joiner;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.ElectricityAdjustmentData;
import com.warrior.eem.entity.ElectricityAdjustmentData.AdjustmentType;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.vo.ElectricityAdjustmentDataCondition;
import com.warrior.eem.entity.vo.ElectricityAdjustmentDataUpdateVO;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.ElectricityAdjustmentDataService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;

@Service
public class ElectricityAdjustmentDataServiceImpl extends AbstractServiceImpl<ElectricityAdjustmentData>
		implements ElectricityAdjustmentDataService {

	@Autowired
	private ElectricityAdjustmentDataDao adjustmentDAO;

	@Autowired
	private PowerCustomerDao customerDAO;

	@Autowired
	private SellPowerAgreementDao agreementDAO;

	@Override
	IDao<ElectricityAdjustmentData> getDao() {
		return this.adjustmentDAO;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {

		ElectricityAdjustmentDataCondition condition = (ElectricityAdjustmentDataCondition) conditions[0];
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		SqlRequest req = new SqlRequest();
		Order order = new Order();
		req.setPage(page);
		if (condition != null) {
			LogicalCondition sqlcdt = LogicalCondition.emptyOfTrue();
			if (condition.getOrder() != null) {
				order.addOrder("month", condition.getOrder() == 1 ? Order_Type.DESC : Order_Type.ASC);
			} else {
				order.addOrder("month", Order_Type.DESC);
			}
			req.setOrder(order);
			if (condition.getCustomerName() != null && condition.getCustomerName().trim().length() > 0) {
				Joiner joiner = new Joiner();
				joiner.add("customer");
				req.setJoiner(joiner);
				sqlcdt = sqlcdt.and(SimpleCondition.like("customer.name", condition.getCustomerName() + "%"));
			}
			if (condition.getBeginMonth() != null && condition.getBeginMonth().trim().length() > 0) {
				sqlcdt = sqlcdt.and(SimpleCondition.ge("month", condition.getBeginMonth()));
			}
			if (condition.getEndMonth() != null && condition.getEndMonth().length() > 0) {
				sqlcdt = sqlcdt.and(SimpleCondition.le("month", condition.getEndMonth()));
			}
			req.setCdt(sqlcdt);
		} else {
			order.addOrder("month", Order_Type.DESC);
			req.setOrder(order);
		}
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	ElectricityAdjustmentData convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	ElectricityAdjustmentData convertVoToDoForCreate(Serializable vo) {
		ElectricityAdjustmentData adjustmentData = new ElectricityAdjustmentData();
		adjustmentData.setAdjustment(((ElectricityAdjustmentDataUpdateVO) vo).getQuantity());
		adjustmentData.setAdjustmentType(
				((ElectricityAdjustmentDataUpdateVO) vo).getAdjustmentType() == 1 ? AdjustmentType.DESC
						: AdjustmentType.ASC);
		adjustmentData.setContractNumber(((ElectricityAdjustmentDataUpdateVO) vo).getContractNumber());
		PowerCustomer customer = customerDAO.getEntity(((ElectricityAdjustmentDataUpdateVO) vo).getCustomerId());
		if (customer == null) {
			throw new EemException("不合法电力用户id");
		}
		adjustmentData.setCustomer(customer);
		adjustmentData.setCustomerNumber(((ElectricityAdjustmentDataUpdateVO) vo).getCustomerNumber());
		adjustmentData.setMonth(((ElectricityAdjustmentDataUpdateVO) vo).getMonth());
		adjustmentData.setPrice(((ElectricityAdjustmentDataUpdateVO) vo).getPrice());
		SellPowerAgreement agreement = agreementDAO.getEntity(((ElectricityAdjustmentDataUpdateVO) vo).getContractId());
		if (agreement == null) {
			throw new EemException("不合法售电合同id");
		}
		adjustmentData.setSellAgreement(agreement);

		adjustmentData.setTradeType(((ElectricityAdjustmentDataUpdateVO) vo).getTradeType());
		adjustmentData.setValidYear(((ElectricityAdjustmentDataUpdateVO) vo).getValidYear());
		adjustmentData.setVoltageType(((ElectricityAdjustmentDataUpdateVO) vo).getVoltageType());
		return adjustmentData;
	}

	@Override
	@Transactional
	public void saveOrUpdateElectricityAdjustmentData(ElectricityAdjustmentDataUpdateVO vo) {
		try {
			EntityValidator.checkEntity(vo);
		} catch (IllegalAccessException | SecurityException e) {
			throw new IllegalArgumentException("参数解析过程失败， 请联系管理员!");
		}
		ElectricityAdjustmentData adjustData = convertVoToDoForCreate(vo);
		if (vo.getId() != null) {
			ElectricityAdjustmentData oldAjustData = (ElectricityAdjustmentData) getEntity(vo.getId());
			if (oldAjustData == null) {
				throw new EemException("合同调整数据id不存在！");
			}
			adjustData.setId(vo.getId());
			adjustData.setCreateTime(oldAjustData.getCreateTime());
			updateEntity(adjustData);
		} else {
			adjustData.setCreateTime(new Date());
			adjustData.setCreator(EemSession.getCurrentUser());
			createEntity(adjustData);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public PageVo listEntities(Serializable... conditions) {
		PageVo pageVo = super.listEntities(conditions);
		List<ElectricityAdjustmentData> objects = (List<ElectricityAdjustmentData>) pageVo.getDatas();
		List<ElectricityAdjustmentDataUpdateVO> list = new ArrayList<>();
		for (ElectricityAdjustmentData object : objects) {
			ElectricityAdjustmentDataUpdateVO vo = new ElectricityAdjustmentDataUpdateVO();
			vo.setAdjustmentType(object.getAdjustmentType().equals(AdjustmentType.ASC) ? 0 : 1);
			vo.setContractId(object.getSellAgreement().getId());
			vo.setContractNumber(object.getContractNumber());
			vo.setContractName(object.getSellAgreement().getName());
			vo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(object.getCreateTime()));
			vo.setCustomerId(object.getCustomer().getId());
			vo.setCustomerName(object.getCustomer().getName());
			vo.setCustomerNumber(object.getCustomerNumber());
			vo.setId(object.getId());
			vo.setMonth(object.getMonth());
			vo.setQuantity(object.getAdjustment());
			vo.setTradeType(object.getTradeType());
			vo.setUserName(object.getCreator() == null ? null
					: object.getCreator().getName() == null ? null : object.getCreator().getName());
			vo.setValidYear(object.getValidYear());
			vo.setVoltageType(object.getVoltageType());
			vo.setPrice(object.getPrice());
			list.add(vo);
		}
		pageVo.setDatas(list);
		return pageVo;
	}
}
