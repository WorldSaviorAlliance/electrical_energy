package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 角色的搜索条件列表
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class RoleCdtVo implements Serializable {
	private static final long serialVersionUID = -2729073531778597684L;

	@FieldChecker(name = "角色名称", maxLen = 20)
	private String name;

	@FieldChecker(name = "起始页", minVal = 1)
	private int startPage;

	@FieldChecker(name = "每页展示的用户数", minVal = 1)
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
}
