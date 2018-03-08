package com.warrior.eem.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.warrior.eem.dao.ElectricityAdjustmentDataDao;
import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.PowerDataDao;
import com.warrior.eem.dao.PriceCoefficientDao;
import com.warrior.eem.dao.SellPowerAgreementDao;
import com.warrior.eem.dao.support.Joiner;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.ElectricityAdjustmentData;
import com.warrior.eem.entity.ElectricityAdjustmentData.AdjustmentType;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.PowerData;
import com.warrior.eem.entity.PriceCoefficient;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.SellPowerAgreement.Sell_Power_Price_Type;
import com.warrior.eem.entity.vo.PowerMonthPriceInfoItemVo;
import com.warrior.eem.entity.vo.PowerMonthPriceReqVo;
import com.warrior.eem.entity.vo.ContractAndPracticalReqVo;
import com.warrior.eem.entity.vo.ContractAndPracticalResVo;
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

	@Autowired
	private ElectricityAdjustmentDataDao eadDao;

	@Autowired
	private PriceCoefficientDao pcfDao;

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
		final List<ContractAndPracticalResVo> capis = new LinkedList<ContractAndPracticalResVo>();
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
		Order or = new Order();
		or.addOrder("month", Order_Type.DESC);
		req.setOrder(or);
		List<PowerData> datas = (List<PowerData>) getDao().listDos(req);
		long count = getDao().countDos(req);
		Map<String, ContractAndPracticalResVo> resMap = new HashMap<String, ContractAndPracticalResVo>();
		if (datas != null && datas.size() > 0) {
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
			if (spas == null || spas.size() == 0) {
				throw new EemException("未找到这个时间段的售电合约信息");
			}
			datas.forEach(data -> {
				ContractAndPracticalResVo monthItem = new ContractAndPracticalResVo();
				SellPowerAgreement targetSpa = filterSellPowerAgreementByMonthAndCustomerId(spas,
						data.getCustomer().getId(), data.getMonth());

				double[] monthAdjustmentArr = getAdjustmentPowerQuantityAndPriceByMonth(data.getMonth(), targetSpa);
				double[] monthSapqpArr = getSellAgreementPowerQuantityAndPriceByMonth(data.getMonth(), targetSpa);

				// 实际电量
				monthItem.setPracticalData(
						data.getFlatKwh().add(data.getPeakKwh()).add(data.getTroughKwh()).doubleValue());

				// 预测电量（合约+-调整）
				monthItem.setContractData(monthSapqpArr[0] + monthSapqpArr[1] + monthSapqpArr[2] + monthSapqpArr[3]
						+ monthAdjustmentArr[0] + monthAdjustmentArr[1] + monthAdjustmentArr[2]
						+ monthAdjustmentArr[3]);
				// 日期
				monthItem.setDate(data.getMonth());

				if (param.getType() == ContractAndPracticalReqVo.MONTH_STATIC) {
					capis.add(monthItem);
				} else {
					ContractAndPracticalResVo resItem = resMap.get(targetSpa.getValidYear());
					if (resItem == null) {
						monthItem.setDate(targetSpa.getValidYear());
						resMap.put(targetSpa.getValidYear(), monthItem);
					} else {
						resItem.setContractData(resItem.getContractData() + monthItem.getContractData());
						resItem.setPracticalData(resItem.getPracticalData() + monthItem.getPracticalData());
					}
				}
			});
		}
		if (param.getType() == ContractAndPracticalReqVo.YEAR_STATIC) {
			capis.clear();
			resMap.forEach((year, item) -> {
				capis.add(item);
			});
		}
		return new PageVo(count, capis);
	}

	/**
	 * 过滤得到对应的售电合约实体
	 * 
	 * @param spas
	 * @param customerId
	 * @param month
	 * @return
	 */
	private SellPowerAgreement filterSellPowerAgreementByMonthAndCustomerId(List<SellPowerAgreement> spas,
			Long customerId, String month) {
		SellPowerAgreement targetSpa = null;
		int size = spas.size();
		for (int i = size - 1; i >= 0; i--) { // 年合约列表
			SellPowerAgreement spa = spas.get(i);
			if (month.startsWith(spa.getValidYear()) && customerId == spa.getCustomer().getId()) {
				targetSpa = spa;
				break;
			}
		}
		if (targetSpa == null) {
			throw new EemException("未找到月份（" + month + "）对应的售电合约信息");
		}
		return targetSpa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageVo listPowerMonthPriceData(PowerMonthPriceReqVo param, String order) {
		List<PowerMonthPriceInfoItemVo> capis = new LinkedList<PowerMonthPriceInfoItemVo>();
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
					cdt = cdt.and(SimpleCondition.between("month", param.getStartTime(), param.getEndTime()));
				} else {
					throw new EemException("开始/结束时间必须同时选择或者不选");
				}
			}
			if (!ToolUtil.isStringEmpty(param.getCustomerName())) {
				Joiner joiner = new Joiner();
				joiner.add("customer");
				req.setJoiner(joiner);
				cdt = cdt.and(SimpleCondition.like("customer.nickName", "%" + param.getCustomerName() + "%"));
			}
			req.setCdt(cdt);
		}
		Order or = new Order();
		or.addOrder("month", Order.ASC.equalsIgnoreCase(order) ? Order_Type.ASC : Order_Type.DESC);
		req.setOrder(or);
		List<PowerData> datas = (List<PowerData>) getDao().listDos(req);
		long count = getDao().countDos(req);
		if (datas != null && datas.size() > 0) {
			// 售电合约 单价（各个月份）
			req = new SqlRequest();
			cdt = LogicalCondition.emptyOfTrue();
			if (!ToolUtil.isStringEmpty(param.getCustomerName())) {
				Joiner joiner = new Joiner();
				joiner.add("customer");
				req.setJoiner(joiner);
				cdt = cdt.and(SimpleCondition.like("customer.nickName", "%" + param.getCustomerName() + "%"));

				if (!ToolUtil.isStringEmpty(startTime) && !ToolUtil.isStringEmpty(endTime)) {
					cdt = cdt.and(SimpleCondition.between("validYear", param.getStartTime().substring(0, 3),
							param.getStartTime().substring(0, 3)));
				}
			}
			List<SellPowerAgreement> spas = (List<SellPowerAgreement>) spaDao.listDos(req);
			if (spas == null || spas.size() == 0) {
				throw new EemException("未找到这个时间段的售电合约信息");
			}
			datas.forEach(data -> {
				SellPowerAgreement targetSpa = filterSellPowerAgreementByMonthAndCustomerId(spas,
						data.getCustomer().getId(), data.getMonth());
				double[] monthAdjustmentArr = getAdjustmentPowerQuantityAndPriceByMonth(data.getMonth(), targetSpa);
				double[] monthSapqpArr = getSellAgreementPowerQuantityAndPriceByMonth(data.getMonth(), targetSpa);
				capis.add(buildContractAndPracticalItem(data, monthAdjustmentArr, monthSapqpArr));
			});
		}
		return new PageVo(count, capis);
	}

	/**
	 * 获取售电合约month月对应的电量和电价数组信息 数组前四位为电量信息， 数组后四位为电价信息
	 * 
	 * @param month
	 * @param sellAgreement
	 * @return
	 */
	private double[] getSellAgreementPowerQuantityAndPriceByMonth(String month, SellPowerAgreement sellAgreement) {
		double[] monthPowerQuantityAndPriceArr = new double[8];
		try {
			Method m = sellAgreement.getMonthData().getClass().getMethod(sellAgreement.getMonthData()
					.convertMonthNumToMethodName(month.substring(month.length() - 2, month.length())));
			String monthData = (String) m.invoke(sellAgreement.getMonthData());
			String[] monthPriceAndQuantityArr = monthData.split(";");
			int i = 0;
			for (String monthPriceAndQuantity : monthPriceAndQuantityArr) {
				String[] pqArr = monthPriceAndQuantity.substring(1, monthPriceAndQuantity.length() - 1).split(":");
				for (String pq : pqArr) {
					monthPowerQuantityAndPriceArr[i] = Double.valueOf(pq);
					i++;
				}
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("执行售电合约月电量或者电价解析失败，请联系管理员", e);
			throw new EemException("执行售电合约月电量或者电价解析失败，请联系管理员");
		}
		return monthPowerQuantityAndPriceArr;
	}

	/**
	 * 返回month月对应的调整电量和电价数组信息（ 数组前四位为电量信息， 数组后四位为电价信息）
	 * 顺序与售电合约顺序保持一致(常规直购电交易电价:精准扶持直购电交易电价：自备替代直购电交易电价:富于电价)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private double[] getAdjustmentPowerQuantityAndPriceByMonth(String month, SellPowerAgreement sellAgreement) {
		double[] monthPowerQuantityAndPriceArr = new double[8];
		// 调整合约
		SqlRequest changeSellArgument = new SqlRequest();
		Joiner join = new Joiner();
		join.add("sellAgreement");
		changeSellArgument.setJoiner(join);
		changeSellArgument.setCdt(LogicalCondition.emptyOfTrue().and(SimpleCondition.equal("month", month))
				.and(SimpleCondition.equal("sellAgreement.id", sellAgreement.getId())));
		List<ElectricityAdjustmentData> eads = (List<ElectricityAdjustmentData>) eadDao.listDos(changeSellArgument);
		double changedQuantity = 0;
		if (eads != null && eads.size() > 0) {
			for (ElectricityAdjustmentData ead : eads) {
				int index = 3; // 富于电
				if (Sell_Power_Price_Type.Normal.getName().equals(ead.getTradeType())) { // 常规直购电
					index = 0;
				} else if (Sell_Power_Price_Type.Support.getName().equals(ead.getTradeType())) { // 精准扶持直购电
					index = 1;
				} else if (Sell_Power_Price_Type.Replace.getName().equals(ead.getTradeType())) { // 自备替代直购电
					index = 2;
				}
				changedQuantity = ead.getAdjustmentType().equals(AdjustmentType.ASC) ? ead.getAdjustment().doubleValue()
						: -ead.getAdjustment().doubleValue();
				monthPowerQuantityAndPriceArr[index] = changedQuantity;
				monthPowerQuantityAndPriceArr[index + 4] = ead.getPrice().doubleValue();
			}
		}
		return monthPowerQuantityAndPriceArr;
	}

	/**
	 * 构建每月电费item对象
	 * 
	 * @param data
	 * @param monthAdjustmentArr
	 *            data month月对应的调整电量电价数组数据
	 * @param monthSapqpArr
	 *            data month月对应的售电合约电量电价数组数据
	 * @return
	 */
	private PowerMonthPriceInfoItemVo buildContractAndPracticalItem(PowerData data, double[] monthAdjustmentArr,
			double[] monthSapqpArr) {
		// 实际有效电量
		BigDecimal validKwh = data.getFlatKwh().add(data.getPeakKwh()).add(data.getTroughKwh());
		// 实际无效电量
		BigDecimal invalidKwh = data.getIdleKwh();
		int index = 3;
		if (Sell_Power_Price_Type.Normal.getName().equals(data.getTradeType())) { // 常规直购电
			index = 0;
		} else if (Sell_Power_Price_Type.Support.getName().equals(data.getTradeType())) { // 精准扶持直购电
			index = 1;
		} else if (Sell_Power_Price_Type.Replace.getName().equals(data.getTradeType())) { // 自备替代直购电
			index = 2;
		}
		// 单价
		BigDecimal unitPrice = new BigDecimal(monthAdjustmentArr[index + 4] + monthSapqpArr[index + 4]);
		BigDecimal[] pcs = getPriceCoefficientInfos();
		data.setFlatKwh(data.getFlatKwh().multiply(unitPrice).multiply(pcs[0])); // 平段
		data.setPeakKwh(data.getPeakKwh().multiply(unitPrice).multiply(pcs[1])); // 高峰
		data.setTroughKwh(data.getTroughKwh().multiply(unitPrice).multiply(pcs[2])); // 低谷

		BigDecimal validPrice = data.getFlatKwh().add(data.getPeakKwh()).add(data.getTroughKwh());
		double totalPrice = validPrice.doubleValue() + calculateInvalidEnergyCharge(validKwh.doubleValue(),
				invalidKwh.doubleValue(), validPrice.doubleValue());
		PowerMonthPriceInfoItemVo item = new PowerMonthPriceInfoItemVo();
		item.setCustomerName(data.getCustomer().getName());
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

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BigDecimal> statisticsCurrentMonthPowerInfo() {
		LocalDate date = LocalDate.now();
		SqlRequest req = new SqlRequest();
		Joiner joiner = new Joiner();
		joiner.add("customer");
		req.setJoiner(joiner);
		// 售电合约
		req.setCdt(LogicalCondition.emptyOfTrue().and(SimpleCondition.equal("validYear", date.getYear() + ""))
				.and(SimpleCondition.equal("customer.id", EemSession.getCurrentUser().getCustomer().getId())));
		List<SellPowerAgreement> spas = (List<SellPowerAgreement>) spaDao.listDos(req);
		if (spas == null || spas.size() == 0) {
			throw new EemException("未签署" + date.getYear() + "年售电合约");
		}
		// 电量查询
		String month = date.getYear()
				+ (date.getMonthValue() < 10 ? "0" + date.getMonthValue() : "" + date.getMonthValue());
		final double[] spaPrices = getSellAgreementPowerQuantityAndPriceByMonth(month, spas.get(0));
		final double[] adpPrices = getAdjustmentPowerQuantityAndPriceByMonth(month, spas.get(0));
		req.setCdt(LogicalCondition.emptyOfTrue().and(SimpleCondition.equal("month", month))
				.and(SimpleCondition.equal("customer.id", EemSession.getCurrentUser().getCustomer().getId())));
		List<PowerData> pds = (List<PowerData>) pdDao.listDos(req);

		final Map<String, BigDecimal> res = new HashMap<String, BigDecimal>();
		res.put("invalidQuantity", BigDecimal.ZERO);
		res.put("validQuantity", BigDecimal.ZERO);
		res.put("totalPrice", BigDecimal.ZERO);

		BigDecimal[] pcs = getPriceCoefficientInfos();

		pds.forEach(pd -> {
			int index = 3;
			if (Sell_Power_Price_Type.Normal.getName().equals(pd.getTradeType())) {
				index = 0;
			} else if (Sell_Power_Price_Type.Support.getName().equals(pd.getTradeType())) {
				index = 1;
			} else if (Sell_Power_Price_Type.Replace.getName().equals(pd.getTradeType())) {
				index = 2;
			}
			BigDecimal unitPrice = new BigDecimal(spaPrices[index + 4] + adpPrices[index + 4]);

			// 无用功电量
			res.put("invalidQuantity", res.get("invalidQuantity").add(pd.getIdleKwh()));

			// 有用功电量
			res.put("validQuantity",
					res.get("validQuantity").add(pd.getFlatKwh().add(pd.getPeakKwh()).add(pd.getTroughKwh())));

			res.put("totalPrice",
					res.get("totalPrice")
							.add((pd.getFlatKwh().multiply(unitPrice).multiply(pcs[0]))
									.add(pd.getPeakKwh().multiply(unitPrice).multiply(pcs[0]))
									.add(pd.getTroughKwh().multiply(unitPrice).multiply(pcs[0]))));
		});
		res.put("totalPrice",
				res.get("totalPrice")
						.add(new BigDecimal(calculateInvalidEnergyCharge(res.get("validQuantity").doubleValue(),
								res.get("invalidQuantity").doubleValue(), res.get("totalPrice").doubleValue()))));
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BigDecimal> statisticsYearPowerQuantity() {
		SqlRequest req = new SqlRequest();
		Joiner joiner = new Joiner();
		joiner.add("customer");
		req.setJoiner(joiner);
		req.setCdt(LogicalCondition.emptyOfTrue().and(SimpleCondition.like("month", LocalDate.now().getYear() + "%"))
				.and(SimpleCondition.equal("customer.id", EemSession.getCurrentUser().getCustomer().getId())));
		List<PowerData> pds = (List<PowerData>) pdDao.listDos(req);
		final Map<String, BigDecimal> res = new HashMap<String, BigDecimal>();
		res.put("invalidQuantity", BigDecimal.ZERO);
		res.put("flatQuantity", BigDecimal.ZERO);
		res.put("peakQuantity", BigDecimal.ZERO);
		res.put("troughQuantity", BigDecimal.ZERO);
		pds.forEach(pd -> {
			// 平段电量
			res.put("flatQuantity", res.get("flatQuantity").add(pd.getFlatKwh()));

			// 高峰电量
			res.put("peakQuantity", res.get("peakQuantity").add(pd.getPeakKwh()));

			// 低谷电量
			res.put("troughQuantity", res.get("troughQuantity").add(pd.getTroughKwh()));

		});
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BigDecimal> statisticsYearPowerPrice() {
		SqlRequest req = new SqlRequest();
		LocalDate date = LocalDate.now();
		// 售电合约
		Joiner joiner = new Joiner();
		joiner.add("customer");
		req.setJoiner(joiner);
		req.setCdt(LogicalCondition.emptyOfTrue().and(SimpleCondition.equal("validYear", date.getYear() + ""))
				.and(SimpleCondition.equal("customer.id", EemSession.getCurrentUser().getCustomer().getId())));
		List<SellPowerAgreement> spas = (List<SellPowerAgreement>) spaDao.listDos(req);
		if (spas == null || spas.size() == 0) {
			throw new EemException("未签署" + date.getYear() + "年售电合约");
		}
		// 电量查询
		String month = date.getYear()
				+ (date.getMonthValue() < 10 ? "0" + date.getMonthValue() : "" + date.getMonthValue());
		final double[] spaPrices = getSellAgreementPowerQuantityAndPriceByMonth(month, spas.get(0));
		final double[] adpPrices = getAdjustmentPowerQuantityAndPriceByMonth(month, spas.get(0));
		req.setCdt(LogicalCondition.emptyOfTrue().and(SimpleCondition.like("month", date.getYear() + "" + "%"))
				.and(SimpleCondition.equal("customer.id", EemSession.getCurrentUser().getCustomer().getId())));
		List<PowerData> pds = (List<PowerData>) pdDao.listDos(req);
		final Map<String, BigDecimal> res = new HashMap<String, BigDecimal>();
		res.put("flatPrice", BigDecimal.ZERO);
		res.put("peakPrice", BigDecimal.ZERO);
		res.put("troughPrice", BigDecimal.ZERO);

		BigDecimal[] pcs = getPriceCoefficientInfos();
		pds.forEach(pd -> {
			int index = 3;
			if (Sell_Power_Price_Type.Normal.getName().equals(pd.getTradeType())) {
				index = 0;
			} else if (Sell_Power_Price_Type.Support.getName().equals(pd.getTradeType())) {
				index = 1;
			} else if (Sell_Power_Price_Type.Replace.getName().equals(pd.getTradeType())) {
				index = 2;
			}
			BigDecimal unitPrice = new BigDecimal(spaPrices[index + 4] + adpPrices[index + 4]);

			// 平段电费
			res.put("flatPrice", res.get("flatPrice").add(pd.getFlatKwh().multiply(unitPrice).multiply(pcs[0])));

			// 高峰电费
			res.put("peakPrice", res.get("peakPrice").add(pd.getPeakKwh().multiply(unitPrice).multiply(pcs[1])));

			// 低谷电费
			res.put("troughPrice", res.get("troughPrice").add(pd.getTroughKwh().multiply(unitPrice).multiply(pcs[2])));
		});
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listMonthPowerQuantity() {
		List<Object[]> pds = null;
		List<Map<String, Object>> res = new LinkedList<Map<String, Object>>();
		if (EemSession.getCurrentUser().getCustomer() != null) {
			pds = (List<Object[]>) pdDao.listDosBySql(
					"select sum(flat_kwh),sum(trough_kwh),sum(peak_kwh),month from power_data where month like '"
							+ LocalDate.now().getYear() + "%' and customer_id = "
							+ EemSession.getCurrentUser().getCustomer().getId() + " group by month order by month asc");
		}
		if (pds != null) {
			pds.forEach(objArr -> {
				Map<String, Object> tempItem = new HashMap<String, Object>();
				// 平段电量
				tempItem.put("flatQuantity", objArr[0]);
				// 高峰电量
				tempItem.put("troughQuantity", objArr[1]);
				// 低谷电量
				tempItem.put("peakQuantity", objArr[2]);

				tempItem.put("month", objArr[3]);
				res.add(tempItem);
			});
		}
		return res;
	}

	/**
	 * 计算无用功电费
	 * 
	 * @return
	 */
	private double calculateInvalidEnergyCharge(double validKwh, double invalidKwh, double validEnergyCharge) {
		if (Math.pow(validKwh, 2) + Math.pow(invalidKwh, 2) == 0) {
			return 0;
		}
		double p = validKwh / Math.sqrt(Math.pow(validKwh, 2) + Math.pow(invalidKwh, 2));
		p = Double.valueOf(new DecimalFormat("0.00").format(p));
		if (p < 0.9) {//
			if (p <= 0.89 && p >= 0.7) {
				return validEnergyCharge * 0.5 * ((0.89 - p) * 100 + 1) / 100;
			} else if (p <= 0.69 && p >= 0.65) {
				return validEnergyCharge * 1 * ((0.69 - p) * 100 + 1) / 100;
			} else {
				return validEnergyCharge * 2 * ((0.64 - p) * 100 + 1) / 100;
			}
		} else if (p > 0.9) {
			return -(validEnergyCharge * 1.5 * (p - 0.9));
		} else {
			return 0;
		}
	}

	/**
	 * 获取电价系数数组 0: flat 1: peak 2: trough
	 * 
	 * @return
	 */
	private BigDecimal[] getPriceCoefficientInfos() {
		PriceCoefficient pcf = pcfDao.getEntity(1L);
		if (pcf == null) {
			throw new EemException("获取电价系数失败");
		}
		return new BigDecimal[] { new BigDecimal(pcf.getFlat()), new BigDecimal(pcf.getPeak()),
				new BigDecimal(pcf.getTrough()) };
	}
}
