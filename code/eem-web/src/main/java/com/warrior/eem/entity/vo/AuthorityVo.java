package com.warrior.eem.entity.vo;

import java.io.Serializable;

/**
 * 权限的界面数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public class AuthorityVo implements Serializable {
	private static final long serialVersionUID = 7550813601081881070L;

	private Long id;
	private String desc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
