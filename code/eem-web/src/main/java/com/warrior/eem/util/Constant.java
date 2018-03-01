package com.warrior.eem.util;

import java.util.Date;

public class Constant {
	public static long getCurTime()
	{
		return new Date().getTime();
	}
	
	//目前对应的界面的名称
	public static String DIR_HOME = "home";
	public static String ICON_MAIN = "fa-home";
	public static String NAME_MAIN = "首页";
	public static String DIR_MAIN = "home";
	
	public static String CUSTOMER = "customer/";
	public static String NAME_DYS = "电源商列表";
	public static String DIR_DYS = "dys";
	
	public static String NAME_DLYH = "电力用户列表";
	public static String DIR_DLYH = "dlyh";
	
	public static String CONTRACT = "contract/";
	public static String NAME_GDHY = "购电合约列表";
	public static String DIR_GDHY = "gdhy";
	
	public static String NAME_SDHY = "售电合约列表";
	public static String DIR_SDHY = "sdhy";
	
	public static String NAME_DLTZ = "电量调整列表";
	public static String DIR_DLTZ = "dltz";
	
	public static String ELECTRICITY = "electricity/";
	public static String NAME_YJSDL = "月结算电量列表";
	public static String DIR_YJSDL = "yjsdl";
	
	public static String NAME_DLYDQS = "电量月度清算";
	public static String DIR_DLYDQS = "dlydqs";
	
	public static String NAME_YDDLPC = "月度电量偏差";
	public static String DIR_YDDLPC = "yddlpc";
	
	public static String NAME_NDDLPC = "年度电量偏差";
	public static String DIR_NDDLPC = "nddlpc";
	
	public static String SETTING = "setting/";
	
	public static String NAME_DYDJ = "电压等级";
	public static String DIR_DYDJ = "dydj";
	
	public static String NAME_DJXS = "电价系数";
	public static String DIR_DJXS = "djxs";
	
	public static String NAME_YHGL = "用户管理";
	public static String DIR_YHGL = "yhgl";
	
	public static String NAME_JSGL = "角色管理";
	public static String DIR_JSGL = "jsgl";
	
	public static String USER = "user/";
	public static String NAME_NX = "能效";
	public static String DIR_NX = "nx";
	
	public static String NAME_WYBL = "我要办理";
	public static String DIR_WYBL = "wybl";
	
	public static String NAME_JBXX = "基本信息";
	public static String DIR_JBXX = "jbxx";
	
	public static final String DIRECT = "direct";
	public static final String STR_ID = "id";
}
