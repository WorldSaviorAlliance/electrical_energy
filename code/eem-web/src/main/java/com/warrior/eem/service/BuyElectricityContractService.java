package com.warrior.eem.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.warrior.eem.entity.BuyElectricityContract;
import com.warrior.eem.entity.vo.BuyContractUserInfoUpdateVo;
import com.warrior.eem.entity.vo.BuyElectricityContractUpdateVo;
import com.warrior.eem.entity.vo.BuyElectricityContractVo;

public interface BuyElectricityContractService extends IService {

	void saveOrUpdateBuyContractAndAttachment(BuyElectricityContractUpdateVo buyContract,
			Set<BuyContractUserInfoUpdateVo> infos, MultipartFile file, List<Long> ids);

	BuyElectricityContract getBuyContractById(Long id);

	BuyElectricityContractVo getBuyContractUserInfoByContractId(Long id);
	
	/**
	 * 下载合约
	 * @param out
	 * @param fileName
	 */
	void downloadAgreement(OutputStream out, String fileName);
	
	/**
	 * 检测附件是否存在
	 * @param fileName
	 * @return
	 */
	boolean isExists(String fileName);
}
