/**
 * 修改密码 
 */
console.log('change');
$(function()
{
	$('input').focus(function(){
		$('label.input_msg').hide();
		$('label.input_msg[for="' + $(this).attr('id')+ '"]').show();
	});
	
	$("#detail_form").validate({
	    highlight: function(element) {
	    	$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	    },
	    success: function(element) {
	    	$(element).closest('.form-group').removeClass('has-error');
	    },
	    submitHandler : function(){
	    	return doModifyPsw();
	    }
	});
	
	$('#cancel').unbind('click').click(function(){
		$('div.eem_window_close').click();
	});
	
	function doModifyPsw()
	{
		var oldpassword = $('#oldpassword').val();
		var newpassword = $('#newpassword').val();
		var confirmpassword = $('#confirmpassword').val();
		if(oldpassword == '')
		{
			showDynamicMessage(STR_CONFIRM, '旧密码不能为空', MESSAGE_TYPE_ERROR);
			return false;
		}
		
		if(oldpassword.length > 12 || oldpassword.length < 6)
		{
			showDynamicMessage(STR_CONFIRM, '旧密码长度为6到12个字符', MESSAGE_TYPE_ERROR);
			return false;
		}
		
		if(newpassword == '')
		{
			showDynamicMessage(STR_CONFIRM, '新密码不能为空', MESSAGE_TYPE_ERROR);
			return false;
		}
		
		if(newpassword.length > 12 || newpassword.length < 6)
		{
			showDynamicMessage(STR_CONFIRM, '新密码长度为6到12个字符', MESSAGE_TYPE_ERROR);
			return false;
		}
		
		if(confirmpassword == '')
		{
			showDynamicMessage(STR_CONFIRM, '确认密码不能为空', MESSAGE_TYPE_ERROR);
			return false;
		}
		
		if(confirmpassword.length > 12 || confirmpassword.length < 6)
		{
			showDynamicMessage(STR_CONFIRM, '确认密码长度为6到12个字符', MESSAGE_TYPE_ERROR);
			return false;
		}
		
		if(newpassword != confirmpassword)
		{
			showDynamicMessage(STR_CONFIRM, '确认密码与新密码不一致，请重新输入', MESSAGE_TYPE_ERROR);
			$('#confirmpassword').val('');
			return false;
		}
		
		var temp = {
			newPwd : newpassword,
			oldPwd : oldpassword,
			userId : g_params.param
		};
			
		$('div.eem_window_close').click();
    	var progress = showProgress('正在修改密码');
    	
		$.ajax({
			url: rootpath + '/' + PATH_USER + '/modify_pwd?newPwd=' + newpassword + '&oldPwd=' + oldpassword + '&userId=' + g_params.param,
			type : 'POST',
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				hideProgress(progress);
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						showDynamicMessage(STR_CONFIRM, '修改密码成功', MESSAGE_TYPE_INFO);
					}
					else
					{
						showDynamicMessage(STR_CONFIRM, '修改密码失败:' + ar.msg, MESSAGE_TYPE_ERROR);
					}
				}
				else
				{
					showSystemError();
				}
			}
		});
	}
});