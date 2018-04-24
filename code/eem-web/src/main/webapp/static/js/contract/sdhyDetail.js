console.log(11);
function SdhyDetail(afterSaveCallbk, curData, onlyView)
{
	var g_table_month = null;
	var g_afterSaveCallbk = afterSaveCallbk;
	var g_curData = curData;
	var g_onlyView = onlyView;
	init();
	function init()
	{
		initControlAction();
		initControlVal();
		
		if(g_onlyView)
		{
			setTimeout(function(){
				$('input').attr('readOnly', true);
				$('#btn_save').hide();
				$('#cancel').hide();
				$('#att_file').hide();
			}, 300);
		}
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
		    	if($('#name').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入合约名称', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#customerNo').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入用户户号', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#no').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入合约编号', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#file_path').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择合约附件', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#validYear').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择合同有效年份', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#voltageType').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择电压等级', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#tradePowerQuantity').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入交易电量', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#normalTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入常规直购电量交易价格', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#supportTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入精准扶持直购电量交易价格', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#replaceTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入自备替代直购电交易价格', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#marginTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未输入富余电量交易价格', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	
		    	if(!g_table_month.validate())
		    	{
		    		return false;
		    	}
		    	doSaveAction();
		    	return false;
		    }
		});
		
		$('#cancel').unbind('click').click(function(){
			$('div.eem_window_close').click();
		});
		$('#validYear').append(getYearSelectStr());
		$('.detail_search').niceSelect();
		if(g_curData == null)
		{
			getAllDlyhSelecte('customerId');
			getAllDydjSelecte('voltageType');
		}
		
		$("#att_file").on("change",function(){
		    var filePath=$(this).val();
		    var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
	        $("#file_path").val(fileName);
		});
	}
	
	function initControlVal()
	{
		if(g_curData != null)
		{
			$.ajax({
				url: rootpath + '/' + PATH_SDHY + '/info?id=' + g_curData.id,
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
							getAllDlyhSelecte('customerId', g_curData.customer.id);
							getAllDydjSelecte('voltageType', g_curData.voltageType);
							$('#customerNo').val(g_curData.customerNo);
							$('#name').val(g_curData.name);
							$('#no').val(g_curData.no);
							$('#file_path').val(g_curData.attachment);//附件
							$('#validYear').val(g_curData.validYear);
							$('#validYear').niceSelect('update');
							$('#tradePowerQuantity').val(g_curData.tradePowerQuantity);
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
		else
		{
			g_table_month = new Month2DL('dl_datas', 'dj_datas', 'normalTradePrice', 'supportTradePrice', 'replaceTradePrice', 'marginTradePrice');
			getAllDlyhSelecte('customerId');
		}
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
				hideProgress(progress);
				if(data.success)
				{
					showDynamicMessage(STR_CONFIRM, msgTitle + '成功', MESSAGE_TYPE_INFO);
					if(g_afterSaveCallbk != null)
					{
						g_afterSaveCallbk();
					}
					$('div.eem_window_close').click();
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
					$('div.eem_window_close').click();
				}
				hideProgress(progress);
			}
		});
	}
	
	return this;
}