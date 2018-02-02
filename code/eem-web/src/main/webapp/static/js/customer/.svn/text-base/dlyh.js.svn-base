/**
 * 针对我的电源商、所有电源和我的电力客户、所有的电力客户的一个统一界面
 */
console.log(11);
$(function()
{
	initPs();
	
	/**
	 * 初始化界面
	 */
	function initPs()
	{
		initControlAction();
		getAllData();
	}
	
	function initControlAction()
	{
		$('#searchPs').unbind('click').click(function(){
			
		});
		$('#addPs').unbind('click').click(function(){
			
		});
		
		$('#psRangTime').daterangepicker({
		    "startDate": "01/20/2018",
		    "endDate": "01/26/2018"
		});
		
		
		$('#page2Ps').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page2Ps').EemPage(opts);
		
		$('a[flag="del_ps"]').unbind('click').click(function(){
			confirm('是否删除该客户？', function(){
				return true;
			});
		});
		
		$('a[flag="modify_ps"]').unbind('click').click(function(){
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/customer/psDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改客户',
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
	
	function getAllData()
	{
		
	}
	return this;	
});