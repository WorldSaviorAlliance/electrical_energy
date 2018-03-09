package com.warrior.eem.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.warrior.eem.entity.vo.RoleVo;
import com.warrior.eem.util.ToolUtil;

/**
 * 角色,不同角色对应着不同的权限
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "role")
public class Role implements Serializable {
	private static final long serialVersionUID = 6762560763197624544L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleAuthority> authorities = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Authority> getAuthorities() {
		List<Authority> list = new ArrayList<>();
		for (RoleAuthority elem : authorities) {
			list.add(elem.getAuthority());
		}
		return list;
	}

	public void addAuthority(Authority authority) {
		RoleAuthority ra = new RoleAuthority(this, authority);
		authorities.add(ra);
		authority.getOwners().add(ra);
	}

	public void removeAuthority(Authority authority) {
		RoleAuthority ra = null;
		for (int i = authorities.size() - 1; i >= 0; --i) {
			ra = authorities.get(i);
			if (ra.getAuthority().equals(authority)) {
				authorities.remove(i);
				authority.getOwners().remove(ra);
				break;
			}
		}
	}
	
	public boolean isValid() {
		return !ToolUtil.isStringEmpty(name);
	}
	
	public RoleVo convert() {
		RoleVo vo = new RoleVo();
		vo.setId(getId());
		vo.setName(name);
		return vo;
	}

	public RoleVo convert2Desc() {
		RoleVo vo = new RoleVo();
		vo.setId(getId());
		vo.setName(name);
		vo.setAuthorities(authorities);
		return vo;
	}
	
	public boolean containsAuthority(Long authorityId) {
		for (RoleAuthority elem : authorities) {
			if (elem.getAuthority().getId().longValue() == authorityId.longValue()) {
				return true;
			}
		}
		return false;
	}
}
