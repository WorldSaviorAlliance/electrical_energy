//@ sourceURL=wyblDetail.js
function WyblDetail(afterSaveCallbk, curData)
{
	var g_afterSaveCallbk = afterSaveCallbk;
	var g_curData = curData;
	var g_all_tc = null;
	init();
	function init()
	{
		initControlAction();
		inintControlVal();
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
		});
	}
	
	function inintControlVal()
	{
		$('#pkgId').empty();
		$.ajax({
			url: rootpath + '/' + PATH_WYBL + '/list?userId=-1',
			type : 'POST', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						var datas = ar.data;
						if(datas != null && datas.length != 0)
						{
							g_all_tc = datas;
							var opts = '<option value="-1">--请选择套餐--</option>';
							for(var i = 0; i < datas.length; i++)
							{
								opts += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
							}
							$('#pkgId').append(opts);
							if(g_curData != null)
							{
								$('#pkgId').val(valId);
							}
						}
						$('#pkgId').niceSelect();
					}
				}
				else
				{
					showSystemError();
				}
			}
		});
		
		$('#pkgId').change(function(){
			if(g_all_tc != null && g_all_tc.length != 0)
			{
				for(var i = 0; i < g_all_tc.length; i++)
				{
					if(g_all_tc[i].id == $(this).val())
					{
						$('#detail').html(g_all_tc[i].desc);
						return;
					}
				}
			}
		});
	}
	
	function doSaveAction()
	{
		var pkgId = $('#pkgId').val();
		if(pkgId == -1)
		{
			showDynamicMessage(STR_CONFIRM, '请选择套餐', MESSAGE_TYPE_ERROR);
    		return false;
		}
		$('div.eem_window_close').click();
    	var progress = showProgress('正在办理套餐');
    	
		$.ajax({
			url: rootpath + '/' + PATH_WYBL + '/handle_elec_pkg?pkgId=' + pkgId,
			type : 'POST',
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				hideProgress(progress);
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						showDynamicMessage(STR_CONFIRM, '办理套餐成功', MESSAGE_TYPE_INFO);
						if(g_afterSaveCallbk != null)
						{
							g_afterSaveCallbk();
						}
					}
					else
					{
						showDynamicMessage(STR_CONFIRM, '办理套餐失败:' + ar.msg, MESSAGE_TYPE_ERROR);
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