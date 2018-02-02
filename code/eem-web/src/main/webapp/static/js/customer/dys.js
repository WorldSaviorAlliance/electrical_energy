//@ sourceURL=dys.js
$(function()
{
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
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/customer/dysDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电源商',
		            content: addDiv,
		            onOkBtnFn : function(){
		            	$("#basicForm").validate({
		            	    highlight: function(element) {
		            	      $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		            	    },
		            	    success: function(element) {
		            	      $(element).closest('.form-group').removeClass('has-error');
		            	    }
		            	});
		            	return false;
		            },
		            afterShow : function(){
		            }
		        });	
			});
		});
		
		jQuery(".select2").select2({
		    width: '100%',
		    minimumResultsForSearch: -1
		});
		 
		$('#page').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page').EemPage(opts);
		
		$('a[flag="del"]').unbind('click').click(function(){
			confirm('是否删除该电源商？', function(){
				return true;
			});
		});
		
		$('a[flag="modify"]').unbind('click').click(function(){
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/customer/dysDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电源商',
		            content: addDiv,
		            onOkBtnFn : function(){
		            	return true;
		            },
		            afterShow : function(){
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