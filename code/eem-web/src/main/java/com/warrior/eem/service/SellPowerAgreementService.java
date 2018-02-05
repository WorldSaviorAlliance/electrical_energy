package com.warrior.eem.service;

import org.springframework.web.multipart.MultipartFile;

import com.warrior.eem.entity.vo.SellPowerAgreementUpdateVo;
import com.warrior.eem.entity.vo.SellPowerAgreementVo;

/**
 * 售电合约服务接口
 * @author seangan
 *
 */
public interface SellPowerAgreementService extends IService {
	
	/**
	 * 创建合约并保存附件
	 * @param file
	 * @param e
	 */
	void saveAndCreateAgreement(MultipartFile file, SellPowerAgreementVo e);
	
	
	/**
	 * 修改合约
	 * @param file
	 * @param e
	 */
	void saveAndUpdateAgreement(MultipartFile file, SellPowerAgreementUpdateVo e);
}
