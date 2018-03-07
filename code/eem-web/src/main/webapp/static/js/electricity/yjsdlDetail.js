//@ sourceURL=yjsdlDetail.js
function YjsdlDetail(afterSaveCallbk, curData)
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
		    	if($('#customerId').val() == -1)
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择电力用户', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	
		    	if($('#month').val() == -1)
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择月份', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	
		    	if($('#tradeType').val() == -1)
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择交易品种', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	doSaveAction();
		    	return false;
		    }
		});
		
		$('#cancel').unbind('click').click(function(){
			$('div.eem_window_close').click();
		});
		
		$('#tradeType').append(getTradeTypeSelectStr());
		$('.detail_search').niceSelect();
		if(g_curData == null)
		{
			getAllDlyhSelecte('customerId');
			getAllDydjSelecte('voltageType');
		}
	}
	
	function initControlVal()
	{
		if(g_curData != null)
		{
			getAllDlyhSelecte('customerId', g_curData.customer.id);
			getAllDydjSelecte('voltageType', g_curData.voltageType);
			$('#customerId').niceSelect('update');
			$('#customerNo').val(g_curData.customerNo);
			$('#emNo').val(g_curData.emNo);
			$('#month').val(g_curData.month);
			$('#month').niceSelect('update');
			$('#peakKwh').val(g_curData.peakKwh);
			$('#flatKwh').val(g_curData.flatKwh);
			$('#troughKwh').val(g_curData.troughKwh);
			$('#idleKwh').val(g_curData.idleKwh);
			$('#tradeType').val(g_curData.tradeType);
			$('#tradeType').niceSelect('update');
		}
		else
		{
			getAllDlyhSelecte('customerId');
		}
	}
	
	function doSaveAction()
	{	
		var temp = {
			customerId : $('#customerId').val(),
			customerNo : $('#customerNo').val(),
			emNo : $('#emNo').val(),
			month : $('#month').val(),
			voltageType : $('#voltageType').val(),
			peakKwh : $('#peakKwh').val(),
			flatKwh : $('#flatKwh').val(),
			troughKwh : $('#troughKwh').val(),
			idleKwh : $('#idleKwh').val(),
			tradeType : $('#tradeType').val()
		};
		
		var msgTitle = '添加月结算电量';
		var ajxaType = 'POST';
		if(g_curData != null)
		{
			ajxaType = 'PUT';
			msgTitle = '修改月结算电量';
			temp.id = g_curData.id;
		}
		$('div.eem_window_close').click();
    	var progress = showProgress('正在保存月结算电量');
    	$.ajax({
			url: rootpath + '/' + PATH_YJSDL + '/info',
			type : ajxaType,
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