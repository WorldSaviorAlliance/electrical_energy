package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.constant.PowerConsts;

/**
 * 电价系数搜索查询条件模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class PriceCoefficientCdtVo implements Serializable {
	private static final long serialVersionUID = -4014309019785703512L;

	@FieldChecker(name = "系数名", maxLen = 20)
	private String name;

	private int startCoefficient = PowerConsts.INVALID_PRICE_COEFFICIENT;

	private int endCoefficient = PowerConsts.INVALID_PRICE_COEFFICIENT;

	@FieldChecker(name = "起始页", minVal = 1)
	private int startPage;

	@FieldChecker(name = "每页展示的数量", minVal = 1)
	private int perPageCnt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartCoefficient() {
		return startCoefficient;
	}

	public void setStartCoefficient(int startCoefficient) {
		this.startCoefficient = startCoefficient;
	}

	public int getEndCoefficient() {
		return endCoefficient;
	}

	public void setEndCoefficient(int endCoefficient) {
		this.endCoefficient = endCoefficient;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPerPageCnt() {
		return perPageCnt;
	}

	public void setPerPageCnt(int perPageCnt) {
		this.perPageCnt = perPageCnt;
	}

	public boolean isCoefficientValid() {
		return startCoefficient <= endCoefficient;
	}
}
