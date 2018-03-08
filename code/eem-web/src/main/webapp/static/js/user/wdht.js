console.log('wdht');
$(function()
{
	var g_page_sdhy_detail = null;
	var g_all_datas = null; //当前所有的数据
	var g_all_count = 0;  //当前所有数据的个数
	var g_customerName = '';
	getCurUser();
	function getCurUser()
	{
		$.ajax({
			url: rootpath + '/' + PATH_USER + '/info?id=' + g_params.param,
			type : 'GET', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						init();
						g_customerName = ar.data.customerName;
					}
				}
			}
		});
	}
	
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
			curpage = FIRST_PAGE;
		}
		$('#datas tr[type="loading_msg"]').show();
		$('#datas tr[type="error_msg"]').hide();
		$('#datas tr[type="empty_msg"]').hide();
		$('#datas tr[type="data"]').remove();
		var search = {
			"name": g_customerName,
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
					if(ar.code == 0)
					{
						g_all_count = ar.count;
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
								'<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="view" id="' + temp.id + '">查看合约</a>'+
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
		
		$('a[flag="view"]').unbind('click').click(function(){
			$(this).blur();
			var id = $(this).attr('id');
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/contract/sdhyDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '查看售电合约',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	var curData = getCurDataById(id, g_all_datas);
		            	g_page_sdhy_detail = new SdhyDetail(getAllData, curData, true);
		            }
		        });	
			});
		});
	}
	return this;		
});