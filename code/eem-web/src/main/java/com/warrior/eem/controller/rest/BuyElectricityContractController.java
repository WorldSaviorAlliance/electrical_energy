package com.warrior.eem.controller.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warrior.eem.common.Result;
import com.warrior.eem.entity.vo.BuyContractSearchVo;
import com.warrior.eem.entity.vo.BuyContractUserInfoUpdateVo;
import com.warrior.eem.entity.vo.BuyElectricityContractUpdateVo;
import com.warrior.eem.entity.vo.PageVo;
import com.warrior.eem.service.BuyElectricityContractService;

/**
 * 购电合约
 * @author seangan
 *
 */
@Controller
@RequestMapping(value = "/buy_agreement")
public class BuyElectricityContractController extends AbstractController {

	@Autowired
	private BuyElectricityContractService buyContractService;

	@ResponseBody
	@RequestMapping(value = "info", method = RequestMethod.POST)
	public Result<Object> createOrUpdateBuyContract(BuyElectricityContractUpdateVo buyContract,
			@RequestParam(name = "infos") String infos, @RequestParam(name = "file") MultipartFile file,
			@RequestParam(name = "ids", required = false) String deleteIds) throws Exception {
		ObjectMapper om = new ObjectMapper();
		JavaType javaType = om.getTypeFactory().constructParametrizedType(Set.class, HashSet.class,
				BuyContractUserInfoUpdateVo.class);
		Set<BuyContractUserInfoUpdateVo> contractUserInfos = om.readValue(infos, javaType);
		List<Long> ids = new ArrayList<>();
		if (deleteIds != null && deleteIds.trim().length() > 0) {
			javaType = om.getTypeFactory().constructParametrizedType(List.class, ArrayList.class, Long.class);
			ids = om.readValue(deleteIds, javaType);
		}
		buyContractService.saveOrUpdateBuyContractAndAttachment(buyContract, contractUserInfos, file, ids);
		return Result.success();
	}

	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public Result<Object> listBuyContracts(@RequestBody(required = false) BuyContractSearchVo searchCondition,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "per_page", required = false) String pageSize) {
		Integer[] pageInfo = buildPageInfo(page, pageSize);
		PageVo pageVO = buyContractService.listEntities(searchCondition, pageInfo[0], pageInfo[1]);
		return Result.success(pageVO.getCount(), pageVO.getDatas());
	}

	@ResponseBody
	@RequestMapping(value = "info", method = RequestMethod.DELETE)
	public Result<Object> deleteBuyContract(@RequestParam(name = "id") Long id) {
		buyContractService.deleteEntity(id);
		return Result.success();
	}

	@ResponseBody
	@RequestMapping(value = "info", method = RequestMethod.GET)
	public Result<List<BuyContractUserInfoUpdateVo>> getBuyContractUserInfoByContractId(
			@RequestParam(name = "id") Long id) {
		return Result.success(buyContractService.getBuyContractUserInfoByContractId(id));
	}
}
