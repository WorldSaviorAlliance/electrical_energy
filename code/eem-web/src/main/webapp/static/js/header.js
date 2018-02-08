/**
 * 头部部分的事件
 */
$(function(){
	$('#header_exist').unbind('click').click(function(){
		confirm('是否退出系统？', function(){
			$.ajax({
				url: rootpath + '/logout',
				type : 'GET', 
				dataType: 'json',
			    contentType: 'application/json',
				complete : function(XHR, TS) {
					if (TS == "success") {
						var ar = JSON.parse(XHR.responseText);
						if (ar.code == 0)
						{
							location.replace(rootpath + '/login');
							return;
						}
						else
						{
							$('#error_msg').html("注销失败：" + ar.msg);
						}
					}
				}
			});
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