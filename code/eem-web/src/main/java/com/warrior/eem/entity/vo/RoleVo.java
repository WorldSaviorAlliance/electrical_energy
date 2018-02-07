package com.warrior.eem.entity.vo;

import java.io.Serializable;

import com.warrior.eem.annotation.FieldChecker;

public class RoleVo implements Serializable {
	private static final long serialVersionUID = -6867645151131916836L;

	private long id;
	
	@FieldChecker(name = "权限名称", minLen = 1)
	private String name;
}
