//@ sourceURL=yhglDetail.js
function YhglDetail(afterSaveCallbk, curData)
{
	var g_afterSaveCallbk = afterSaveCallbk;
	var g_curData = curData;
	init();
	function init()
	{
		initControlAction();
	}
	
	function initControlAction()
	{
		$("#detail_form").validate({
		    highlight: function(element) {
		      $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		    },
		    success: function(element) {
		      $(element).closest('.form-group').removeClass('has-error');
		    },
		    submitHandler : function(){
		    	$('div.eem_window_close').click();
		    	showProgress('测试提交进度');
		    	showDynamicMessage('标题', '内容', 1);
		    	return false;
		    }
		});
		
		$('.detail_search').niceSelect();
		if(g_curData == null)
		{
			getAllDlyhSelecte('customerId');
			$('#pasword_div').show();
		}
		else
		{
			initControlVal();
			$('#pasword_div').hide();
		}
	}
	
	function initControlVal()
	{
		if(g_curData != null)
		{
			$('#name').val(getObjStr(g_curData.name));
		}
	}
	
	function doSaveAction()
	{
		var temp = {
			name : $('#name').val()
		};
		
		var ajaxType = 'POST';
		var msgTitle = '添加用户';
		if(g_curData != null)
		{
			ajaxType = 'PUT';
			msgTitle = '修改用户';
			temp.id = g_curData.id;
		}
		else
		{
			temp.password = $('#password').val();
		}
		
		$('div.eem_window_close').click();
    	var progress = showProgress('正在保存用户');
    	
		$.ajax({
			url: rootpath + '/' + PATH_USER + '/info',
			type : ajaxType,
			dataType: 'json',
		    contentType: 'application/json',
			data : JSON.stringify(temp),
			complete : function(XHR, TS) {
				hideProgress(progress);
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						showDynamicMessage(STR_CONFIRM, msgTitle + '成功', MESSAGE_TYPE_INFO);
						if(g_afterSaveCallbk != null)
						{
							g_afterSaveCallbk();
						}
					}
					else
					{
						showDynamicMessage(STR_CONFIRM, msgTitle + '失败:' + ar.msg, MESSAGE_TYPE_ERROR);
					}
				}
				else
				{
					showSystemError();
				}
			}
		});
	}
	return this;
}