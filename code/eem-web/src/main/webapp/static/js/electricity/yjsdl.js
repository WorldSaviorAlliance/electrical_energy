//@ sourceURL=yjsdl.js
$(function()
{
	var g_page_yjsdl_detail = null;
	var g_all_data = null; // 当前所有的数据
	var g_all_count = 0;  //当前所有数据的个数
	init();
	/**
	 * 初始化界面
	 */
	function init()
	{
		initControlAction();
		getAllData(FIRST_PAGE);
	}
	
	/**
	 * 初始化控件事件
	 */
	function initControlAction()
	{
		$('#do_search').unbind('click').click(function(){
			getAllData(FIRST_PAGE);
		});
		$('#add').unbind('click').click(function(){
			$(this).blur();
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/electricity/yjsdlDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改月结算电量',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	g_page_yjsdl_detail = new YjsdlDetail(getAllData);
		            }
		        });	
			});
		});
		$('#beginMonth').append(getMonthSelectStr());
		$('#endMonth').append(getMonthSelectStr());
		$('.select').niceSelect();
		
		$('a[flag="del"]').unbind('click').click(function(){
			confirm('是否删除该月结算电量？', function(){
				return true;
			});
		});
		
		$('a[flag="modify"]').unbind('click').click(function(){
			$(this).blur();
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/electricity/yjsdlDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改月结算电量',
		            content: addDiv,
		            hasBottomBtn : false,
		            onOkBtnFn : function(){
		            	return true;
		            },
		            afterShow : function(){
		            	g_page_yjsdl_detail = new YjsdlDetail();
		            }
		        });	
			});
		});
	}
	
	/**
	 * 获取所有的数据
	 */
	function getAllData(curpage)
	{
		if(curpage == null)
		{
			curpage = FIRST_PAGE;
		}
		$('#datas tr[type="loading_msg"]').show();
		$('#datas tr[type="error_msg"]').hide();
		$('#datas tr[type="empty_msg"]').hide();
		$('#datas tr[type="data"]').remove();
		
		var search = {
			"name": $('#search_name').val(),
	        "beginMonth": $('#beginMonth').val(),
	        'endMonth' : $('#endMonth').val()
		};
		$.ajax({
			url: rootpath + '/' + PATH_YJSDL + '/list?page=' + curpage + '&per_page=' + PAGE_COUNT,
			type : 'POST', 
			dataType: 'json',
			data : JSON.stringify(search),
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				$('#datas tr[type="loading_msg"]').hide();
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					console.log(ar);
					if(ar.code == 0)
					{
						initTable(curpage, ar.data);
					}
					else
					{
						$('#datas tr[type="error_msg"]').show();
						$('#datas span[type="error_detail"]').html(ar.msg);
					}
				}
				else
				{
					$('#datas tr[type="empty_msg"]').show();
				}
			}
		});
	}
	
	/**
	 * 初始化表格
	 */
	function initTable(curpage, datas)
	{
		$('#datas tr[type="data"]').remove();
		if(datas != null && datas.length != 0)
		{
			g_all_datas = datas;
			var trs = '';
			for(var i = 0; i < datas.length; i++)
			{
				var temp = datas[i];
				trs += '<tr type="data">'+
							'<td>' + getObjStr(temp.customer.name) + '</td>'+
							'<td>' + getObjStr(temp.customerNo) + '</td>'+
							'<td>' + getObjStr(temp.emNo) + '</td>'+
							'<td>' + getObjStr(temp.month) + '</td>'+
							'<td>' + getObjStr(temp.voltageType) + '</td>'+
							'<td>' + gettradeType(temp.tradeType) + '</td>'+					
							'<td>' + parseInt(temp.peakKwh) + '万kWh</td>'+
							'<td>' + parseInt(temp.flatKwh) + '万kWh</td>'+
							'<td>' + parseInt(temp.troughKwh) + '万kWh</td>'+							
							'<td>' + (parseInt(temp.peakKwh) + parseInt(temp.flatKwh) + parseInt(temp.troughKwh) + parseInt(temp.idleKwh)) + '万kWh</td>'+
							'<td>' + parseInt(temp.idleKwh) + '万kWh</td>'+
							'<td>' + getObjStr(temp.createTime) + '</td>'+
							'<td>'+
								'<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify" id="' + temp.id + '">修改</a>'+
								'<a class="btn btn-danger btn-xs" flag="del" id="' + temp.id + '">删除</a>'+
							'</td>'+
						'</tr>';
			}
			
			$('#datas').append(trs);
		}
		else
		{
			$('#datas tr[type="empty_msg"]').show();
		}
		initTableAction(curpage);
	}
	
	function initTableAction(curpage)
	{
		$('#page').empty();
		var opts = {
			totalPage : getTotalPage(g_all_count),
			curPage : curpage,
			changePageFnc : getAllData
		};
		$('#page').EemPage(opts);
		
		$('a[flag="del"]').unbind('click').click(function(){
			var id = $(this).attr('id');
			confirm('是否删除该月结算电量？', function(){
				delDys(id);
				return true;
			});
		});
		
		$('a[flag="modify"]').unbind('click').click(function(){
			$(this).blur();
			var id = $(this).attr('id');
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/electricity/yjsdlDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改月结算电量',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	var curData = getCurDataById(id, g_all_datas);
		            	g_page_yjsdl_detail = new YjsdlDetail(getAllData, curData);
		            }
		        });	
			});
		});
	}
	
	/*
	 * 删除对应的月结算电量
	 */
	function delDys(id)
	{
		$.ajax({
			url: rootpath + '/' + PATH_YJSDL+ '/info?id=' + id,
			type : 'DELETE', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					console.log(ar);
					if(ar.code == 0)
					{
						showDynamicMessage(STR_CONFIRM, '删除月结算电量成功', MESSAGE_TYPE_INFO);
						getAllData();
					}
					else
					{
						showDynamicMessage(STR_CONFIRM, '删除月结算电量失败', MESSAGE_TYPE_INFO);
					}
				}
				else
				{
					showSystemError();
				}
			}
		});
	}
	return this;		
});