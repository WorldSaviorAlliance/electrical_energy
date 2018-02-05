//@ sourceURL=yddlpc.js
$(function()
{
	$('.select').niceSelect();
	showchart();
	function showchart()
	{
	    var yflag = true;
	    var dataLabelsflag = true;
	    var categoriesdata = [];
	    var data1 = new Array();
	    var data2 = new Array();
	    var data3 = new Array();
	    
	    categoriesdata = ["2017-03","2017-04","2017-05","2017-06","2017-07","2017-08","2017-09","2017-10","2017-11","2017-12","2018-01","2018-02","2018-03"];
	    var customer_id = 1;
	    
	    for(var i=0; i < 13; i++){
	        // 预测电量
	        data1[i] = 700+customer_id*20+80*Math.random();
	        data1[i] = data1[i]/12;
	        data1[i] = parseFloat(data1[i].toFixed(2));
	        // 实际电量
	        data2[i] = 700+customer_id*25.21+120*Math.random();
	        data2[i] = data2[i]/12;
	        data2[i] = parseFloat(data2[i].toFixed(2));
	        // 偏差率
	        data3[i] = Math.abs((data1[i] - data2[i]) / data2[i] * 100);
	        data3[i] = parseFloat(data3[i].toFixed(2));
	        
	        if(i >= 11){
	            data2[i] = null;
	            data3[i] = null;
	        }
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
});