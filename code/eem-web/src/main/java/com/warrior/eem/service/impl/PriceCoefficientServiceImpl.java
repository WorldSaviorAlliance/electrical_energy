package com.warrior.eem.service.impl;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warrior.eem.dao.IDao;
import com.warrior.eem.dao.PriceCoefficientDao;
import com.warrior.eem.dao.support.SqlRequest;
import com.warrior.eem.entity.PriceCoefficient;
import com.warrior.eem.entity.vo.PriceCoefficientVo;
import com.warrior.eem.service.PriceCoefficientService;

/**
 * 电价系数的服务实现
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@Service
public final class PriceCoefficientServiceImpl extends AbstractServiceImpl<PriceCoefficient> implements
		PriceCoefficientService {

	@Autowired
	private PriceCoefficientDao dao;

	@Override
	IDao<PriceCoefficient> getDao() {
		return dao;
	}

	@Override
	SqlRequest buildListSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	SqlRequest buildCountSqlRequest(Serializable... conditions) {
		return null;
	}

	@Override
	PriceCoefficient convertVoToDoForUpdate(Serializable dbo, Serializable vo) {
		PriceCoefficientVo coefficientVo = (PriceCoefficientVo) vo;
		PriceCoefficient coefficient = (PriceCoefficient) dbo;
		coefficient.setFlat(coefficientVo.getFlat());
		coefficient.setPeak(coefficientVo.getPeak());
		coefficient.setTrough(coefficientVo.getTrough());
		return coefficient;
	}

	@Override
	PriceCoefficient convertVoToDoForCreate(Serializable vo) {
		return convertVoToDoForUpdate(new PriceCoefficient(), vo);
	}

	@Override
	@Transactional
	public boolean initDefaultDataIfAbsent() {
		if (dao.countDos(null) > 0) {
			return true;
		}
		PriceCoefficient pc = new PriceCoefficient();
		pc.setFlat(1);
		pc.setPeak(1);
		pc.setTrough(1);
		dao.createDo(pc);
		return dao.countDos(null) > 0;
	}
}
