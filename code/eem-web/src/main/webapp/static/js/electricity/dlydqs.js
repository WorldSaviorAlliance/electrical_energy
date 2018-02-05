//@ sourceURL=dlydqs.js
$(function()
{
	var g_page_dlydqs_detail = null;
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
		
		$('.select').niceSelect();
		
		$('#page').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page').EemPage(opts);
	}
	
	/**
	 * 获取所有的数据
	 */
	function getAllData()
	{
		
	}
	return this;		
});