package com.warrior.eem.util;

import java.util.List;

import com.warrior.eem.entity.Authority;
import com.warrior.eem.shiro.session.EemSession;

public class HeaderUtil {
	public static String getSystemUserHeaderHtml()
	{
		String el = "<li class=\"dropdown\" style=\"position: relative;\">"+
						"<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+
							"<i class=\"fa fa-stack-exchange fa-lg\" style=\"margin-right: 5px;\"></i>"+
							"<span class=\"toptitle\">电量</span>"+
						"</a>"+
						"<ul class=\"dropdown-menu extended inbox eem-dropdown-menu\" style=\"min-width: 150px;margin-left: 3px;height: auto;overflow: auto;\">"+
							"<li class=\"eem-dropdown-menu\"><a href=\"yjsdl\"><i class=\"fa fa-road\"></i><span style=\"margin-left:5px;\">月结算电量</span></a></li>"+
							"<li class=\"eem-dropdown-menu\"><a href=\"dlydqs\"><i class=\"fa fa-shield\"></i><span style=\"margin-left:5px;\">电量月度清算</span></a></li>"+
							"<li class=\"eem-dropdown-menu\"><a href=\"yddlpc\"><i class=\"fa fa-ship\"></i><span style=\"margin-left:5px;\">月度电量偏差</span></a></li>"+
							"<li class=\"eem-dropdown-menu\"><a href=\"nddlpc\"><i class=\"fa fa-tasks\"></i><span style=\"margin-left:5px;\">年度电量偏差</span></a></li>"+
						"</ul>"+
					"</li>";
		String header = "";
		
		List<Authority> authors = EemSession.getCurrentUserPermissions();
		if(authors != null)
		{
			String dysStr = "";
			String dlyhStr = "";
			String sdhyStr = "";
			String gdhyStr = "";
			String dltzStr = "";
			for(Authority author : authors)
			{
				String desc = author.getRes() + ":" + author.getOp().toString();
				if(desc.equals("PowerData:complete_control"))
				{
					header += el;
				}
				else if(desc.equals("PowerSupplier:complete_control"))
				{
					dysStr = "<li class=\"eem-dropdown-menu\"><a href=\"dys\"><i class=\"fa fa-user-circle\"></i><span style=\"margin-left:5px;\">电源商</span></a></li>"; 
				}
				else if(desc.equals("PowerCustomer:complete_control"))
				{
					dlyhStr = "<li class=\"eem-dropdown-menu\"><a href=\"dlyh\"><i class=\"fa fa-user-o\"></i><span style=\"margin-left:5px;\">电力用户</span></a></li>";
				}
				else if(desc.equals("SellPowerAgreement:complete_control"))
				{
					sdhyStr = "<li class=\"eem-dropdown-menu\"><a href=\"sdhy\"><i class=\"fa fa-id-card\"></i><span style=\"margin-left:5px;\">售电合约</span></a></li>";
				}
				else if(desc.equals("BuyElectricityContract:complete_control"))
				{
					gdhyStr = "<li class=\"eem-dropdown-menu\"><a href=\"gdhy\"><i class=\"fa fa-id-card-o\"></i><span style=\"margin-left:5px;\">购电合约</span></a></li>";
				}
				else if(desc.equals("ElectricityAdjustmentData:complete_control"))
				{
					dltzStr = "<li class=\"eem-dropdown-menu\"><a href=\"dltz\"><i class=\"fa fa-address-card-o\"></i><span style=\"margin-left:5px;\">电量调整</span></a></li>";
				}
			}
			
			if(!dysStr.equals("") || !dlyhStr.equals(""))
			{
				header += "<li class=\"dropdown\" style=\"position: relative;\">"+
								"<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+
									"<i class=\"fa fa-users fa-lg\" style=\"margin-right: 5px;\"></i>"+
									"<span class=\"toptitle\">客户</span>"+
								"</a>"+
								"<ul class=\"dropdown-menu extended inbox\" style=\"min-width: 150px;margin-left: 3px;height: auto;overflow: auto;\">"+
									dysStr +
									dlyhStr +
								"</ul>"+
							"</li>";
			}
			
			if(!dysStr.equals("") || !dlyhStr.equals(""))
			{
				header += "<li class=\"dropdown\" style=\"position: relative;\">"+
								"<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+
									"<i class=\"fa fa-money fa-lg\" style=\"margin-right: 5px;\"></i>"+
									"<span class=\"toptitle\">合约</span>"+
								"</a>"+
								"<ul class=\"dropdown-menu extended inbox eem-dropdown-menu\" style=\"min-width: 150px;margin-left: 3px;height: auto;overflow: auto;\">"+
									sdhyStr+
									gdhyStr+
									dltzStr+
								"</ul>"+
						"</li>";
			}
		}
		
		return header;
	}
}
