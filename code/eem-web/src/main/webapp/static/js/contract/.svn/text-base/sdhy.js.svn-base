/**
 * 购电合约界面
 */
console.log(11);
$(function()
{
	initSc();
	
	/**
	 * 初始化界面
	 */
	function initSc()
	{
		initControlAction();
		getAllData();
	}
	
	function initControlAction()
	{
		$('#searchSc').unbind('click').click(function(){
			
		});
		$('#addSc').unbind('click').click(function(){
			
		});
		
		$('#scRangTime').daterangepicker({
		    "startDate": "01/20/2018",
		    "endDate": "01/26/2018"
		});
		
		
		$('#page2Sc').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page2Sc').EemPage(opts);
		
		$('a[flag="del_sc"]').unbind('click').click(function(){
			confirm('是否删除该售电合约？', function(){
				return true;
			});
		});
		
		$('a[flag="modify_sc"]').unbind('click').click(function(){
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/contract/scDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改售电合约',
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