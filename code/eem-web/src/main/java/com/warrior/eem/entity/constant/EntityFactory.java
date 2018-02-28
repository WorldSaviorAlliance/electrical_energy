package com.warrior.eem.entity.constant;

import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.entity.ElectricityPackage;

/**
 * 获取实体的默认值
 * 
 * @author cold_blade
 * @version 1.0.0
 */
public final class EntityFactory {

	private EntityFactory() {
	}

	/**
	 * 获取默认的套餐列表
	 * 
	 * @return
	 */
	public static List<ElectricityPackage> getDefaultElectricityPackages() {
		List<ElectricityPackage> list = new ArrayList<ElectricityPackage>();
		ElectricityPackage elecPkg = new ElectricityPackage();
		elecPkg.setName("套餐1");
		elecPkg.setType(ElectricityPackageType.FIXED_RETURN);
		elecPkg.setExtPrice(520);
		elecPkg.setMinElecticity(4000);
		elecPkg.setDesc("客户电价按现行目录电价基础上下降52厘/千瓦时（含税）作为固定交易电价的固定降价价差，" + "进行各月份合约电量的电力交易。此价差与现行目录销售电价联动，"
				+ "不收目录电价调整与市场集中竞价交易价差影响。");
		list.add(elecPkg);

		elecPkg = new ElectricityPackage();
		elecPkg.setName("套餐2");
		elecPkg.setType(ElectricityPackageType.MARKET_LINKAGE);
		elecPkg.setExtPrice(480);
		elecPkg.setMinElecticity(3000);
		elecPkg.setDesc("客户全部电量分解为固定电量和市场联动电量，" + "市场联动部分参与集中竞价，以市场统一出请假为准，" + "客户获得80%的竞价价差。");
		list.add(elecPkg);

		elecPkg = new ElectricityPackage();
		elecPkg.setName("套餐3");
		elecPkg.setType(ElectricityPackageType.DEVIATION_CHECK);
		elecPkg.setExtPrice(420);
		elecPkg.setMinElecticity(2000);
		elecPkg.setDesc("客户全部电量交由售电公司代理，按固定回报或市场联动套餐办理，" + "由客户和售电公司共同承担偏差考核风险，月偏差率10%以内时由售电公司承担，"
				+ "10%~20%是各承担50%，大于20%时由客户承担70%。");
		list.add(elecPkg);
		return list;
	}
}
