//@ sourceURL=dlydqs.js
$(function()
{
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
		$('#startTime').append(getMonthSelectStr());
		$('#endTime').append(getMonthSelectStr());
		$('.select').niceSelect();
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
	        "startTime": $('#startTime').val(),
	        'endTime' : $('#endTime').val()
		};
		$.ajax({
			url: rootpath + '/' + PATH_YJSDL + '/price_list?page=' + curpage + '&per_page=' + PAGE_COUNT,
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
							'<td>' + getObjStr(temp.customerName) + '</td>'+
							'<td>' + getObjStr(temp.customerNo) + '</td>'+
							'<td>' + getObjStr(temp.emNo) + '</td>'+
							'<td>' + getObjStr(temp.month) + '</td>'+
							'<td>' + getObjStr(temp.voltageType) + '</td>'+
							'<td>' + gettradeType(temp.tradeType) + '</td>'+	
							'<td>' + getObjStr(temp.tradePrice) + '</td>' +
							'<td>' + parseInt(temp.peakPrice) + '万元</td>'+
							'<td>' + parseInt(temp.flatPrice) + '万元</td>'+
							'<td>' + parseInt(temp.troughPrice) + '万元</td>'+
							'<td>' + (parseInt(temp.peakPrice) + parseInt(temp.flatPrice) + parseInt(temp.troughPrice)) + '万元</td>'+
							'<td>' + parseInt(temp.validPrice) + '万元</td>'+
							'<td>' + getObjStr(temp.totalPrice) + '万元</td>'+
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
	}
	return this;		
});