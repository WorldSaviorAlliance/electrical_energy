package com.warrior.eem.entity.constant;

import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.entity.Authority;

/**
 * 获取默认的权限列表
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class DefaultAuthority {
	private static List<Authority> defAuthorities = new ArrayList<>();

	/**
	 * 获取默认的权限列表
	 * 
	 * @return
	 */
	public static List<Authority> getDefaultAuthorities() {
		if (defAuthorities.isEmpty()) {// 后期考虑配置文件的形式
			defAuthorities.add(new Authority("客户", "读权限"));
			defAuthorities.add(new Authority("客户", "写权限"));
			defAuthorities.add(new Authority("客户", "完全控制"));
			defAuthorities.add(new Authority("合约", "读权限"));
			defAuthorities.add(new Authority("合约", "写权限"));
			defAuthorities.add(new Authority("合约", "完全控制"));
			defAuthorities.add(new Authority("电量", "读权限"));
			defAuthorities.add(new Authority("电量", "写权限"));
			defAuthorities.add(new Authority("电量", "完全控制"));
			defAuthorities.add(new Authority("系统管理", "读权限"));
			defAuthorities.add(new Authority("系统管理", "写权限"));
			defAuthorities.add(new Authority("系统管理", "完全控制"));
			defAuthorities.add(new Authority("能效管理", "读权限"));
			defAuthorities.add(new Authority("能效管理", "写权限"));
			defAuthorities.add(new Authority("能效管理", "完全控制"));
		}
		return defAuthorities;
	}

	private DefaultAuthority() {
	}
}
