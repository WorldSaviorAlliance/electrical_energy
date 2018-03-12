function PC(type)  //1：年度，0：月度
{
	init();
	
	function init()
	{
		initControl();
		getData();
	}
	function initControl()
	{
		getAllDlyhSelecte('customerId');
		getAllDydjSelecte('voltageType');
		$('#tradeType').append(getTradeTypeSelectStr());
		if(type == 1)
		{
			$('#nd_div').show();
			$('#yd_div').hide();
			$('#startYear').append(getYearSelectStr());
			$('#endYear').append(getYearSelectStr());
		}
		else
		{
			$('#nd_div').hide();
			$('#yd_div').show();
			$('#startTime').append(getMonthSelectStr());
			$('#endTime').append(getMonthSelectStr());
		}
		
		$('.detail_search').niceSelect();
	}
	
	function getData()
	{
		var params = {
			type : type,
			startTime : type == 1 ? $('#startYear').val() : $('#startTime').val(),
			endTime : type == 1 ? $('#endYear').val() : $('#endTime').val(),
			customerNo : $('#customerNo').val(),
			customerId : $('#customerId').val(),
			voltageType : $('#voltageType').val(),
			tradeType : $('#tradeType').val() == '-1' ? '' : $('#tradeType').val()
		}
		$.ajax({
			url: rootpath + '/' + PATH_YJSDL + '/statis_list',
			type : 'POST', 
			dataType: 'json',
		    contentType: 'application/json',
		    data : JSON.stringify(params),
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);console.log(ar);
					if(ar.code == 0)
					{
						construcChart(ar.data);
					}
				}
			}
		});
	}
	
	function construcChart(data)
	{
		if(data == null || data.length == 0)
		{
			return;
		}
	    var yflag = true;
	    var dataLabelsflag = true;
	    var categoriesdata = [];
	    var data1 = new Array();
	    var data2 = new Array();
	    var data3 = new Array();
	    
	    for(var i = 0; i < data.length; i++)
	    {
	    	categoriesdata.push(data[i].date);
	    	data1[i] = data[i].contractData;
	    	data2[i] = data[i].practicalData;
	    	data3[i] = Math.abs((data1[i] - data2[i]) / data2[i] * 100);
		    data3[i] = parseFloat(data3[i].toFixed(2));
	    }
	    
	    $('#chart').highcharts({
	        title: {
	            text: ''
	        },
	        legend: {
	            enabled: true
	        },
	        xAxis: {
	            categories: categoriesdata
	        },
	        yAxis: [
	            {
	                min: 0,
	                title: {
	                    text: ''
	                },
	                labels: {
	                    enabled: yflag,
	                    formatter: function() {
	                        return this.value + ' 万kWh';
	                    }
	                }
	            },
	            {
	                min: 0,
	                visible: false,
	                title: {
	                    text: ''
	                }
	            }
	        ],
	        tooltip: {
	            shared: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        credits:{//右下角的文本
	            enabled: false
	        },
	        series: [
	        {
	            type: 'column',
	            name: '实际用电量',
	            yAxis: 1,
	            color: Highcharts.getOptions().colors[1],
	            data: data2,
	            dataLabels: {
	                enabled: dataLabelsflag,
	                color: '#000000',
	                x: 0,
	                y: 0,
	                style: {
	                        color: 'white',
	                        fontWeight: "bold",
	                        textShadow: "none"
	                },
	                formatter: function() {
	                    return this.y + ' 万kWh';
	                }
	            },
	            tooltip: {
	                valueSuffix: ' 万kWh'
	            }
	        },
	        {
	            type: 'column',
	            name: '预测用电量',
	            color: "#FFC125",
	            data: data1,
	            yAxis: 1,
	            dataLabels: {
	                enabled: dataLabelsflag,
	                color: '#000000',
	                x: 0,
	                y: 0,
	                style: {
	                        color: 'white',
	                        fontWeight: "bold",
	                        textShadow: "none"
	                },
	                formatter: function() {
	                    return this.y + ' 万kWh';
	                }
	            },
	            tooltip: {
	                valueSuffix: ' 万kWh'
	            }
	        },
	        {
	            type: 'spline',
	            name: '偏差率',
	            color: "#00ff00",
	            data: data3,
	            tooltip: {
	                valueSuffix: ' %'
	            }
	        }
	        ]
	    });

	}
}

//@ sourceURL=yddlpc.js
//$(function()
//{
//	
//	showchart();
	
//});