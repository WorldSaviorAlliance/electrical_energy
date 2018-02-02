/**
 * 针对我的电源商、所有电源和我的电力客户、所有的电力客户的一个统一界面
 */
console.log(11);
$(function()
{
	initPc();
	
	/**
	 * 初始化界面
	 */
	function initPc()
	{
		initControlAction();
		getAllData();
	}
	
	function initControlAction()
	{
		$('#searchPc').unbind('click').click(function(){
			
		});
		$('#addPc').unbind('click').click(function(){
			
		});
		
		 jQuery(".select2").select2({
			    width: '100%',
			    minimumResultsForSearch: -1
			  });
		 
		$('#page2Pc').empty();
		var opts = {
			totalPage : 100,
			curPage : 1
		};
		$('#page2Pc').EemPage(opts);
		
		$('a[flag="del_pc"]').unbind('click').click(function(){
			confirm('是否删除该客户？', function(){
				return true;
			});
		});
		
		$('a[flag="modify_pc"]').unbind('click').click(function(){
			var addDiv = $('<div></div>');
			addDiv.load(rootpath + '/static/jsp/customer/pcDetail.jsp', function(){
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