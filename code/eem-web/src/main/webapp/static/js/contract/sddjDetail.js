//@ sourceURL=sddjDetail.js
function SddjDetail(g_afterSaveCallbk, id)
{
	var g_id = id;
	var g_table_month = null;
	var g_curData = null;
	init();
	function init()
	{
		initControlAction();
		initTable();
	}
	
	function initTable()
	{
		$.ajax({
			url: rootpath + '/' + PATH_SDHY + '/info?id=' + g_id,
			type : 'GET', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				$('#datas tr[type="loading_msg"]').hide();
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						g_curData = ar.data;
						$('#name').val(g_curData.name);
						$('#normalTradePrice').val(g_curData.normalTradePrice);
						$('#supportTradePrice').val(g_curData.supportTradePrice);
						$('#replaceTradePrice').val(g_curData.replaceTradePrice);
						$('#marginTradePrice').val(g_curData.marginTradePrice);
						g_table_month = new Month2DL('dl_datas', 'dj_datas', 'normalTradePrice', 'supportTradePrice', 'replaceTradePrice', 'marginTradePrice', g_curData);
					}
				}
				else
				{
					showSystemError();
				}
			}
		});
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
		    	if(!g_table_month.validate())
		    	{
		    		showDynamicMessage(STR_CONFIRM, '月份电量不能为空', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	doSaveAction();
		    	return false;
		    }
		});
		
		$('#cancel').unbind('click').click(function(){
			$('div.eem_window_close').click();
		});
		getAllDlyhSelecte('customer');
		g_table_month = new Month2DL('dl_datas', 'dj_datas', 'normalTradePrice', 'supportTradePrice', 'replaceTradePrice', 'marginTradePrice');
	}
	
	function doSaveAction()
	{	
		var tempMonthData = g_table_month.getMonthData();
		var obj = Object.assign(g_curData, tempMonthData);
		
		var msgTitle = '修改售电合约电价';
		g_curData.customerId = g_curData.customer.id;
    	var progress = showProgress('正在保存售电合约电价');
    	$.ajaxFileUpload({
			url: rootpath + '/' + PATH_SDHY + '/info',
			secureuri : false,
			type : 'POST',
			fileElementId : 'att_file',
			dataType : 'json',
			data : g_curData,
			success : function(data, status) {
				$('div.eem_window_close').click();
				hideProgress(progress);
				if(data.success)
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
			},
			error : function(data, status, e) {
				if(data.responseText.indexOf('"code":0') == -1)
				{
					showDynamicMessage(STR_CONFIRM, msgTitle + '失败', MESSAGE_TYPE_INFO);
				}
				else
				{
					showDynamicMessage(STR_CONFIRM, msgTitle + '成功', MESSAGE_TYPE_INFO);
					if(g_afterSaveCallbk != null)
					{
						g_afterSaveCallbk();
					}
				}
				
				$('div.eem_window_close').click();
				hideProgress(progress);
			}
		});
	}
	return this;
}