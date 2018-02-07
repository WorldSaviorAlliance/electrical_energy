package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 电量列表搜索条件
 * @author seangan
 *
 */
public class PowerDataCdtVo implements Serializable {
	
	private static final long serialVersionUID = 621349478829993951L;
	
	/**
	 * 名称
	 */
	@FieldChecker(name = "电力用户名称", minLen = 0, maxLen = 30)
	private String name;
	
	/**
	 * 开始月份
	 */
	@FieldChecker(name = "开始月份", minLen = 0, maxLen = 6)
	private String beginMonth;
	 
	/**
	 * 结束月份
	 */
	@FieldChecker(name = "结束月份", minLen = 0, maxLen = 6)
	private String endMonth;
	
	public PowerDataCdtVo() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginMonth() {
		return beginMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setBeginMonth(String beginMonth) {
		this.beginMonth = beginMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

}
