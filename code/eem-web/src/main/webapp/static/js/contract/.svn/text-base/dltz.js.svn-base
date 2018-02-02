/**
 * 购电合约界面
 */
console.log(11);
$(function()
{
	initBc();
	
	/**
	 * 初始化界面
	 */
	function initBc()
	{
		initControlAction();
		getAllData();
	}
	
	function initControlAction()
	{
		$('#searchBc').unbind('click').click(function(){
			
		});
		$('#addBc').unbind('click').click(function(){
			
		});
		
		$('#bcRangTime').daterangepicker({
		    "startDate": "01/20/2018",
		    "endDate": "01/26/2018"
		});
		
		
		$('#page2Bc').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page2Bc').EemPage(opts);
		
		$('a[flag="del_bc"]').unbind('click').click(function(){
			confirm('是否删除该购电合约？', function(){
				return true;
			});
		});
		
		$('a[flag="modify_bc"]').unbind('click').click(function(){
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/contract/bcDetail.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '修改购电合约',
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