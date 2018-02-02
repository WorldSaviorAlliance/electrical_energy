/**
 * 购电合约界面
 */
console.log(11);
$(function()
{
	initEa();
	
	/**
	 * 初始化界面
	 */
	function initEa()
	{
		initControlAction();
		getAllData();
	}
	
	function initControlAction()
	{
		$('#searchEa').unbind('click').click(function(){
			
		});
		$('#addEa').unbind('click').click(function(){
			
		});
		
		$('#eaRangTime').daterangepicker({
		    "startDate": "01/20/2018",
		    "endDate": "01/26/2018"
		});
		
		
		$('#page2Ea').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page2Ea').EemPage(opts);
		
		$('a[flag="del_ea"]').unbind('click').click(function(){
			confirm('是否删除该电量调整？', function(){
				return true;
			});
		});
		
		$('a[flag="modify_ea"]').unbind('click').click(function(){
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/contract/eaDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改电量调整',
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