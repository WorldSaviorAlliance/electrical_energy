package com.warrior.eem.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.warrior.eem.entity.constant.ResourceOperation;
import com.warrior.eem.entity.constant.UserStatus;
import com.warrior.eem.entity.constant.UserType;
import com.warrior.eem.entity.vo.UserVo;
import com.warrior.eem.exception.EemException;
import com.warrior.eem.util.ToolUtil;

/**
 * 用户实体
 * 
 * @author cold_blade
 * @version 1.0.0
 */

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 7127750241730901860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "user_type")
	@Enumerated(EnumType.ORDINAL)
	private UserType type;

	@Column(name = "user_status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private PowerCustomer customer;

	@Column(name = "add_time")
	private Timestamp addTime;

	@Column(name = "last_login_time")
	private Timestamp lastLoginTime;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserElectricityPackage> pkgs = new ArrayList<UserElectricityPackage>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public PowerCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(PowerCustomer customer) {
		this.customer = customer;
	}

	public List<ElectricityPackage> getElectricityPackages() {
		List<ElectricityPackage> list = new ArrayList<>();
		for (UserElectricityPackage elem : pkgs) {
			list.add(elem.getPkg());
		}
		return list;
	}

	public void handleElectricityPackage(ElectricityPackage pkg) {
		UserElectricityPackage uep = new UserElectricityPackage();
		uep.setPkg(pkg);
		uep.setUser(this);
		pkgs.add(uep);
		pkg.getOwners().add(uep);
	}

	public boolean cancelElectricityPackage(long pkgId) {
		UserElectricityPackage uep = null;
		for (int i = pkgs.size() - 1; i >= 0; --i) {
			uep = pkgs.get(i);
			if (uep.getPkg().getId() == pkgId) {
				uep.getPkg().getOwners().remove(uep);
				pkgs.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean containsElectricityPackage(long pkgId) {
		for (UserElectricityPackage elem : pkgs) {
			if (elem.getPkg().getId() == pkgId) {
				return true;
			}
		}
		return false;
	}

	public boolean containsCustomer(long customerId) {
		if (null == customer) {
			return false;
		}
		return customer.getId() == customerId;
	}

	public UserVo convert() {
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setName(name);
		vo.setType(type.ordinal());
		if (UserType.ELECTRICITY == type) {
			vo.setCustomerId(customer.getId());
			vo.setCustomerName(customer.getName());
		} else {
			vo.setCustomerName("");
		}
		vo.setCreateTime(ToolUtil.formatDate(addTime));
		return vo;
	}

	public void checkPermission(String res, ResourceOperation op) {
		boolean hasPermission = false;
		for (Authority authority : role.getAuthorities()) {
			if (authority.hasPermission(res, op)) {
				hasPermission = true;
			}
		}
		if (!hasPermission) {
			throw new EemException("no " + res + ":" + op.toString() + "permission");
		}
	}

	public void checkPermission(String res, ResourceOperation op, Long resId) {
		// TODO:更小的权限设计,以后版本扩展
		checkPermission(res, op);
	}
}
