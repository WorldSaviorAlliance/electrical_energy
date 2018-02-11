package com.warrior.eem.service;

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
}
