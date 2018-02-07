package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 针对前端list请求数据返回对象
 * @author seangan
 *
 */
public class PageVo implements Serializable {
	
	private static final long serialVersionUID = -3892361604130438393L;

	/**
	 * 总个数
	 */
	private Long count;
	
	/**
	 * 数据列表（分页过后的每页数据列表）
	 */
	private List<?> datas;
	
	public PageVo() {
		
	}
	
	public PageVo(Long count, List<?> datas) {
		this.count = count;
		this.datas = datas;
	}

	public Long getCount() {
		return count;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
}
