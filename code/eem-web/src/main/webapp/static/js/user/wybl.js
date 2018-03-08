//@ sourceURL=wybl.js
$(function()
{
	console.log(g_params);
	var g_page_wybl_detail = null;
	var g_all_datas = null; //当前所有的数据
	var g_all_count = 0;  //当前所有数据的个数
	init();
	/**
	 * 初始化界面
	 */
	function init()
	{
		initControlAction();
		getAllData();
	}
	
	/**
	 * 初始化控件事件
	 */
	function initControlAction(FIRST_PAGE)
	{
		$('#add').unbind('click').click(function(){
			$(this).blur();
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/user/wyblDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '办理套餐',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	g_page_wybl_detail = new WyblDetail(getAllData);
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
		$.ajax({
			url: rootpath + '/' + PATH_WYBL + '/list?userId=' + g_params.param,
			type : 'POST', 
			dataType: 'json',
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
			g_all_datas = datas;console.log(g_all_datas);
			var trs = '';
			for(var i = 0; i < datas.length; i++)
			{
				var temp = datas[i];
				trs += '<tr type="data">'+
							'<td>' + getObjStr(temp.name) + '</td>'+
							'<td>' + getObjStr(temp.time) + '</td>'+
							'<td>' + getObjStr(temp.type) + '</td>'+
							'<td>'+
								'<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="detail" id="' + temp.id + '">详情</a>'+
								'<a class="btn btn-danger btn-xs" flag="del" id="' + temp.id + '">退订</a>'+
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
			confirm('是否退订该套餐？', function(){
				delTc(id);
				return true;
			});
		});
		
		$('a[flag="detail"]').unbind('click').click(function(){
			var id = $(this).attr('id');
			var curData = getCurDataById(id, g_all_datas);
			alert(curData.desc);
		});
	}
	
	/*
	 * 退订对应的套餐
	 */
	function delTc(id)
	{
		$.ajax({
			url: rootpath + '/' + PATH_WYBL + '/cancel_elec_pkg?pkgId=' + id,
			type : 'POST', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					console.log(ar);
					if(ar.code == 0)
					{
						showDynamicMessage(STR_CONFIRM, '退订套餐成功', MESSAGE_TYPE_INFO);
						getAllData();
					}
					else
					{
						showDynamicMessage(STR_CONFIRM, '退订套餐失败', MESSAGE_TYPE_INFO);
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