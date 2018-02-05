//@ sourceURL=dys.js
$(function()
{
	var g_page_dys_detail = null;
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
		$('#search').unbind('click').click(function(){
			getAllData(0);
		});
		
		
		$('#add').unbind('click').click(function(){
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/customer/dysDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电源商',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	g_page_dys_detail = new DysDetail();
		            }
		        });	
			});
		});
		
		$('.select').niceSelect();
	}
	
	/**
	 * 获取所有的数据
	 */
	function getAllData(curpage)
	{
		$('#datas tr[type="loading_msg"]').show();
		$('#datas tr[type="error_msg"]').hide();
		$('#datas tr[type="empty_msg"]').hide();
		$('#datas tr[type="data"]').remove();
		var search = {
			"name": $('#name').val(),
	        "province": $('#province').val(),
	        "city": $('#city').val()
			};
		$.ajax({
			url: rootpath + '/' + PATH_DYS + '/list?page=' + curpage + '&per_page=' + PAGE_COUNT,
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
						$('#datas span[type="error_detail]').html(ar.msg);
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
			var trs = '';
			totalpage = datas.length / PAGE_COUNT + (datas.length % PAGE_COUNT != 0 ? 1 : 0);
			for(var i = 0; i < datas.length; i++)
			{
				var temp = datas[i];
				trs += '<tr type="data">'+
							'<td>' + getObjStr(temp.name) + '</td>'+
							'<td>' + getObjStr(temp.nickName) + '</td>'+
							'<td>' + getObjStr(temp.province) + getObjStr(temp.city) + '</td>'+
							'<td>' + getPowerTypeStr(temp.powerType) + '</td>'+
							'<td>' + getObjStr(temp.capacity) + '</td>'+
							'<td>' + getObjStr(temp.natureType) + '</td>'+					
							'<td>' + getObjStr(temp.contactName) + '</td>'+
							'<td>' + getObjStr(temp.contactPhone) + '</td>'+
							'<td>' + getObjStr(temp.contactPosition) + '</td>'+
							'<td>' + getObjStr(temp.contactEmail) + '</td>'+
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
	
	function initTableAction(curpage, totalpage)
	{
		$('#page').empty();
		var opts = {
			totalPage : totalpage,
			curPage : curpage
		};
		$('#page').EemPage(opts);
		
		$('a[flag="del"]').unbind('click').click(function(){
			confirm('是否删除该电源商？', function(){
				return true;
			});
		});
		
		$('a[flag="modify"]').unbind('click').click(function(){
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/customer/dysDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电源商',
		            content: addDiv,
		            hasBottomBtn : false,
		            onOkBtnFn : function(){
		            	return true;
		            },
		            afterShow : function(){
		            	g_page_dys_detail = new DysDetail();
		            }
		        });	
			});
		});
	}
	return this;	
});