$(function(){
	$.ajax({
		url: rootpath + '/' + PATH_NX + '/cmpq_statistics',
		type : 'GET', 
		dataType: 'json',
	    contentType: 'application/json',
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);
				if(ar.code == 0)
				{
					$('#validQuantity').html(ar.data.validQuantity);
					$('#invalidQuantity').html(ar.data.invalidQuantity);
					$('#totalPrice').html(ar.data.totalPrice);
				}
			}
		}
	});
	
	$.ajax({
		url: rootpath + '/' + PATH_NX + '/ypq_statistics',
		type : 'GET', 
		dataType: 'json',
	    contentType: 'application/json',
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);
				var troughQuantity = 0;  //低估电量
				var flatQuantity = 0;  //平段电量
				var peakQuantity = 0;  //高峰电量
				if(ar.code == 0)
				{
					troughQuantity = ar.data.troughQuantity;
					flatQuantity = ar.data.flatQuantity;
					peakQuantity = ar.data.peakQuantity;
				}
				var jsondata = {"powerdata":[{"name":"高峰","value": peakQuantity},{"name":"平段","value": flatQuantity},{"name":"低谷","value": troughQuantity}],
		    		    "color":["#f7c31e","#36cea2","#24b7e3"],
		    		    "powertotal":(peakQuantity + flatQuantity + troughQuantity)	};
				setChart('pie_day_1', jsondata.color, jsondata.powerdata, jsondata.powertotal, '万kWh');
			}
		}
	});
	
	$.ajax({
		url: rootpath + '/' + PATH_NX + '/ypp_statistics',
		type : 'GET', 
		dataType: 'json',
	    contentType: 'application/json',
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);
				var troughPrice = 0;  //低估电价
				var flatPrice = 0;  //平段电价
				var peakPrice = 0;  //高峰电价
				if(ar.code == 0)
				{
					troughPrice = ar.data.troughPrice;
					flatPrice = ar.data.flatPrice;
					peakPrice = ar.data.peakPrice;
				}
				var jsondata = {"powerdata":[{"name":"高峰","value":peakPrice},{"name":"平段","value":flatPrice},{"name":"低谷","value":troughPrice}],
		    		    "color":["#f7c31e","#36cea2","#24b7e3"],
		    		    "powertotal":(flatPrice + peakPrice + troughPrice)};
				setChart('pie_day_2', jsondata.color, jsondata.powerdata, jsondata.powertotal, '元');
			}
		}
	});
	$.ajax({
		url: rootpath + '/' + PATH_NX + '/mpq_list',
		type : 'GET', 
		dataType: 'json',
	    contentType: 'application/json',
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);
				if(ar.code == 0)
				{
					setMonthChart(ar.data);
				}
			}
		}
	});
	
	function setMonthChart(allData)
	{
		var now = new Date();
		var time = [];
		var allFlat = [];
		var allPeak = [];
		var allThrough = [];
		if(allData == null || allData.length == 0)
		{
			return;
		}
		for(var i = 0; i < allData.length; i++)
		{
			time.push(allData[i].month);
			allFlat.push(allData[i].flatQuantity);
			allPeak.push(allData[i].peakQuantity);
			allThrough.push(allData[i].troughQuantity);
		}
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data:['低谷','平段','高峰']
			    },
			    toolbox: {
			        show : true,
			        orient: 'vertical',
			        x: 'right',
			        y: 'center',
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : time
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    color : ["#f7c31e","#36cea2","#24b7e3"],
			    series : [
			        {
			            name:'低谷',
			            type:'bar',
			            data: allThrough
			        },
			        {
			            name:'平段',
			            type:'bar',
			            data: allFlat
			        },
			        {
			            name:'高峰',
			            type:'bar',
			            data: allPeak
			        }
			    ]
			};
		
		 var myChart = echarts.init(document.getElementById('month'));
		    myChart.setOption(option);
	}
	
	function setChart(char_id, colordata, data, total, unit){
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
	                formatter: "{b}: {c} "+ unit
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
	    var myChart = echarts.init(document.getElementById(char_id));
	    myChart.setOption(pieoption);
	}
	
});
