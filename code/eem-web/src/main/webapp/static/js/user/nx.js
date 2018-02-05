$(function(){
	var piedata = new Array();
	// 今日
	piedata["today_color"] = null;
	piedata["today_powerdata"] = null;
	piedata["today_powertotal"] = "--";
	piedata["today_moneydata"] = null;
	piedata["today_moneytotal"] = "--";
	// 昨日
	piedata["yesterday_color"] = null;
	piedata["yesterday_powerdata"] = null;
	piedata["yesterday_powertotal"] = "--";
	piedata["yesterday_moneydata"] = null;
	piedata["yesterday_moneytotal"] = "--";
	// 上月
	piedata["lastmonth_color"] = null;
	piedata["lastmonth_powerdata"] = null;
	piedata["lastmonth_powertotal"] = "--";
	piedata["lastmonth_moneydata"] = null;
	piedata["lastmonth_moneytotal"] = "--";
	// 去年
	piedata["lastyear_color"] = null;
	piedata["lastyear_powerdata"] = null;
	piedata["lastyear_powertotal"] = "--";
	piedata["lastyear_moneydata"] = null;
	piedata["lastyear_moneytotal"] = "--";

	function initPowerPie(range){
	    $("#pie_day_1").html("").addClass("bg_load");
	    $("#pie_day_2").html("").addClass("bg_load");
	    // ajax 获取数据
	    var url = 'http://rdny-service.24kwh.com/index.php/system/eemsdata/';   
	    
	    jsondata = {"powerdata":[{"name":"\u9ad8\u5cf0","value":"1.835"},{"name":"\u5e73\u6bb5","value":"4.021"},{"name":"\u4f4e\u8c37","value":"3.724"}],"color":["#f7c31e","#36cea2","#24b7e3"],"powertotal":"9.580","moneydata":[{"name":"\u9ad8\u5cf0","value":"2.053"},{"name":"\u5e73\u6bb5","value":"2.804"},{"name":"\u4f4e\u8c37","value":"1.389"}],"moneytotal":"6.246"};
        $("#pie_day_1").html("").removeClass("bg_load");
        $("#pie_day_2").html("").removeClass("bg_load");
        if(jsondata)
        {
            piedata[range+"_color"] = jsondata.color;
            piedata[range+"_powerdata"] = jsondata.powerdata;
            piedata[range+"_powertotal"] = jsondata.powertotal;
            piedata[range+"_moneydata"] = jsondata.moneydata;
            piedata[range+"_moneytotal"] = jsondata.moneytotal;
            // 合 xxx 元/kWh
            if(jsondata.moneytotal === "--" || jsondata.powertotal === "--" || jsondata.powertotal == 0){
                $("#pie_day_2_he").html("--");
            }
            else{
                var he = jsondata.moneytotal / jsondata.powertotal ;
                $("#pie_day_2_he").html(he.toFixed(3));
            }
            setChart("pie_day_1", range, "power");
            setChart("pie_day_2", range, "money");
        }
        
        window.setTimeout('initPowerPie("today")', 5*60000);
	}

	function setChart(char_id, range, type){
	    var colordata = piedata[range+"_color"];
	    var data = piedata[range+"_"+type+"data"];
	    var total= piedata[range+"_"+type+"total"];
	    var ietype = $("#workshop_id").find("option:selected").data("type");
	    /*发电车间时*/
	    if (ietype === 'e') {

	        if (type === 'power') {// 发电量
	            data = [{name:'发电量', value:total}];
	            colordata = ['#1fb5ac'];
	        }else{// 电费
	            data = [];
	            total = '--';
	        }
	    }
	    var unit = "";
	    if(type === "power"){
	        unit = "万kWh";
	    }
	    else if(type === "money"){
	        unit = "万元";
	    }
	    
	    if(total === "--"){
	        total = "无数据";
	        unit = "";
	    }

	    // 指定图表的配置项和数据
	    var pieoption = {
	            title:{
	                text: total,
	                left: 'center',
	                top: 'middle',
	                textStyle: {
	                    color: '#0db09b',
	                    fontSize:18
	                },
	                subtext: unit,
	            },
	            tooltip: {
	                trigger: 'item',
	                formatter: "{b}: {c} "+unit
	            },
	            color: colordata,
	            legend: {
	                show: false
	            },
	            series: [
	                    {
                            name:'数据',
                            type:'pie',
                            radius: ['50%', '70%'],
                            data: data,  
                            itemStyle:{
                                normal:{
                                    label:{
                                        show:true,
                                        position:'top',
                                        textStyle:{
                                            fontSize:12,
                                            fontWeight: 'bold'
                                            //,  color:"green"
                                        },
                                        formatter:function(a){
                                            if ($.isNumeric(total) && total!=0 )
                                            {
                                                var per = a.value / total * 100;
                                            }else{
                                                var per = 100;
                                            }

                                            return a.name+"\n"+per.toFixed(1)+"%";
                                        }
                                    }
                                }
                            }
	                    }
	            ]
	    };
	        // 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById(char_id));
	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(pieoption);
	    
	}



	var showPowerColumns_timeout;
	// 查询历史数据
	function showPowerColumns(char_id)
	{ 
	    if(showPowerColumns_timeout){
	        clearTimeout(showPowerColumns_timeout);
	    }
	    $("#"+char_id).html("").addClass("bg_load");
	    var url = 'http://rdny-service.24kwh.com/index.php/system/eemsdata/';  
	    //var url = 'http://app.eemsii.hezongyun.com/index.php/electricstatistics/todaypowerdetail/'+$("#now_company_id").val()+"/"+$("#workshop_id").val();
	    
	    var xdata = [];
	    var x1 = ["0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"];
	    var x2 = ["00","15","30","45"];
	    for(var i=0;i<x1.length;i++){
	       for(var j=0;j<x2.length;j++){
	           xdata.push( x1[i]+":"+x2[j] ) ;
	        } 
	    }
	    xdata.push( "24:00") ;
	    
	    jsondata = {"max_y":6202,"y_hour":[null,{"y":4750.581,"color":"#24b7e3","in":"\u4eca\u65e5 0:00-1:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4750.581,"color":"#24b7e3","in":"\u4eca\u65e5 0:00-1:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4750.581,"color":"#24b7e3","in":"\u4eca\u65e5 0:00-1:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4804.249,"color":"#24b7e3","in":"\u4eca\u65e5 1:00-2:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4804.249,"color":"#24b7e3","in":"\u4eca\u65e5 1:00-2:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4804.249,"color":"#24b7e3","in":"\u4eca\u65e5 1:00-2:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4594.387,"color":"#24b7e3","in":"\u4eca\u65e5 2:00-3:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4594.387,"color":"#24b7e3","in":"\u4eca\u65e5 2:00-3:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4594.387,"color":"#24b7e3","in":"\u4eca\u65e5 2:00-3:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4508.528,"color":"#24b7e3","in":"\u4eca\u65e5 3:00-4:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4508.528,"color":"#24b7e3","in":"\u4eca\u65e5 3:00-4:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4508.528,"color":"#24b7e3","in":"\u4eca\u65e5 3:00-4:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4574.306,"color":"#24b7e3","in":"\u4eca\u65e5 4:00-5:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4574.306,"color":"#24b7e3","in":"\u4eca\u65e5 4:00-5:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4574.306,"color":"#24b7e3","in":"\u4eca\u65e5 4:00-5:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4603.586,"color":"#24b7e3","in":"\u4eca\u65e5 5:00-6:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4603.586,"color":"#24b7e3","in":"\u4eca\u65e5 5:00-6:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4603.586,"color":"#24b7e3","in":"\u4eca\u65e5 5:00-6:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4735.697,"color":"#24b7e3","in":"\u4eca\u65e5 6:00-7:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4735.697,"color":"#24b7e3","in":"\u4eca\u65e5 6:00-7:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4735.697,"color":"#24b7e3","in":"\u4eca\u65e5 6:00-7:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4657.746,"color":"#24b7e3","in":"\u4eca\u65e5 7:00-8:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4657.746,"color":"#24b7e3","in":"\u4eca\u65e5 7:00-8:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4657.746,"color":"#24b7e3","in":"\u4eca\u65e5 7:00-8:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4865.196,"color":"#36cea2","in":"\u4eca\u65e5 8:00-9:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4865.196,"color":"#36cea2","in":"\u4eca\u65e5 8:00-9:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4865.196,"color":"#36cea2","in":"\u4eca\u65e5 8:00-9:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4937.646,"color":"#36cea2","in":"\u4eca\u65e5 9:00-10:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4937.646,"color":"#36cea2","in":"\u4eca\u65e5 9:00-10:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4937.646,"color":"#36cea2","in":"\u4eca\u65e5 9:00-10:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":5047.55,"color":"#36cea2","in":"\u4eca\u65e5 10:00-11:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5047.55,"color":"#36cea2","in":"\u4eca\u65e5 10:00-11:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5047.55,"color":"#36cea2","in":"\u4eca\u65e5 10:00-11:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4988.7,"color":"#36cea2","in":"\u4eca\u65e5 11:00-12:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4988.7,"color":"#36cea2","in":"\u4eca\u65e5 11:00-12:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4988.7,"color":"#36cea2","in":"\u4eca\u65e5 11:00-12:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":4982.836,"color":"#36cea2","in":"\u4eca\u65e5 12:00-13:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4982.836,"color":"#36cea2","in":"\u4eca\u65e5 12:00-13:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":4982.836,"color":"#36cea2","in":"\u4eca\u65e5 12:00-13:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":5135.981,"color":"#36cea2","in":"\u4eca\u65e5 13:00-14:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5135.981,"color":"#36cea2","in":"\u4eca\u65e5 13:00-14:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5135.981,"color":"#36cea2","in":"\u4eca\u65e5 13:00-14:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":5236.479,"color":"#f7c31e","in":"\u4eca\u65e5 14:00-15:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5236.479,"color":"#f7c31e","in":"\u4eca\u65e5 14:00-15:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5236.479,"color":"#f7c31e","in":"\u4eca\u65e5 14:00-15:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":5246.461,"color":"#f7c31e","in":"\u4eca\u65e5 15:00-16:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5246.461,"color":"#f7c31e","in":"\u4eca\u65e5 15:00-16:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5246.461,"color":"#f7c31e","in":"\u4eca\u65e5 15:00-16:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":5222.616,"color":"#f7c31e","in":"\u4eca\u65e5 16:00-17:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5222.616,"color":"#f7c31e","in":"\u4eca\u65e5 16:00-17:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5222.616,"color":"#f7c31e","in":"\u4eca\u65e5 16:00-17:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":5013.842,"color":"#36cea2","in":"\u4eca\u65e5 17:00-18:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5013.842,"color":"#36cea2","in":"\u4eca\u65e5 17:00-18:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5013.842,"color":"#36cea2","in":"\u4eca\u65e5 17:00-18:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":5221.199,"color":"#36cea2","in":"\u4eca\u65e5 18:00-19:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5221.199,"color":"#36cea2","in":"\u4eca\u65e5 18:00-19:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":5221.199,"color":"#36cea2","in":"\u4eca\u65e5 18:00-19:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":2642.016,"color":"#f7c31e","in":"\u4eca\u65e5 19:00-20:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":2642.016,"color":"#f7c31e","in":"\u4eca\u65e5 19:00-20:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":2642.016,"color":"#f7c31e","in":"\u4eca\u65e5 19:00-20:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 20:00-21:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 20:00-21:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 20:00-21:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 21:00-22:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 21:00-22:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 21:00-22:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 22:00-23:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 22:00-23:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 22:00-23:00 \u7528\u7535\u91cf","unit":"kWh"},null,{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 23:00-24:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 23:00-24:00 \u7528\u7535\u91cf","unit":"kWh"},{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 23:00-24:00 \u7528\u7535\u91cf","unit":"kWh"}],"y_hour2":[null,null,{"y":4750.581,"color":"#24b7e3","in":"\u4eca\u65e5 0:00-1:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4804.249,"color":"#24b7e3","in":"\u4eca\u65e5 1:00-2:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4594.387,"color":"#24b7e3","in":"\u4eca\u65e5 2:00-3:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4508.528,"color":"#24b7e3","in":"\u4eca\u65e5 3:00-4:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4574.306,"color":"#24b7e3","in":"\u4eca\u65e5 4:00-5:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4603.586,"color":"#24b7e3","in":"\u4eca\u65e5 5:00-6:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4735.697,"color":"#24b7e3","in":"\u4eca\u65e5 6:00-7:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4657.746,"color":"#24b7e3","in":"\u4eca\u65e5 7:00-8:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4865.196,"color":"#36cea2","in":"\u4eca\u65e5 8:00-9:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4937.646,"color":"#36cea2","in":"\u4eca\u65e5 9:00-10:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":5047.55,"color":"#36cea2","in":"\u4eca\u65e5 10:00-11:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4988.7,"color":"#36cea2","in":"\u4eca\u65e5 11:00-12:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":4982.836,"color":"#36cea2","in":"\u4eca\u65e5 12:00-13:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":5135.981,"color":"#36cea2","in":"\u4eca\u65e5 13:00-14:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":5236.479,"color":"#f7c31e","in":"\u4eca\u65e5 14:00-15:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":5246.461,"color":"#f7c31e","in":"\u4eca\u65e5 15:00-16:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":5222.616,"color":"#f7c31e","in":"\u4eca\u65e5 16:00-17:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":5013.842,"color":"#36cea2","in":"\u4eca\u65e5 17:00-18:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":5221.199,"color":"#36cea2","in":"\u4eca\u65e5 18:00-19:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":2642.016,"color":"#f7c31e","in":"\u4eca\u65e5 19:00-20:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 20:00-21:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":null,"color":"#f7c31e","in":"\u4eca\u65e5 21:00-22:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 22:00-23:00 \u7528\u7535\u91cf","unit":"kWh"},null,null,null,{"y":null,"color":"#36cea2","in":"\u4eca\u65e5 23:00-24:00 \u7528\u7535\u91cf","unit":"kWh"},null],"y_minute":[null,{"y":4691.3893847222,"in":"\u4eca\u65e5 0:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4752.9534965278,"in":"\u4eca\u65e5 0:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4798.7771777778,"in":"\u4eca\u65e5 0:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4759.2036909722,"in":"\u4eca\u65e5 1:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4742.39429375,"in":"\u4eca\u65e5 1:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4819.2991993056,"in":"\u4eca\u65e5 1:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4877.1790736111,"in":"\u4eca\u65e5 1:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4778.1225506944,"in":"\u4eca\u65e5 2:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4696.3610270833,"in":"\u4eca\u65e5 2:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4570.2139756944,"in":"\u4eca\u65e5 2:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4575.6763138889,"in":"\u4eca\u65e5 2:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4535.2969729167,"in":"\u4eca\u65e5 3:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4530.8323680556,"in":"\u4eca\u65e5 3:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4525.9275368056,"in":"\u4eca\u65e5 3:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4500.4550333333,"in":"\u4eca\u65e5 3:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4476.8956034722,"in":"\u4eca\u65e5 4:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4547.6369222222,"in":"\u4eca\u65e5 4:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4573.3815201389,"in":"\u4eca\u65e5 4:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4578.4678638889,"in":"\u4eca\u65e5 4:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4597.7377631944,"in":"\u4eca\u65e5 5:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4639.1944645833,"in":"\u4eca\u65e5 5:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4541.4933576389,"in":"\u4eca\u65e5 5:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4564.5587875,"in":"\u4eca\u65e5 5:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4669.0973881944,"in":"\u4eca\u65e5 6:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4847.9360944444,"in":"\u4eca\u65e5 6:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4789.8508895833,"in":"\u4eca\u65e5 6:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4706.4545430556,"in":"\u4eca\u65e5 6:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4598.5472791667,"in":"\u4eca\u65e5 7:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4688.4752472222,"in":"\u4eca\u65e5 7:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4597.5869548611,"in":"\u4eca\u65e5 7:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4626.1631638889,"in":"\u4eca\u65e5 7:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4718.76036875,"in":"\u4eca\u65e5 8:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4723.9756548611,"in":"\u4eca\u65e5 8:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4811.3339590278,"in":"\u4eca\u65e5 8:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4945.1186673611,"in":"\u4eca\u65e5 8:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4980.3562576389,"in":"\u4eca\u65e5 9:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4916.3301319444,"in":"\u4eca\u65e5 9:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4942.7446215278,"in":"\u4eca\u65e5 9:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4911.3768381944,"in":"\u4eca\u65e5 9:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4980.1342576389,"in":"\u4eca\u65e5 10:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5062.0299590278,"in":"\u4eca\u65e5 10:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5031.2884520833,"in":"\u4eca\u65e5 10:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5041.9540923611,"in":"\u4eca\u65e5 10:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5054.9274013889,"in":"\u4eca\u65e5 11:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4980.4535756944,"in":"\u4eca\u65e5 11:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4973.5922680556,"in":"\u4eca\u65e5 11:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4978.6341583333,"in":"\u4eca\u65e5 11:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5022.1187895833,"in":"\u4eca\u65e5 12:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4923.7354569444,"in":"\u4eca\u65e5 12:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4893.5919152778,"in":"\u4eca\u65e5 12:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4986.5212979167,"in":"\u4eca\u65e5 12:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5127.4945590278,"in":"\u4eca\u65e5 13:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5170.1152208333,"in":"\u4eca\u65e5 13:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5088.0626569444,"in":"\u4eca\u65e5 13:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5131.9121319444,"in":"\u4eca\u65e5 13:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5153.8332569444,"in":"\u4eca\u65e5 14:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5172.8111819444,"in":"\u4eca\u65e5 14:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5255.8125305556,"in":"\u4eca\u65e5 14:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5280.4714180556,"in":"\u4eca\u65e5 14:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5236.8199215278,"in":"\u4eca\u65e5 15:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5204.9226006944,"in":"\u4eca\u65e5 15:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5184.1272361111,"in":"\u4eca\u65e5 15:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5295.2446701389,"in":"\u4eca\u65e5 15:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5301.5487673611,"in":"\u4eca\u65e5 16:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5287.91090625,"in":"\u4eca\u65e5 16:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5245.9780659722,"in":"\u4eca\u65e5 16:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5213.1664722222,"in":"\u4eca\u65e5 16:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5143.4097673611,"in":"\u4eca\u65e5 17:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4989.1798680556,"in":"\u4eca\u65e5 17:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4967.8676319444,"in":"\u4eca\u65e5 17:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4960.3540381944,"in":"\u4eca\u65e5 17:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5137.9648125,"in":"\u4eca\u65e5 18:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5160.7810034722,"in":"\u4eca\u65e5 18:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5236.1736631944,"in":"\u4eca\u65e5 18:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5250.1275520833,"in":"\u4eca\u65e5 18:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5237.7119652778,"in":"\u4eca\u65e5 19:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5312.9141805556,"in":"\u4eca\u65e5 19:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5255.14934375,"in":"\u4eca\u65e5 19:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 19:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 20:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 20:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 20:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 20:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 21:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 21:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 21:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 21:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 22:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 22:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 22:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 22:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 23:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 23:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 23:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 23:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":null,"in":"\u4eca\u65e5 24:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"}],"y_minute2":[null,{"y":4913.5336694444,"in":"\u6628\u65e5 0:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4977.2309486111,"in":"\u6628\u65e5 0:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4897.7800041667,"in":"\u6628\u65e5 0:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4947.8831152778,"in":"\u6628\u65e5 1:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4920.5969909722,"in":"\u6628\u65e5 1:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4917.1621645833,"in":"\u6628\u65e5 1:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4934.2140763889,"in":"\u6628\u65e5 1:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5052.3872888889,"in":"\u6628\u65e5 2:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5083.8105638889,"in":"\u6628\u65e5 2:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5102.67265,"in":"\u6628\u65e5 2:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5093.6713645833,"in":"\u6628\u65e5 2:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5084.285075,"in":"\u6628\u65e5 3:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5014.6474756944,"in":"\u6628\u65e5 3:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4976.6329798611,"in":"\u6628\u65e5 3:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4974.4932493056,"in":"\u6628\u65e5 3:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4967.2052277778,"in":"\u6628\u65e5 4:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4916.1981104167,"in":"\u6628\u65e5 4:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4999.9199701389,"in":"\u6628\u65e5 4:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4885.3906375,"in":"\u6628\u65e5 4:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4889.6869868056,"in":"\u6628\u65e5 5:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4906.7938013889,"in":"\u6628\u65e5 5:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4884.8289555556,"in":"\u6628\u65e5 5:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4938.3255930556,"in":"\u6628\u65e5 5:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4895.9996972222,"in":"\u6628\u65e5 6:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5067.6193166667,"in":"\u6628\u65e5 6:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4883.5971180556,"in":"\u6628\u65e5 6:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4856.0514048611,"in":"\u6628\u65e5 6:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4764.2961104167,"in":"\u6628\u65e5 7:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4807.7758833333,"in":"\u6628\u65e5 7:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4785.6581118056,"in":"\u6628\u65e5 7:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4822.0454652778,"in":"\u6628\u65e5 7:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4882.14365625,"in":"\u6628\u65e5 8:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4838.7922854167,"in":"\u6628\u65e5 8:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4831.6742631944,"in":"\u6628\u65e5 8:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4877.5668090278,"in":"\u6628\u65e5 8:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4858.4613715278,"in":"\u6628\u65e5 9:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4924.2181541667,"in":"\u6628\u65e5 9:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4978.72970625,"in":"\u6628\u65e5 9:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5004.41905625,"in":"\u6628\u65e5 9:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5004.9291520833,"in":"\u6628\u65e5 10:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5032.6297555556,"in":"\u6628\u65e5 10:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5157.0797138889,"in":"\u6628\u65e5 10:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5110.4139840278,"in":"\u6628\u65e5 10:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5099.5442416667,"in":"\u6628\u65e5 11:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5088.0842736111,"in":"\u6628\u65e5 11:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4987.0174284722,"in":"\u6628\u65e5 11:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4897.8343020833,"in":"\u6628\u65e5 11:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4810.6326638889,"in":"\u6628\u65e5 12:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4650.2832736111,"in":"\u6628\u65e5 12:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4714.8058506944,"in":"\u6628\u65e5 12:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4698.2398090278,"in":"\u6628\u65e5 12:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4833.5511986111,"in":"\u6628\u65e5 13:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4946.9314875,"in":"\u6628\u65e5 13:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4948.2312694444,"in":"\u6628\u65e5 13:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4958.4934916667,"in":"\u6628\u65e5 13:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5082.5705527778,"in":"\u6628\u65e5 14:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5064.82405,"in":"\u6628\u65e5 14:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5109.2259090278,"in":"\u6628\u65e5 14:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5181.8392409722,"in":"\u6628\u65e5 14:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5220.0151965278,"in":"\u6628\u65e5 15:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5165.9126506944,"in":"\u6628\u65e5 15:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5238.1532666667,"in":"\u6628\u65e5 15:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5190.7455333333,"in":"\u6628\u65e5 15:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5214.1558951389,"in":"\u6628\u65e5 16:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5256.0375715278,"in":"\u6628\u65e5 16:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5255.1311930556,"in":"\u6628\u65e5 16:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5349.0210258929,"in":"\u6628\u65e5 16:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5294.4429325397,"in":"\u6628\u65e5 17:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5174.742578373,"in":"\u6628\u65e5 17:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":5012.2046090278,"in":"\u6628\u65e5 17:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4892.4237,"in":"\u6628\u65e5 17:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4847.5301319444,"in":"\u6628\u65e5 18:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4892.2548256944,"in":"\u6628\u65e5 18:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4937.7508159722,"in":"\u6628\u65e5 18:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4889.0114652778,"in":"\u6628\u65e5 18:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4921.3385381944,"in":"\u6628\u65e5 19:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4846.5356097222,"in":"\u6628\u65e5 19:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4953.2500951389,"in":"\u6628\u65e5 19:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4967.62178125,"in":"\u6628\u65e5 19:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4821.5891388889,"in":"\u6628\u65e5 20:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4803.3705569444,"in":"\u6628\u65e5 20:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4924.2779875,"in":"\u6628\u65e5 20:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4887.8832409722,"in":"\u6628\u65e5 20:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4924.7442544643,"in":"\u6628\u65e5 21:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4898.830728869,"in":"\u6628\u65e5 21:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4864.4732875,"in":"\u6628\u65e5 21:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4881.5168729167,"in":"\u6628\u65e5 21:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4837.2012228175,"in":"\u6628\u65e5 22:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4816.0308650794,"in":"\u6628\u65e5 22:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4811.9582218254,"in":"\u6628\u65e5 22:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4835.7866826389,"in":"\u6628\u65e5 22:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4749.8205854167,"in":"\u6628\u65e5 23:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4765.7178736111,"in":"\u6628\u65e5 23:15 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4701.7659972222,"in":"\u6628\u65e5 23:30 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4685.4948402778,"in":"\u6628\u65e5 23:45 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"},{"y":4619.5311451389,"in":"\u6628\u65e5 24:00 \u5b9e\u65f6\u8d1f\u8377","unit":"kW"}]};
        $("#"+char_id).removeClass("bg_load");
        if(jsondata)
        { 
            var data = [];
            data.categories = xdata;
            data.series = jsondata.y_hour;
            data.series2 = jsondata.y_minute; 
            data.series_min2 = jsondata.y_minute2; 
            data.max_y = jsondata.max_y;

            powerColumns(char_id,data,'');
        }
        
        if(char_id === "column_1"){
            showPowerColumns_timeout = window.setTimeout('showPowerColumns("column_1")', 5*60000);
        }
	}

	function powerColumns(obj,data,name)
	{ 
	    Highcharts.setOptions({
	        global: {
	            useUTC: false
	        }
	    });

	    $('#'+obj).highcharts({
	        chart: {
	            type: 'column',
	            marginLeft: 0, 
	            marginRight: 20 ,
	            paddingLeft: 0, 
	            paddingRight: 0,
	            height: 290 
	        },
	        legend: {
	            enabled: true
	        },
	        title: {
	            text: name
	        },
	        tooltip: {
	            formatter: function(){
	                return '<b>'+this.point.in +'<br/>'+ Highcharts.numberFormat(this.point.y, 1, ".", ",") + ' ' + this.point.unit + '</b>';
	            },
	            shared: false,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0,
	                groupPadding: 0,  
	                borderWidth: 0, 
	                states: {
	                  hover: {
	                    enabled:false //鼠标放上去时的状态控制
	                  }
	                } 
	              
	            }
	        },
	        xAxis: {
	            labels: {
	                rotation: 0
	            },
	            tickInterval: 4,
	            //tickLength: 0,
	            categories: data.categories
	        },
	        yAxis: {
	            gridLineWidth: 0,
	            min: 0,
	            max: data.max_y,
	            title: {
	                text: ''
	            },
	            labels:{
	                enabled: false
	            }
	        },
	        credits:{//右下角的文本
	            enabled: false
	        },
	        series: [
	            {
	                name: '今日分时电量',
	                data: data.series,
	                color:'#1fb5ac'
	            },
	            {                                                              
	               type: 'spline',                                               
	               name: '今日实时负荷',                                              
	               data: data.series2,
	               color:'#32323a',
	               marker: {                                                     
	                   lineWidth: 2,                                               
	                   lineColor: '#32323a',               
	                   fillColor: '#fff', 
	                   radius: 2.5,
	                    states: {
	                      hover: {
	                        enabled:true, //鼠标放上去时的状态控制
	                        radius: 4 
	                      }
	                    }                                           
	               }                                                             
	            },
	            {                                                              
	               type: 'spline',                                               
	               name: '昨日实时负荷',                                              
	               data: data.series_min2,
	               color:'#aaaaaa',
	               marker: {                                                     
	                   lineWidth: 2,                                               
	                   lineColor: '#aaaaaa',               
	                   fillColor: '#fff', 
	                   radius: 2.5,
	                    states: {
	                      hover: {
	                        enabled:true, //鼠标放上去时的状态控制
	                        radius: 4 
	                      }
	                    }                                           
	               }                                                             
	            } 
	        ]
	    });

	}


	function showPowerLines(char_id, type)
	{ 
	    $("#"+char_id).html("").addClass("bg_load");
	    var url = 'http://rdny-service.24kwh.com/index.php/system/eemsdata/'; 
	    if(type === "days"){
	        //url = 'http://app.eemsii.hezongyun.com/index.php/electricstatistics/lastndaysdata/10'+"/"+$("#now_company_id").val()+"/"+$("#workshop_id").val();
	    }
	    else if(type === "month"){
	        //url = 'http://app.eemsii.hezongyun.com/index.php/electricstatistics/lastnmonthdata/6'+"/"+$("#now_company_id").val()+"/"+$("#workshop_id").val();
	    }
	    
	    
        if(type === "days"){
            jsondata = {"categories":["08\u670818\u65e5","08\u670819\u65e5","08\u670820\u65e5","08\u670821\u65e5","08\u670822\u65e5","08\u670823\u65e5","08\u670824\u65e5","08\u670825\u65e5","08\u670826\u65e5","08\u670827\u65e5"],"series_data1":[13.157,12.069,11.485,11.422,13.288,12.85,13.072,12.957,12.297,11.88],"series_data2":[9.195,8.322,7.94,8.167,9.24,9.013,9.15,9.043,8.519,8.28]};
        }
        else if(type === "month"){
            jsondata = {"categories":["02\u6708","03\u6708","04\u6708","05\u6708","06\u6708","07\u6708"],"series_data1":[null,244.789,211.568,188.994,202.18,364.672],"series_data2":[null,158.715,142.687,132.962,141.723,255.678]};
        }
            
            $("#"+char_id).removeClass("bg_load");
            if(jsondata)
            { 
                var data = [];
                data.categories = jsondata.categories;
                var ietype = $("#workshop_id").find("option:selected").data("type"); //是发电还是用电
                var line_name = '';
                if (ietype === 'e') {
                    line_name = '发电量';
                    jsondata.series_data2 = [];
                } else {
                    line_name = "用电量";
                }
                data.series = [
                    {
                        type: 'spline',
                        name: line_name,
                        tooltip: {
                            valueDecimals: 3,
                            valueSuffix: ' 万kWh'
                        },
                        data: jsondata.series_data1
                    }, 
                    {
                        type: 'spline',
                        name: '电费',
                        tooltip: {
                            valueDecimals: 3,
                            valueSuffix: ' 万元'
                        },
                        data: jsondata.series_data2
                    } 
                ];
                powerLines(char_id,data,'');
            }
            
            window.setTimeout('showPowerLines("'+char_id+'","'+type+'")', 15*60000);
        
	}

	function powerLines(obj,data,name)
	{ 
	    Highcharts.setOptions({
	        global: {
	            useUTC: false
	        }
	    });

	    $('#'+obj).highcharts({
	        chart: {
	            zoomType: 'xy'
	        },
	        colors:['#1fb5ac','#f89f07', '#24b7e3', '#1fbba6'],
	        legend: {
	            enabled: false
	        },
	        title: {
	            text: name
	        },
	        tooltip: {
	            shared: true
	        },
	        plotOptions: {
	            series: {
	                lineWidth: 4,
	                marker: {
	                    radius: 6,
	                    fillColor: '#FFFFFF',
	                    lineWidth: 2,
	                    lineColor: null // inherit from series
	                }
	            }
	        },
	        xAxis: {
	            categories: data.categories
	        },
	        yAxis: {
	            min: 0,
	            gridLineWidth: 0,
	            title: {
	                text: ''
	            },
	            labels:{
	                enabled: false
	            }
	        },
	        credits:{//右下角的文本
	            enabled: false
	        },
	        series: data.series
	    });

	}

	function initChars(){
	    initPowerPie("today");
	    showPowerColumns("column_1");
	    showPowerLines("line_1", "days");
	    showPowerLines("line_2", "month");
	    //initRealdata();
	}

	function initRealdata(){
	    //数据
		$("#yshu").html(jsondata.yshu);
        $("#yougong_power").html(formatCurrency(jsondata.yougong_power));
        $("#wugong_power").html(formatCurrency(jsondata.wugong_power));
        //sum_charge
        $("#sum_charge").html(formatCurrency(jsondata.sum_charge));
        if( jsondata.yougong_power != 0){
            var he = jsondata.sum_charge / jsondata.yougong_power ;
            $("#charge_power_he").html(he.toFixed(3));
        }
	}


	$(function(){	
	    initChars(); 
	});

    //182
    function getlastxb(str){
          var lastxb=0; //下标
          if(str.indexOf('.') != -1){
             lastxb=str.indexOf('.');
             str+="0"; //有小数点最多加一个零，也可能不需要加，反正后面会截取掉
             return str.substring(0,lastxb+3); //截取
          }else{//如果连小数点都没有，就加.00
             return str+".00";
          }
    }
    
    
    function formatCurrency(num) {
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
        num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*100+0.50000000001);
        cents = num%100;
        num = Math.floor(num/100).toString();
        if(cents<10)
        cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
        num = num.substring(0,num.length-(4*i+3))+','+
        num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }
    $(".left-f li").on("click", function () {
        $(this).addClass("active2").siblings().removeClass("active2");
    });
});