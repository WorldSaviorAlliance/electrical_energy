//@ sourceURL=sdhy.js
$(function()
{
	var g_page_sdhy_detail = null;
	var g_all_datas = null; //当前所有的数据
	init();
	/**
	 * 初始化界面
	 */
	function init()
	{
		initControlAction();
		getAllData(0);
	}
	
	/**
	 * 初始化控件事件
	 */
	function initControlAction()
	{
		$('#do_search').unbind('click').click(function(){
			getAllData(0);
		});
		$('#add').unbind('click').click(function(){
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/contract/sdhyDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改售电合约',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	g_page_sdhy_detail = new SdhyDetail(getAllData);
		            }
		        });	
			});
		});
		
		$('#modify_dj').unbind('click').click(function(){
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/contract/sddjDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电价',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	new SddjDetail();
		            }
		        });	
			});
		});
		
		
		$('#search_year').append(getYearSelectStr());
		$('.search_select').niceSelect();
	}
	
	/**
	 * 获取所有的数据
	 */
	function getAllData(curpage)
	{
		if(curpage == null)
		{
			curpage = 0;
		}
		$('#datas tr[type="loading_msg"]').show();
		$('#datas tr[type="error_msg"]').hide();
		$('#datas tr[type="empty_msg"]').hide();
		$('#datas tr[type="data"]').remove();
		var search = {
			"name": $('#search_name').val(),
	        "validYear": $('#search_year').val()
			};
		$.ajax({
			url: rootpath + '/' + PATH_SDHY + '/list?page=' + curpage + '&per_page=' + PAGE_COUNT,
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
		var totalpage = 0;
		if(datas != null && datas.length != 0)
		{
			g_all_datas = datas;
			var trs = '';
			totalpage = datas.length / PAGE_COUNT + (datas.length % PAGE_COUNT != 0 ? 1 : 0);
			for(var i = 0; i < datas.length; i++)
			{
				var temp = datas[i];
				trs += '<tr type="data">'+
							'<td>' + getObjStr(temp.customerName) + '</td>'+
							'<td>' + getObjStr(temp.customerNo) + '</td>'+
							'<td>' + getObjStr(temp.name) + '</td>'+
							'<td>' + getObjStr(temp.No) + '</td>'+
							'<td>' + getObjStr(temp.validYear) + '</td>'+
							'<td>' + getObjStr(temp.tradePowerQuantity) + '</td>'+					
							'<td>' + getObjStr(temp.voltageType) + '</td>'+
							'<td></td>'+
							'<td>' + getObjStr(temp.createTime) + '</td>'+
							'<td>'+
								'<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify" id="' + temp.id + '">修改合约</a>'+
								'<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="modify_p" id="' + temp.id + '">修改电价</a>'+
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
	
	function initTableAction(curpage, totalpage)
	{
		$('#page').empty();
		var opts = {
			totalPage : totalpage,
			curPage : curpage
		};
		$('#page').EemPage(opts);
		
		$('a[flag="del"]').unbind('click').click(function(){
			var id = $(this).attr('id');
			confirm('是否删除该售电合约？', function(){
				delDys(id);
				return true;
			});
		});
		
		$('a[flag="modify"]').unbind('click').click(function(){
			var id = $(this).attr('id');
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/contract/sdhyDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改售电合约',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	var curData = getCurDataById(id, g_all_datas);
		            	g_page_sdhy_detail = new SdhyDetail(getAllData, curData);
		            }
		        });	
			});
		});
	}
	
	/*
	 * 删除对应的售电合约
	 */
	function delDys(id)
	{
		$.ajax({
			url: rootpath + '/' + PATH_SDHY + '/info?id=' + id,
			type : 'DELETE', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					console.log(ar);
					if(ar.code == 0)
					{
						showDynamicMessage(STR_CONFIRM, '删除售电合约成功', MESSAGE_TYPE_INFO);
						getAllData();
					}
					else
					{
						showDynamicMessage(STR_CONFIRM, '删除售电合约失败', MESSAGE_TYPE_INFO);
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