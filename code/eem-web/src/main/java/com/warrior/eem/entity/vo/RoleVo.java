package com.warrior.eem.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.annotation.FieldChecker;
import com.warrior.eem.entity.Authority;
import com.warrior.eem.entity.RoleAuthority;

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

	private List<Long> authorityIds;
	
	private List<String> authorityDesc;

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

	public List<Long> getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(List<Long> authorityIds) {
		this.authorityIds = authorityIds;
	}

	public List<String> getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(List<String> authorityDesc) {
		this.authorityDesc = authorityDesc;
	}
	
	public void setAuthorities(List<RoleAuthority> authorities) {
		if (null == authorities || authorities.isEmpty()) {
			return;
		}
		int size = authorities.size();
		authorityIds = new ArrayList<>(size);
		authorityDesc = new ArrayList<>(size);
		Authority authority;
		for (RoleAuthority elem : authorities) {
			authority = elem.getAuthority();
			authorityIds.add(authority.getId());
			authorityDesc.add(authority.getRes() + ":" + authority.getOp().toString());
		}
	}
}
