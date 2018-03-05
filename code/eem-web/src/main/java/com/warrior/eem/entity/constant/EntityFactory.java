package com.warrior.eem.entity.constant;

import java.util.ArrayList;
import java.util.List;

import com.warrior.eem.entity.Authority;
import com.warrior.eem.entity.BuyContractUserInfo;
import com.warrior.eem.entity.BuyElectricityContract;
import com.warrior.eem.entity.ElectricityAdjustmentData;
import com.warrior.eem.entity.ElectricityPackage;
import com.warrior.eem.entity.OperationLog;
import com.warrior.eem.entity.PowerCustomer;
import com.warrior.eem.entity.PowerSupplier;
import com.warrior.eem.entity.PriceCoefficient;
import com.warrior.eem.entity.Role;
import com.warrior.eem.entity.SellPowerAgreement;
import com.warrior.eem.entity.SellPowerAgreementMonthData;
import com.warrior.eem.entity.User;
import com.warrior.eem.entity.VoltageType;

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

	/**
	 * 获取默认的权限列表
	 * 
	 * @return
	 */
	public static List<Authority> getDefaultAuthorities() {
		List<Authority> authorities = new ArrayList<>();
		List<String> entities = getResources();
		for (String elem : entities) {
			authorities.add(new Authority(elem, ResourceOperation.READ));
			authorities.add(new Authority(elem, ResourceOperation.WRITE));
			authorities.add(new Authority(elem, ResourceOperation.COM_CONTROL));
		}
		return authorities;
	}

	private static List<String> getResources() {
		List<String> entities = new ArrayList<>();
		entities.add(BuyContractUserInfo.class.getSimpleName());
		entities.add(BuyElectricityContract.class.getSimpleName());
		entities.add(ElectricityAdjustmentData.class.getSimpleName());
		entities.add(ElectricityPackage.class.getSimpleName());
		entities.add(OperationLog.class.getSimpleName());
		entities.add(PowerCustomer.class.getSimpleName());
		entities.add(PowerSupplier.class.getSimpleName());
		entities.add(PriceCoefficient.class.getSimpleName());
		entities.add(Role.class.getSimpleName());
		entities.add(SellPowerAgreement.class.getSimpleName());
		entities.add(SellPowerAgreementMonthData.class.getSimpleName());
		entities.add(User.class.getSimpleName());
		entities.add(VoltageType.class.getSimpleName());
		return entities;
	}
}
