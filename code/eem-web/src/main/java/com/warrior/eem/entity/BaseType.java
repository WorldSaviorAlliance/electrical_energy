package com.warrior.eem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.warrior.eem.entity.vo.BaseTypeVo;
import com.warrior.eem.util.ToolUtil;

/**
 * 类型实体的基类
 * 
 * @author cold_blade
 * @version 1.0.0
 */
@MappedSuperclass
public class BaseType extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@Column(name = "create_date")
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User creator;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public BaseTypeVo convert() {
		BaseTypeVo vo = new BaseTypeVo();
		vo.setId(getId());
		vo.setName(name);
		vo.setCreator(creator.getName());
		vo.setDate(ToolUtil.formatDate(createDate));
		return vo;
	}
}