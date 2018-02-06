package com.warrior.eem.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.SellPowerAgreementDao;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.SellPowerAgreementMonthData;
import com.warrior.eem.entity.vo.SellAgreementCdtVo;
import com.warrior.eem.entity.vo.SellPowerAgreementMonthDataUpateVo;
import com.warrior.eem.entity.vo.SellPowerAgreementMonthDataVo;
import com.warrior.eem.entity.vo.SellPowerAgreementUpdateVo;
import com.warrior.eem.entity.vo.SellPowerAgreementVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.SellPowerAgreementService;
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
		SellPowerAgreement tb = (SellPowerAgreement) dbo;
		SellPowerAgreementVo sb = (SellPowerAgreementVo) vo;
		mergeProps(tb, sb);
		return tb;
	}

	@Override
	SellPowerAgreement convertVoToDoForCreate(Serializable vo) {
		SellPowerAgreement tb = new SellPowerAgreement();
		SellPowerAgreementVo sb = (SellPowerAgreementVo) vo;
		mergeProps(tb, sb);
		tb.setCreateTime(new Date());
		// TODO 当前登录用户
		tb.setCreator(null);
		return tb;
	}

	private void mergeProps(SellPowerAgreement tb, SellPowerAgreementVo sb) {
		PowerCustomer pc = pcDao.getEntity(sb.getCustomerId());
		if (pc == null) {
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
	public void saveAndUpdateAgreement(MultipartFile file, SellPowerAgreementUpdateVo e,
			SellPowerAgreementMonthDataUpateVo sellPowerAgreementMonthUpdateVo) {
		if (file == null) {
			throw new EemException("附件不能为空");
		}
		try {
			EntityValidator.checkEntity(e);
			EntityValidator.checkEntity(sellPowerAgreementMonthUpdateVo);
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
			attamentName = sa.getAttachment();
		} else {
			throw new EemException("id不能为空");
		}
		try {
			String fileName = FileUtil.saveFile(baseDir, file.getOriginalFilename(), file.getInputStream());
			((SellPowerAgreementVo) e).setAttachment(fileName);
		} catch (IOException e1) {
			throw new EemException("读取附件失败，请联系管理员");
		}
		try {
			sa = convertVoToDoForUpdate(sa, e);
			sa.setMonthData(convertMonthVoToDo(sa.getMonthData(), sellPowerAgreementMonthUpdateVo));
			updateEntity(sa);
		} catch (Exception e2) { // 更新失败 删除附件
			FileUtil.deleteFile(baseDir + sa.getAttachment());
		}
		if (attamentName != null && attamentName.trim().length() > 0) {
			FileUtil.deleteFile(baseDir + attamentName);
		}
	}

	@Override
	public void deleteAgreement(Serializable id) {
		SellPowerAgreement spa = (SellPowerAgreement)getEntity(id);
		if(spa != null) {
			FileUtil.deleteFile(baseDir + spa.getAttachment()); 
		}
		deleteEntity(id);
	}
}
