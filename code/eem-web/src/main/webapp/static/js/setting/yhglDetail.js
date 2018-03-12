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
			getAllJsSelecte();
			$('#pasword_div').show();
		}
		else
		{
//			if(g_curData.type == 0) //电力用户
//			{
//				getAllDlyhSelecte('customerId', g_curData.customerId);
//				$('#dlyhDiv').show();
//				$('#roleDiv').hide();
//				getAllJsSelecte();
//			}
//			else //系统用户
//			{
//				getAllJsSelecte(g_curData.roleId);
//				$('#dlyhDiv').hide();
//				$('#roleDiv').show();
//				getAllDlyhSelecte('customerId');
//			}
//			$('#userType').val(g_curData.type);
//			$('#userType').niceSelect('update');
			$('#name').val(getObjStr(g_curData.name));
			$('#pasword_div').hide();
			$('#customerId').hide();
			$('#dlyhDiv').hide();
			$('#roleDiv').hide();
			$('#typeDiv').hide();
		}
		$('#cancel').unbind('click').click(function(){
			$('div.eem_window_close').click();
		})
		$('#userType').change(function(){
			var userType = $(this).val();
			if(userType == 0)
			{
				$('#dlyhDiv').show();
				$('#roleDiv').hide();
			}
			else
			{
				$('#dlyhDiv').hide();
				$('#roleDiv').show();
			}
		});
	}
	
	function doSaveAction()
	{
		var customer = parseInt($('#customerId').val());
		var name = $('#name').val();
		var password = $('#password').val();
		var userType = $('#userType').val();
		var roleId = parseInt($('#roleId').val());
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
		
		if(g_curData == null)
		{
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
		}
		
		if(userType == 0 && customer == -1)
		{
			showDynamicMessage(STR_CONFIRM, '请选择对应的电力用户', MESSAGE_TYPE_ERROR);
    		return false;
		}
		else if(userType == 1 && roleId == -1)
		{
			showDynamicMessage(STR_CONFIRM, '请选择对应的角色', MESSAGE_TYPE_ERROR);
    		return false;
		}
		var temp = {
			name : name,
			password : password,
			type : userType,
			customerId : customer,
			roleId : roleId
		};
		
		var ajaxType = 'POST';
		var msgTitle = '添加用户';
		var params = '';
		if(g_curData != null)
		{
			ajaxType = 'PUT';
			msgTitle = '修改用户';
			params = '?userId=' + g_curData.id + '&newName=' + name;
		}
		else
		{
			temp.password = $('#password').val();
		}
		
		$('div.eem_window_close').click();
    	var progress = showProgress('正在保存用户');
    	
		$.ajax({
			url: rootpath + '/' + PATH_USER + '/info' + params,
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
	
	/**
	 * 获取所有的角色下拉列表
	 */
	function getAllJsSelecte(valId)
	{
		$('#roleId').empty();
		var search = {
			'name' : $('#search_name').val(),
			'startPage' : 1,
			'perPageCnt' : 10000
		};
				
		$.ajax({
			url: rootpath + '/' + PATH_JSGL + '/list',
			type : 'POST', 
			dataType: 'json',
		    contentType: 'application/json',
		    data : JSON.stringify(search),
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						var datas = ar.data;
						if(datas != null && datas.length != 0)
						{
							var opts = '<option value="-1">--请选择角色--</option>';
							for(var i = 0; i < datas.length; i++)
							{
								opts += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
							}
							$('#roleId').append(opts);
							if(valId != null)
							{
								$('#roleId').val(valId);
							}
						}
						$('#roleId').niceSelect();
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