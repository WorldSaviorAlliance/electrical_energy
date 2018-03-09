//@ sourceURL=jsglDetail.js
function JsglDetail(afterSaveCallbk, curData)
{
	var g_afterSaveCallbk = afterSaveCallbk;
	var g_curData = curData;
	init();
	function init()
	{
		initControlAction();
		initControlVal();
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
		
		$('#cancel').unbind('click').click(function(){
			$('div.eem_window_close').click();
		})
		
		$('input').focus(function(){
			$('label.input_msg').hide();
			$('label.input_msg[for="' + $(this).attr('id')+ '"]').show();
		});
	}
	
	function initControlVal()
	{
		if(g_curData != null)
		{
			$('#name').val(g_curData.name);
			$.ajax({
				url: rootpath + '/' + PATH_JSGL + '/info?id=' + g_curData.id,
				type : 'GET', 
				dataType: 'json',
			    contentType: 'application/json',
				complete : function(XHR, TS) {
					if (TS == "success") {
						var ar = JSON.parse(XHR.responseText);
						if(ar.code == 0)
						{
							var $js = $('#js_datas input[type="checkbox"]');
							for(var i = 0 ; i < $js.length; i++)
							{
								var temp = $js.get(i);
								for(var j = 0; j < ar.data.authorityDesc.length; j++)
								{
									if($(temp).attr('desc') == ar.data.authorityDesc[j])
									{
										$(temp).attr("checked", true);
										break;
									}
								}
							}
						}
					}
				}
			});
		}
		$.ajax({
			url: rootpath + '/' + PATH_JSGL + '/list_authority',
			type : 'POST', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						if(ar.data != null && ar.data.length != 0)
						{
							var $js = $('#js_datas input[type="checkbox"]');
							for(var i = 0 ; i < $js.length; i++)
							{
								var temp = $js.get(i);
								for(var j = 0; j < ar.data.length; j++)
								{
									if($(temp).attr('desc') == ar.data[j].desc)
									{
										$(temp).attr('jsId', ar.data[j].id);
										break;
									}
								}
							}
						}
					}
				}
			}
		});
	}
	
	function doSaveAction()
	{
		var name = $('#name').val();
		var $js = $('#js_datas input[type="checkbox"]');
		var isSel = false;
		for(var i = 0; i < $js.length; i++)
		{
			if($($js.get(i)).is(":checked"))
			{
				isSel = true;
				break;
			}
		}
		if(name == '')
    	{
    		showDynamicMessage(STR_CONFIRM, '请输入角色名称', MESSAGE_TYPE_ERROR);
    		return false;
    	}
		
		if(!isSel)
    	{
    		showDynamicMessage(STR_CONFIRM, '请选择权限', MESSAGE_TYPE_ERROR);
    		return false;
    	}
		
		var authorityIds = [];
		var authorityDesc = [];
		for(var i = 0; i < $js.length; i++)
		{
			if($($js.get(i)).is(":checked"))
			{
				var temp = $($js.get(i));
				authorityIds.push($(temp).attr('jsId'));
				authorityDesc.push($(temp).attr('desc'));
			}
		}
		
		
		var temp = {
			name : name,
			authorityIds : authorityIds,
			authorityDesc : authorityDesc
		};
		
		var ajaxType = 'POST';
		var msgTitle = '添加角色';
		if(g_curData != null)
		{
			ajaxType = 'PUT';
			msgTitle = '修改角色';
			temp.id = g_curData.id;
		}
		
		$('div.eem_window_close').click();
    	var progress = showProgress('正在保存角色');
    	
		$.ajax({
			url: rootpath + '/' + PATH_JSGL + '/info',
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