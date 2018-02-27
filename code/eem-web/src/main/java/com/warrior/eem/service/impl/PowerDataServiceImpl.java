package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.PowerDataDao;
import com.warrior.eem.dao.SellPowerAgreementDao;
import com.warrior.eem.dao.support.Joiner;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.PowerData;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.vo.ContractAndPracticalItem;
import com.warrior.eem.entity.vo.ContractAndPracticalReqVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.PowerDataCdtVo;
import com.warrior.eem.entity.vo.PowerDataVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.PowerDataService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.ToolUtil;

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

	@Autowired
	private SellPowerAgreementDao spaDao;

	@Override
	IDao<PowerData> getDao() {
		return pdDao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		PowerDataCdtVo cdt = (PowerDataCdtVo) conditions[0];
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		Order order = new Order();
		order.addOrder((String) conditions[3], Order.valueOf((String) conditions[4]));
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
		return mergeProps((PowerData) dbo, (PowerDataVo) vo);
	}

	@Override
	PowerData convertVoToDoForCreate(Serializable vo) {
		PowerData pd = mergeProps(new PowerData(), (PowerDataVo) vo);
		pd.setCreateTime(new Date());
		pd.setCreator(EemSession.getCurrentUser());
		return pd;

	}

	private PowerData mergeProps(PowerData pd, PowerDataVo pvo) {
		PowerCustomer pc = pcDao.getEntity(pvo.getCustomerId());
		if (pc == null) {
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public PageVo listContractAndpracticalData(ContractAndPracticalReqVo param) {
		List<ContractAndPracticalItem> capis = new LinkedList<ContractAndPracticalItem>();
		SqlRequest req = null;
		LogicalCondition cdt = null;
		String startTime = param.getStartTime();
		String endTime = param.getEndTime();
		if (param != null) {
			req = new SqlRequest();
			cdt = LogicalCondition.emptyOfTrue();
			if (ToolUtil.isStringEmpty(param.getStartTime()) && ToolUtil.isStringEmpty(param.getEndTime())) {
			} else {
				if (!ToolUtil.isStringEmpty(startTime) && !ToolUtil.isStringEmpty(endTime)) {
					if (param.getType() == ContractAndPracticalReqVo.MONTH_STATIC) { // 按照月
					} else if (param.getType() == ContractAndPracticalReqVo.YEAR_STATIC) { // 按照年
						param.setStartTime(startTime + "01");
						param.setEndTime(endTime + "12");
					} else {
						throw new EemException("无效的类型");
					}
					cdt = cdt.and(SimpleCondition.between("month", param.getStartTime(), param.getEndTime()));
				} else {
					throw new EemException("开始/结束时间必须同时选择或者不选");
				}
			}
			if (!ToolUtil.isStringEmpty(param.getCustomerId())) {
				try {
					if (pcDao.getEntity(Long.valueOf(param.getCustomerId())) == null) {
						throw new EemException("无效的电力用户");
					}
				} catch (NumberFormatException e) {
					throw new EemException("错误的电力用户id格式");
				}
				Joiner joiner = new Joiner();
				joiner.add("customer");
				req.setJoiner(joiner);
				cdt = cdt.and(SimpleCondition.equal("customer.id", param.getCustomerId()));
			}
			if (!ToolUtil.isStringEmpty(param.getCustomerNo())) {
				cdt = cdt.and(SimpleCondition.equal("customerNo", param.getCustomerNo()));
			}
			if (!ToolUtil.isStringEmpty(param.getVoltageType())) {
				cdt = cdt.and(SimpleCondition.equal("voltageType", param.getVoltageType()));
			}
			if (!ToolUtil.isStringEmpty(param.getTradeType())) {
				cdt = cdt.and(SimpleCondition.equal("tradeType", param.getTradeType()));
			}
			req.setCdt(cdt);
		}
		Order order = new Order();
		order.addOrder("month", Order_Type.ASC);
		req.setOrder(order);
		List<PowerData> datas = (List<PowerData>) getDao().listDos(req);
		long count = getDao().countDos(req);
		if (datas != null && datas.size() > 0) {
			// 售电合约 单价（各个月份）
			req = new SqlRequest();
			cdt = LogicalCondition.emptyOfTrue();
			if (!ToolUtil.isStringEmpty(param.getCustomerId())) {
				try {
					if (pcDao.getEntity(Long.valueOf(param.getCustomerId())) == null) {
						throw new EemException("无效的电力用户");
					}
				} catch (NumberFormatException e) {
					throw new EemException("错误的电力用户id格式");
				}
				Joiner joiner = new Joiner();
				joiner.add("customer");
				req.setJoiner(joiner);
				cdt = cdt.and(SimpleCondition.equal("customer.id", param.getCustomerId()));

				if (!ToolUtil.isStringEmpty(startTime) && !ToolUtil.isStringEmpty(endTime)) {
					cdt = cdt.and(SimpleCondition.between("validYear", param.getStartTime().substring(0, 3),
							param.getStartTime().substring(0, 3)));
				}
			}
			List<SellPowerAgreement> spas = (List<SellPowerAgreement>) spaDao.listDos(req);
			int size = spas.size();
			datas.forEach(data -> {
				BigDecimal unitPrice = null;
				for (int i = size - 1; i >= 0; i--) { // 年合约列表
					SellPowerAgreement spa = spas.get(i);
					if (data.getMonth().startsWith(spa.getValidYear())) {
						unitPrice = spa.createUnitPriceBySellType(data.getTradeType()); // 电单价
						break;
					}
				}
				if (unitPrice == null) {
					throw new EemException("未找到月份（" + data.getMonth() + "）对应的售电合约信息");
				}
				capis.add(buildContractAndPracticalItem(data, unitPrice));
			});
		}
		return new PageVo(count, capis);
	}

	/**
	 * 构建item对象
	 * 
	 * @param data
	 * @param unitPrice
	 * @return
	 */
	private ContractAndPracticalItem buildContractAndPracticalItem(PowerData data, BigDecimal unitPrice) {
		// 有效电量
		BigDecimal validKwh = data.getFlatKwh().add(data.getPeakKwh()).add(data.getTroughKwh());
		// 无效电量
		BigDecimal invalidKwh = data.getIdleKwh();
		double num = validKwh.doubleValue()
				/ Math.sqrt(Math.pow(validKwh.doubleValue(), 2) + Math.pow(invalidKwh.doubleValue(), 2));

		// TODO 需要乘以系数
		data.setFlatKwh(data.getFlatKwh().multiply(unitPrice)); // 平段
		data.setPeakKwh(data.getPeakKwh().multiply(unitPrice)); // 高峰
		data.setTroughKwh(data.getTroughKwh().multiply(unitPrice)); // 低谷

		BigDecimal validPrice = data.getFlatKwh().add(data.getPeakKwh()).add(data.getTroughKwh());
		double totalPrice = validPrice.doubleValue() + (validPrice.doubleValue() * num);
		ContractAndPracticalItem item = new ContractAndPracticalItem();
		item.setCustomerName(data.getCustomer().getNickName());
		item.setCustomerNo(data.getCustomerNo());
		item.setEmNo(data.getEmNo());
		item.setFlatPrice(data.getFlatKwh());
		item.setValidPrice(validPrice);
		item.setMonth(data.getMonth());
		item.setPeakPrice(data.getPeakKwh());
		item.setTotalPrice(totalPrice);
		item.setTradePrice(unitPrice);
		item.setTradeType(data.getTradeType());
		item.setTroughPrice(data.getTroughKwh());
		item.setVoltageType(data.getVoltageType());
		return item;
	}
}
