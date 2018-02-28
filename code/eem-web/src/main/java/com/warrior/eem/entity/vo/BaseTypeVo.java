package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 交易类型或电压类型的界面数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class BaseTypeVo implements Serializable {
	private static final long serialVersionUID = 7894342124333342669L;

	private long id;

	@FieldChecker(name = "名称", minLen = 1, maxLen = 20)
	private String name;

	private Date date;// 创建和修改的时候不传

	private String creator;// 创建和修改的时候不传

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
