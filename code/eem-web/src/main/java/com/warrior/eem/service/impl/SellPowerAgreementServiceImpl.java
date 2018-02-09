package com.warrior.eem.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.SellPowerAgreementDao;
import com.warrior.eem.dao.support.Joiner;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.MultiSelector;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.SellPowerAgreementMonthData;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.entity.vo.SellAgreementCdtVo;
import com.warrior.eem.entity.vo.SellPowerAgreementMonthDataVo;
import com.warrior.eem.entity.vo.SellPowerAgreementVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.SellPowerAgreementService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.FileUtil;

/**
 * 售电合约服务
 * 
 * @author seangan
 *
 */
@Service
public class SellPowerAgreementServiceImpl extends AbstractServiceImpl<SellPowerAgreement>
		implements SellPowerAgreementService {

	@Value("${sell_power_agreement_base_dir}")
	private String baseDir;

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
		Order order = new Order();
		order.addOrder("id", Order_Type.DESC);
		req.setOrder(order);
		Joiner joiner = new Joiner();
		joiner.add("customer");
		req.setJoiner(joiner);
		MultiSelector ms = new MultiSelector();
		ms.addSelectProp("id");
		ms.addSelectProp("customer.name");
		ms.addSelectProp("customerNo");
		ms.addSelectProp("name");
		ms.addSelectProp("No");
		ms.addSelectProp("validYear");
		ms.addSelectProp("voltageType");
		ms.addSelectProp("tradePowerQuantity");
		ms.addSelectProp("attachment");
		ms.addSelectProp("createTime");
		req.setSelect(ms);
		req.setPage(page);
		LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
		if (cdt.getName() != null && cdt.getName().trim().length() > 0) {
			sqlCdt = sqlCdt.and(SimpleCondition.like("name", "%" + cdt.getName() + "%"));
		}
		if (cdt.getValidYear() != null && cdt.getValidYear().trim().length() > 0) {
			sqlCdt = sqlCdt.and(SimpleCondition.equal("validYear", cdt.getValidYear()));
		}
		req.setCdt(sqlCdt);
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... condition) {
		return null;
	}

	@Override
	SellPowerAgreement convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		SellPowerAgreement tb = (SellPowerAgreement) dbo;
		mergeProps(tb, (SellPowerAgreementVo) vo);
		return tb;
	}

	@Override
	SellPowerAgreement convertVoToDoForCreate(Serializable vo) {
		SellPowerAgreement tb = new SellPowerAgreement();
		mergeProps(tb, (SellPowerAgreementVo) vo);
		tb.setCreateTime(new Date());
		tb.setCreator(EemSession.getCurrentUser());
		return tb;
	}

	private void mergeProps(SellPowerAgreement tb, SellPowerAgreementVo sb) {
		PowerCustomer pc = pcDao.getEntity(sb.getCustomerId());
		if (pc == null) {
			throw new EemException("无效的电力用户id：" + sb.getCustomerId());
		}
		if (sb.getAttachment() != null && sb.getAttachment().trim().length() > 0) {
			tb.setAttachment(sb.getAttachment());
		}
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

	@Override
	@Transactional
	public void saveAndCreateAgreement(MultipartFile file, SellPowerAgreementVo e,
			SellPowerAgreementMonthDataVo sellPowerAgreementMonthVo) {
		if (file == null) {
			throw new EemException("附件信息不能为空");
		}
		try {
			EntityValidator.checkEntity(e);
			EntityValidator.checkEntity(sellPowerAgreementMonthVo);
		} catch (IllegalAccessException | SecurityException e1) {
			throw new EemException("解析参数失败");
		}
		try {
			String fileName = FileUtil.saveFile(baseDir, file.getOriginalFilename(), file.getInputStream());
			((SellPowerAgreementVo) e).setAttachment(fileName);
		} catch (IOException e1) {
			throw new EemException("读取附件失败，请联系管理员");
		}
		try {
			SellPowerAgreement spa = convertVoToDoForCreate(e);
			spa.setMonthData(convertMonthVoToDo(new SellPowerAgreementMonthData(), sellPowerAgreementMonthVo));
			createEntity(spa);
		} catch (Exception e2) { // 创建失败 删除附件
			FileUtil.deleteFile(baseDir + ((SellPowerAgreementVo) e).getAttachment());
			throw new EemException(e2.getMessage());
		}
	}

	private SellPowerAgreementMonthData convertMonthVoToDo(SellPowerAgreementMonthData monthDo,
			SellPowerAgreementMonthDataVo monthVo) {
		if (monthVo == null) {
			throw new EemException("售电合约月度信息不能为空");
		}
		monthDo.setApril(monthVo.getApril());
		monthDo.setAugust(monthVo.getAugust());
		monthDo.setDecember(monthVo.getDecember());
		monthDo.setFebruary(monthVo.getFebruary());
		monthDo.setJanuary(monthVo.getJanuary());
		monthDo.setJuly(monthVo.getJuly());
		monthDo.setJune(monthVo.getJune());
		monthDo.setMarch(monthVo.getMarch());
		monthDo.setMay(monthVo.getMay());
		monthDo.setNovember(monthVo.getNovember());
		monthDo.setOctober(monthVo.getOctober());
		monthDo.setSeptember(monthVo.getSeptember());
		return monthDo;
	}

	@Override
	@Transactional
	public void saveAndUpdateAgreement(MultipartFile file, SellPowerAgreementVo e,
			SellPowerAgreementMonthDataVo sellPowerAgreementMonthVo) {
		try {
			EntityValidator.checkEntity(e);
			EntityValidator.checkEntity(sellPowerAgreementMonthVo);
		} catch (IllegalAccessException | SecurityException e1) {
			throw new EemException("解析参数失败");
		}
		String attamentName = "";
		SellPowerAgreement sa = null;
		if (e.getId() != null) {
			Long idNum = -1L;
			try {
				idNum = Long.valueOf(e.getId());
			} catch (NumberFormatException e1) {
				throw new EemException("id必须为数字");
			}
			sa = (SellPowerAgreement) getEntity(idNum);
			if (sa == null) {
				throw new EemException("无效的id（" + e.getId() + "）");
			}
		} else {
			throw new EemException("id不能为空");
		}
		boolean hasAttachment = (file != null && file.getSize() > 0);
		if (hasAttachment) { // 更新附件
			try {
				String fileName = FileUtil.saveFile(baseDir, file.getOriginalFilename(), file.getInputStream());
				((SellPowerAgreementVo) e).setAttachment(fileName);
			} catch (IOException e1) {
				throw new EemException("读取附件失败，请联系管理员");
			}
			attamentName = sa.getAttachment();
		}
		try {
			sa = convertVoToDoForUpdate(sa, e);
			sa.setMonthData(convertMonthVoToDo(sa.getMonthData(), sellPowerAgreementMonthVo));
			updateEntity(sa);
		} catch (Exception e2) {
			if (hasAttachment) {// 更新失败 如果有上传附件则删除附件
				FileUtil.deleteFile(baseDir + sa.getAttachment());
			}
			throw new EemException(e2.getMessage());
		}
		if (attamentName != null && attamentName.trim().length() > 0) {
			FileUtil.deleteFile(baseDir + attamentName);
		}
	}

	@Override
	@Transactional
	public void deleteAgreement(Serializable id) {
		SellPowerAgreement spa = (SellPowerAgreement) getEntity(id);
		if (spa != null) {
			FileUtil.deleteFile(baseDir + spa.getAttachment());
		}
		deleteEntity(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageVo listEntities(Serializable... conditions) {
		PageVo pageVo = (PageVo) super.listEntities(conditions);
		List<Object[]> arrList = (List<Object[]>) pageVo.getDatas();
		List<Map<String, Object>> res = new LinkedList<Map<String, Object>>();
		List<String> propNames = new ArrayList<String>();
		propNames.add("id");
		propNames.add("customerName");
		propNames.add("customerNo");
		propNames.add("name");
		propNames.add("No");
		propNames.add("validYear");
		propNames.add("voltageType");
		propNames.add("tradePowerQuantity");
		propNames.add("attachment");
		propNames.add("createTime");
		Map<String, Object> resItem = null;
		for (Object[] arr : arrList) {
			int i = 0;
			arr[arr.length - 1] = format((Timestamp) arr[arr.length - 1]);
			resItem = new HashMap<String, Object>();
			for (Object obj : arr) {
				resItem.put(propNames.get(i), obj);
				i++;
			}
			res.add(resItem);
		}
		pageVo.setDatas(res);
		return pageVo;
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return threadLocal.get().format(date);
	}

	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

}
