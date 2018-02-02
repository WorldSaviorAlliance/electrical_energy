package com.warrior.eem.entity.ui;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Direct")
public class Direct extends BaseModel {

	@Expose
	private String name = "";
	@Expose
	private String activeItem = "";
	@Expose
	private String hostGuid = "";
	@Expose
	private String hostName = "";
	@Expose
	private Object param;

	public Direct(String name) {
		this.name = name;
		this.activeItem = name;
	}

	public Direct(String name, String activeItem) {
		this.name = name;
		this.activeItem = activeItem;
	}

	public Direct(String name, String activeItem, Object param) {
		this.name = name;
		this.activeItem = activeItem;
		this.param = param;
	}

	public Direct(String name, String activeItem, String hostGuid, String hostName) {
		this.name = name;
		this.activeItem = activeItem;
		this.hostGuid = hostGuid;
		this.hostName = hostName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveItem() {
		return activeItem;
	}

	public void setActiveItem(String activeItem) {
		this.activeItem = activeItem;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}
}
