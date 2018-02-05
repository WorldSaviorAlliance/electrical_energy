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
import com.warrior.eem.entity.vo.SellAgreementCdtVo;
import com.warrior.eem.entity.vo.SellPowerAgreementUpdateVo;
import com.warrior.eem.entity.vo.SellPowerAgreementVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.SellPowerAgreementMonthDataService;
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
	private SellPowerAgreementMonthDataService spamdService;

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
	public void saveAndCreateAgreement(MultipartFile file, SellPowerAgreementVo e) {
		if(file == null) {
			throw new EemException("附件信息不能为空");
		}
		try {
			String fileName = FileUtil.saveFile(baseDir, file.getOriginalFilename(), file.getInputStream());
			((SellPowerAgreementVo) e).setAttachment(fileName);
		} catch (IOException e1) {
			throw new EemException("读取附件失败，请联系管理员");
		}
		createEntity(e);
		// TODO 月度信息
//		spamdService.createEntity(e);
	}

	@Override
	@Transactional
	public void saveAndUpdateAgreement(MultipartFile file, SellPowerAgreementUpdateVo e) {
		if(file == null) {
			throw new EemException("附件不能为空");
		}
		String attamentName = "";
		if(e.getId() != null) {
			SellPowerAgreement sa = (SellPowerAgreement)getEntity(e.getId());
			if(sa == null) {
				throw new EemException("无效的id（" + e.getId() + "）");
			}
			attamentName = sa.getAttachment();
		} else {
			throw new EemException("id不能为空");
		}
		try {
			String fileName = FileUtil.saveFile(baseDir, file.getName(), file.getInputStream());
			((SellPowerAgreementVo) e).setAttachment(fileName);
		} catch (IOException e1) {
			throw new EemException("读取附件失败，请联系管理员");
		}
		updateEntity(e);
		// TODO 月度信息
//		spamdService.updateEntity();
		if(attamentName != null && attamentName.trim().length() > 0) {
			FileUtil.deleteFile(baseDir + attamentName);
		}
	}
}
