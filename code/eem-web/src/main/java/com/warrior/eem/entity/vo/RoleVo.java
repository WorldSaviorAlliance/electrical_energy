package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.annotation.FieldChecker;

/**
 * 角色的界面数据模型
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class RoleVo implements Serializable {
	private static final long serialVersionUID = -6867645151131916836L;

	private long id;

	@FieldChecker(name = "权限名称", minLen = 1, maxLen = 20)
	private String name;

	private List<Long> authorities;

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

	public List<Long> getAuthorities() {
		return authorities;
	}

	public void addAuthority(long authorityId) {
		if (null == authorities) {
			authorities = new ArrayList<>();
		}
		authorities.add(authorityId);
	}
}
