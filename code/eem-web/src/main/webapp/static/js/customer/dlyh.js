//@ sourceURL=dlyh.js
$(function()
{
	var g_page_dlyh_detail = null;
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
	function initControlAction()
	{
		$('#search').unbind('click').click(function(){
			
		});
		$('#add').unbind('click').click(function(){
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/customer/dlyhDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电力用户',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
		            	g_page_dlyh_detail = new DlyhDetail();
		            }
		        });	
			});
		});
		
		$('.select').niceSelect();
		
		$('#page').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page').EemPage(opts);
		
		$('a[flag="del"]').unbind('click').click(function(){
			confirm('是否删除该电力用户？', function(){
				return true;
			});
		});
		
		$('a[flag="modify"]').unbind('click').click(function(){
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/customer/dlyhDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电力用户',
		            content: addDiv,
		            hasBottomBtn : false,
		            onOkBtnFn : function(){
		            	return true;
		            },
		            afterShow : function(){
		            	g_page_dlyh_detail = new DlyhDetail();
		            }
		        });	
			});
		});
	}
	
	/**
	 * 获取所有的数据
	 */
	function getAllData()
	{
		
	}
	return this;
});