//@ sourceURL=sddjDetail.js
function SddjDetail()
{
	var g_table_month = null;
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
		
		$('#customer').change(function(){
			
		});
	}
	
	function doSaveAction()
	{	
		var temp = {
			customerId : $('#customerId').val(),
			customerNo : $('#customerNo').val(),
			name : $('#name').val(),
			No : $('#no').val(),
			attachment : $('#file_path').val(),
			validYear : $('#validYear').val(),
			voltageType : $('#voltageType').val(),
			tradePowerQuantity : $('#tradePowerQuantity').val(),
			normalTradePrice : $('#normalTradePrice').val(),
			supportTradePrice : $('#supportTradePrice').val(),
			replaceTradePrice : $('#replaceTradePrice').val(),
			marginTradePrice : $('#marginTradePrice').val()
		};
		var tempMonthData = g_table_month.getMonthData();
		var obj = Object.assign(temp, tempMonthData);
		
		var msgTitle = '添加售电合约';
		if(g_curData != null)
		{
			msgTitle = '修改售电合约';
			temp.id = g_curData.id;
		}
		
    	var progress = showProgress('正在保存售电合约');
    	$.ajaxFileUpload({
			url: rootpath + '/' + PATH_SDHY + '/info',
			secureuri : false,
			type : 'POST',
			fileElementId : 'att_file',
			dataType : 'json',
			data : temp,
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
				showDynamicMessage(STR_CONFIRM, msgTitle + '成功', MESSAGE_TYPE_INFO);
				$('div.eem_window_close').click();
				hideProgress(progress);
				if(g_afterSaveCallbk != null)
				{
					g_afterSaveCallbk();
				}
			}
		});
	}
	return this;
}