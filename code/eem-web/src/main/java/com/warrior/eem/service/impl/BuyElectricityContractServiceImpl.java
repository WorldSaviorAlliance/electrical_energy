package com.warrior.eem.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.warrior.eem.dao.BuyContractUserInfoDao;
import com.warrior.eem.dao.BuyElectricityContractDao;
import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PowerCustomerDao;
import com.warrior.eem.dao.PowerSupplierDao;
import com.warrior.eem.dao.support.Joiner;
import com.warrior.eem.dao.support.LogicalCondition;
import com.warrior.eem.dao.support.Order;
import com.warrior.eem.dao.support.Order.Order_Type;
import com.warrior.eem.dao.support.Page;
import com.warrior.eem.dao.support.SimpleCondition;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.BuyContractUserInfo;
import com.warrior.eem.entity.BuyElectricityContract;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.PowerSupplier;
import com.warrior.eem.entity.vo.BuyContractSearchVo;
import com.warrior.eem.entity.vo.BuyContractUserInfoUpdateVo;
import com.warrior.eem.entity.vo.BuyElectricityContractUpdateVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.service.BuyElectricityContractService;
import com.warrior.eem.shiro.session.EemSession;
import com.warrior.eem.util.EntityValidator;
import com.warrior.eem.util.FileUtil;

/**
 * 购电合同
 *
 */
