/**
 * 头部部分的事件
 */
$(function(){
	$('#header_exist').unbind('click').click(function(){
		confirm('是否退出系统？', function(){
			return true;
		});
	});
	
	$('#changePsw').unbind('click').click(function(){
		var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
		addDiv.load(rootpath + '/static/jsp/setting/changepsw.jsp', function(){
			$(this).EemWindow({
				height : WINDOW_HEIGHT,
				width : WINDOW_WIDTH,
	            title: '修改密码',
	            content: addDiv,
	            hasBottomBtn : false,
	            onOkBtnFn : function(){
	            	return true;
	            },
	            afterShow : function(){
	            }
	        });	
		});
	});
});