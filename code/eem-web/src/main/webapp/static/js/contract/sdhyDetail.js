//@ sourceURL=sdhyDetail.js
function SdhyDetail(afterSaveCallbk, curData)
{
	var g_afterSaveCallbk = afterSaveCallbk;
	var g_curData = curData;
	init();
	function init()
	{
		initControlAction();
		if(g_curData != null)
		{
			inintControlVal();
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
		    	if($('#file_path').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择合约附件', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#tradePowerQuantity').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '交易电量不能为空', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#normalTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '常规直购电量交易价格不能为空', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#supportTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '精准扶持直购电量交易价格不能为空', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#replaceTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '自备替代直购电交易价格不能为空', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#marginTradePrice').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '富余电量交易价格不能为空', MESSAGE_TYPE_ERROR);
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
		getAllDlyhSelecte('customerId');
		
		$("#att_file").on("change",function(){
		    var filePath=$(this).val();
		    var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
	        $("#file_path").val(fileName);
		});console.log($("#att_file"));
	}
	
	function inintControlVal()
	{
		if(g_curData != null)
		{
			$('#name').val(getObjStr(g_curData.name));
			$('#nickName').val(getObjStr(g_curData.nickName));
			$('#powerType').val(g_curData.powerType);
			$('#capacity').val(getObjStr(g_curData.capacity));
			$('#province').val(g_curData.province);
			$('#city').val(g_curData.city);
			$('#province').niceSelect('update');
			$('#city').niceSelect('update');
			$('#address').val(getObjStr(g_curData.address));
			$('#contactName').val(getObjStr(g_curData.contactName));
			$('#contactPhone').val(getObjStr(g_curData.contactPhone));
			$('#contactPosition').val(getObjStr(g_curData.contactPosition));
			$('#contactEmail').val(getObjStr(g_curData.contactEmail));
			$('#fax').val(getObjStr(g_curData.fax));
			$('#natureType').val(g_curData.natureType);
			$('#natureType').niceSelect('update');
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
			marginTradePrice : $('marginTradePrice').val()
		};
		var msgTitle = '添加售电合约';
		if(g_curData != null)
		{
			msgTitle = '修改售电合约';
			temp.id = g_curData.id;
		}
		
    	var progress = showProgress('正在保存售电合约');
    	console.log($('#att_file'));
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
				hideProgress(progress);
				showSystemError();
			}
		});
	}
	
	return this;
}