@Service
public class BuyElectricityContractServiceImpl extends AbstractServiceImpl<BuyElectricityContract>
		implements BuyElectricityContractService {

	@Value("${buy_power_agreement_base_dir}")
	private String baseDir;

	@Autowired
	private BuyElectricityContractDao buyContractDAO;

	@Autowired
	private BuyContractUserInfoDao buyUserInfoDAO;

	@Autowired
	private PowerCustomerDao customerDAO;

	@Autowired
	private PowerSupplierDao supplierDAO;

	@Override
	IDao<BuyElectricityContract> getDao() {
		return this.buyContractDAO;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		BuyContractSearchVo cdt = (BuyContractSearchVo) conditions[0];
		Page page = new Page((int) conditions[1], (int) conditions[2]);
		SqlRequest req = new SqlRequest();
		Order order = new Order();
		order.addOrder("validYear", Order_Type.DESC);
		req.setOrder(order);
		req.setPage(page);
		if (cdt != null) {
			LogicalCondition sqlCdt = LogicalCondition.emptyOfTrue();
			if (cdt.getSupplier() != null && cdt.getSupplier().trim().length() > 0) {
				Joiner joiner = new Joiner();
				joiner.add("supplier");
				sqlCdt = sqlCdt.and(SimpleCondition.like("supplier.name", cdt.getSupplier() + "%"));
				req.setJoiner(joiner);
			}
			if (cdt.getValidYear() != null && cdt.getValidYear().trim().length() > 0) {
				sqlCdt = sqlCdt.and(SimpleCondition.equal("validYear", cdt.getValidYear()));
			}
			req.setCdt(sqlCdt);
		}
		return req;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	BuyElectricityContract convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PageVo listEntities(Serializable... conditions) {
		PageVo pageVo = super.listEntities(conditions);
		List<BuyElectricityContract> list = (List<BuyElectricityContract>) pageVo.getDatas();
		List<BuyElectricityContractUpdateVo> contracts = new ArrayList<>();
		for (BuyElectricityContract object : list) {
			BuyElectricityContractUpdateVo contract = new BuyElectricityContractUpdateVo();
			contract.setName(object.getName());
			contract.setId(object.getId());
			contract.setNumber(object.getNumber());
			contract.setPrice(object.getPrice());
			contract.setQuantity(object.getTradeQuantity());
			if (object.getSupplier() != null) {
				contract.setSupplier(object.getSupplier().getId());
			}
			contract.setTradeType(object.getTradeType());
			contract.setValidYear(object.getValidYear());
			contract.setVoltageLevel(object.getVoltageType());
			contracts.add(contract);
		}
		pageVo.setDatas(contracts);
		return pageVo;
	}

	@Override
	BuyElectricityContract convertVoToDoForCreate(Serializable vo) {
		BuyElectricityContract contract = new BuyElectricityContract();
		contract.setId(((BuyElectricityContractUpdateVo) vo).getId());
		contract.setName(((BuyElectricityContractUpdateVo) vo).getName());
		contract.setNumber(((BuyElectricityContractUpdateVo) vo).getNumber());
		contract.setPrice(((BuyElectricityContractUpdateVo) vo).getPrice());
		contract.setTradeQuantity(((BuyElectricityContractUpdateVo) vo).getQuantity());
		contract.setValidYear(((BuyElectricityContractUpdateVo) vo).getValidYear());
		Long supplierId ;
		try {			
			supplierId= ((BuyElectricityContractUpdateVo) vo).getSupplier();
		}catch(NumberFormatException e) {
			throw new EemException("不合法电力提供商id格式");
		}
		PowerSupplier supplier = supplierDAO.getEntity(supplierId);
		if (supplier == null) {
			throw new EemException("不合法电力提供商id");
		}
		contract.setSupplier(supplier);
		contract.setTradeType(((BuyElectricityContractUpdateVo) vo).getTradeType());
		contract.setVoltageType(((BuyElectricityContractUpdateVo) vo).getVoltageLevel());
		contract.setCreator(EemSession.getCurrentUser());
		return contract;
	}

	@Override
	@Transactional
	public void saveOrUpdateBuyContractAndAttachment(BuyElectricityContractUpdateVo buyContract,
			Set<BuyContractUserInfoUpdateVo> infos, MultipartFile file, List<Long> ids) {
		if (file == null) {
			throw new EemException("附件不能为空");
		}
		BuyElectricityContract contract = convertVoToDoForCreate(buyContract);
		BuyElectricityContract oldContract = null;
		String fileName;
		try {
			fileName = FileUtil.saveFile(baseDir, file.getOriginalFilename(), file.getInputStream());
		} catch (IOException e1) {
			throw new EemException("文件读取失败，请联系管理员");
		}
		try {
			if (contract.getId() != null) {
				try {
					oldContract = getBuyContractById(contract.getId());
				} catch (NoResultException e) {
					throw new EemException("购电合同不存在！");
				}
			}
			EntityValidator.checkEntity(buyContract);
			for (BuyContractUserInfoUpdateVo obj : infos) {
				EntityValidator.checkEntity(obj);
			}
			contract.setAttachmentName(fileName);
			Set<BuyContractUserInfo> contractUserInfos = new HashSet<>();
			for (BuyContractUserInfoUpdateVo info : infos) {
				contractUserInfos.add(converseBuyContractUserInfoVoToDo(info));
			}
			contract.setContractUserInfos(contractUserInfos);
			if (contract.getId() == null) {
				Date now = new Date();
				contract.setCreateDate(now);
				getDao().createDo(contract);
			} else {
				for (Long id : ids) {
					deleteBuyContractUserInfoById(id);
				}
				contract.setCreateDate(oldContract.getCreateDate());
				getDao().updateDo(contract);
			}
		} catch (IllegalAccessException | SecurityException e) {
			FileUtil.deleteFile(baseDir + fileName);
			throw new EemException("解析参数失败，请联系管理员！");
		} catch (EemException e) {
			FileUtil.deleteFile(baseDir + fileName);
			throw e;
		} catch (Exception e) {
			FileUtil.deleteFile(baseDir + fileName);
			throw new EemException("解析过程失败， 请联系管理员！");
		}
	}

	private void deleteBuyContractUserInfoById(Long id) {
		EntityManager entityManager = buyUserInfoDAO.getEntityManager();
		BuyContractUserInfo info = entityManager.find(BuyContractUserInfo.class, id);
		entityManager.remove(info);
	}

	private BuyContractUserInfo converseBuyContractUserInfoVoToDo(Serializable info) {
		BuyContractUserInfo userInfo = new BuyContractUserInfo();
		if (info instanceof BuyContractUserInfoUpdateVo) {
			userInfo.setId(((BuyContractUserInfoUpdateVo) info).getBuyContractUserInfoId());
			userInfo.setTradeQuantity(((BuyContractUserInfoUpdateVo) info).getQuantity());
			PowerCustomer customer = customerDAO.getEntity(((BuyContractUserInfoUpdateVo) info).getPowerUserId());
			if (customer == null) {
				throw new EemException("不合法电力用户id");
			}
			userInfo.setCustomers(customer);
		} else {
			throw new EemException("不合法购买合同用户信息实体， 请联系管理员");
		}
		return userInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public BuyElectricityContract getBuyContractById(Long id) {
		EntityManager entityManager = buyContractDAO.getEntityManager();
		BuyElectricityContract contract = entityManager.find(BuyElectricityContract.class, id);
		return contract;
	}

	@Override
	public List<BuyContractUserInfoUpdateVo> getBuyContractUserInfoByContractId(Long id) {
		BuyElectricityContract contract = null;
		try {
			contract = getBuyContractById(id);
		} catch (NoResultException e) {
			throw new EemException("不合法id");
		}
		List<BuyContractUserInfoUpdateVo> infos = new ArrayList<>();

		if (contract.getContractUserInfos() != null && !contract.getContractUserInfos().isEmpty()) {
			for (BuyContractUserInfo object : contract.getContractUserInfos()) {
				BuyContractUserInfoUpdateVo info = new BuyContractUserInfoUpdateVo();
				info.setBuyContractUserInfoId(object.getId());
				if (object.getCustomers() != null) {
					info.setPowerUserId(object.getCustomers().getId());
				}
				info.setQuantity(object.getTradeQuantity());
				infos.add(info);
			}
		}
		return infos;
	}
	
	public static void main(String args[]) {
		
		Date now = new Date();
		System.out.println(now);
	}
}
