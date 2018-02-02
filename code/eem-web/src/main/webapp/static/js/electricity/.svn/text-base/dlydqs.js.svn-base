console.log(11);
$(function()
{
	initBm();
	
	/**
	 * 初始化界面
	 */
	function initBm()
	{
		initControlAction();
		getAllData();
	}
	
	function initControlAction()
	{
		$('#searchBm').unbind('click').click(function(){
			
		});
		$('#addBm').unbind('click').click(function(){
			
		});
		
		$('#bmRangTime').daterangepicker({
		    "startDate": "01/20/2018",
		    "endDate": "01/26/2018"
		});
		
		
		$('#page2Bm').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page2Bm').EemPage(opts);
		
		$('a[flag="del_bm"]').unbind('click').click(function(){
			confirm('是否删除该月结算电量？', function(){
				return true;
			});
		});
		
		$('a[flag="modify_bm"]').unbind('click').click(function(){
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/customer/bmDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改月结算电量',
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