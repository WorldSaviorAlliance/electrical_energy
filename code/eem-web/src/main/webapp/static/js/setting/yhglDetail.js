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
		    	return doSaveAction();
		    }
		});
		
		$('input').focus(function(){
			$('label.input_msg').hide();
			$('label.input_msg[for="' + $(this).attr('id')+ '"]').show();
		});
		
		$('.detail_search').niceSelect();
		if(g_curData == null)
		{
			getAllDlyhSelecte('customerId');
			$('#pasword_div').show();
		}
		else
		{
			if(g_curData.type == 0) //电力用户
			{
				getAllDlyhSelecte('customerId');
				$('#customerId').show();
			}
			else //系统用户 //TODO 还需要配置对应的权限
			{
				$('#customerId').hide();
			}
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
		var customer = parseInt($('#customerId').val());
		var name = $('#name').val();
		var password = $('#password').val();
		var userType = $('#userType').val();
		if(name == '')
		{
			showDynamicMessage(STR_CONFIRM, '用户名不能为空', MESSAGE_TYPE_ERROR);
    		return false;
		}
		
		if(name.length > 20 || name.length < 2)
		{
			showDynamicMessage(STR_CONFIRM, '用户名长度范围2到20个字符', MESSAGE_TYPE_ERROR);
    		return false;
		}
		
		if(password == '')
		{
			showDynamicMessage(STR_CONFIRM, '密码不能为空', MESSAGE_TYPE_ERROR);
    		return false;
		}
		if(password.length > 12 || password.length < 6)
		{
			showDynamicMessage(STR_CONFIRM, '密码长度范围6到12个字符', MESSAGE_TYPE_ERROR);
    		return false;
		}
		if(userType == 0 && customer == -1)
		{
			showDynamicMessage(STR_CONFIRM, '请选择对应的电力用户', MESSAGE_TYPE_ERROR);
    		return false;
		}
		
		var temp = {
			name : name,
			password : password,
			type : userType,
			customerId : customer
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
		
		return false;
	}
	return this;
}