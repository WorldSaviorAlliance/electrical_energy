package com.warrior.eem.entity.ui;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class MenuItem extends BaseModel {
	@Expose
	private String name; // 名称
	@Expose
	private String icon; // 图标
	@Expose
	private int isSel; // 是否选择
	@Expose
	private String url; // 指定访问的地址
	@Expose
	@XStreamImplicit
	private List<MenuItem> children; // 子菜单

	public MenuItem() {

	}

	public MenuItem(String name)
	{
		this.name = name;
	}
	
	public MenuItem(String name, int isSel, String url)
	{
		this.name = name;
		this.isSel = isSel;
		this.url = url;
	}
	
	public MenuItem(String name, String icon, int isSel, String url) {
		this.name = name;
		this.icon = icon;
		this.isSel = isSel;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getIsSel() {
		return isSel;
	}

	public void setIsSel(int isSel) {
		this.isSel = isSel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}
}
