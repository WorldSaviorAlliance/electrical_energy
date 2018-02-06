package com.warrior.eem.service;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.warrior.eem.entity.vo.SellPowerAgreementMonthDataUpateVo;
import com.warrior.eem.entity.vo.SellPowerAgreementMonthDataVo;
import com.warrior.eem.entity.vo.SellPowerAgreementUpdateVo;
import com.warrior.eem.entity.vo.SellPowerAgreementVo;

/**
 * 售电合约服务接口
 * 
 * @author seangan
 *
 */
public interface SellPowerAgreementService extends IService {

	/**
	 * 创建合约并保存附件
	 * 
	 * @param file
	 * @param e
	 * @param monthdata
	 */
	void saveAndCreateAgreement(MultipartFile file, SellPowerAgreementVo e,
			SellPowerAgreementMonthDataVo sellPowerAgreementMonthVo);

	/**
	 * 修改合约
	 * 
	 * @param file
	 * @param e
	 * @param monthdata
	 */
	void saveAndUpdateAgreement(MultipartFile file, SellPowerAgreementUpdateVo e,
			SellPowerAgreementMonthDataUpateVo sellPowerAgreementMonthUpdateVo);
	
	/**
	 * 移除合约信息
	 * @param id
	 */
	void deleteAgreement(Serializable id);
}
