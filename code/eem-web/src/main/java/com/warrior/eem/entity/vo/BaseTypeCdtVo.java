package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.dao.support.Page;

/**
 * 交易类型或电压类型的查询搜索条件数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class BaseTypeCdtVo implements Serializable {
	private static final long serialVersionUID = -8212251091308735533L;

	@FieldChecker(name = "名称", maxLen = 20)
	private String name;

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
	
	public Page getPage() {
		return new Page(startPage, perPageCnt);
	}
}
