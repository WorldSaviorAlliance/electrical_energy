//@ sourceURL=sdhyDetail.js
function DltzDetail(afterSaveCallbk, curData)
{
	var g_afterSaveCallbk = afterSaveCallbk;
	var g_curData = curData;
	var g_all_sdhy = null; //当前所有的售电合约
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
		    	if(parseInt($('#quantity').val()) <= 0)
		    	{
		    		showDynamicMessage(STR_CONFIRM, '调整电量不能小于0', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	if($('#tradeType').val() == '-1')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '请选择交易品种', MESSAGE_TYPE_ERROR);
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
		$('#customerId').change(function(){
			initSdhyList($(this).val());
		});
		
		$('#contractId').change(function(){
			var sdhyId = $(this).val();
			if(g_all_sdhy != null && sdhyId != null && sdhyId != '-1')
			{
				for(var i = 0; i < g_all_sdhy.length; i++)
				{
					if(g_all_sdhy[i].id == sdhyId)
					{
						$('#contractNumber').val(g_all_sdhy[i].No);
						$('#validYear').val(g_all_sdhy[i].validYear);
						$('#voltageType').val(g_all_sdhy[i].voltageType);
						break;
					}
				}
			}
			else
			{
				$('#contractNumber').val('');
				$('#validYear').val('');
				$('#voltageType').val('');
			}
		});
		
		$('#adjustmentType').change(function(){
			var type = $(this).val();
			if(type == 0)
			{
				$('#price').attr('disabled', false);
			}
			else
			{
				$('#price').attr('disabled', true)
			}
		});
	}
	
	function initControlVal()
	{
		if(g_curData != null)
		{
			
		}
		else
		{
			getAllDlyhSelecte('customerId');
		}
		getAllSdhy();
	}
	
	function getAllSdhy()
	{
		var search = {
			"name": '',
	        "validYear": ''
			};
		$.ajax({
			url: rootpath + '/' + PATH_SDHY + '/list?page=0&per_page=10000',
			type : 'POST', 
			dataType: 'json',
			data : JSON.stringify(search),
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				$('#datas tr[type="loading_msg"]').hide();
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					console.log(ar);
					if(ar.code == 0)
					{
						g_all_sdhy = ar.data;
					}
					initSdhyList();
				}
			}
		});
	}
	
	/**
	 * 通过电力用户获取其对应的售电合约
	 */
	function initSdhyList(dlyhId)
	{
		$('#contractId').empty();
		var opts = '<option value="-1">--请选择售电合约--</option>';
		if(dlyhId != null)
		{
			if(g_all_sdhy != null)
			{
				for(var i = 0; i < g_all_sdhy.length; i++)
				{
					if(g_all_sdhy[i].customerId == dlyhId)
					{
						opts += '<option value="' + g_all_sdhy[i].id + '">' + g_all_sdhy[i].name + '</option>';
					}
				}
				$('#contractId').append(opts);
				$('#contractId').niceSelect('update');
			}
		}
		else
		{
			$('#contractId').append(opts);
			$('#contractId').niceSelect();
		}
	}
	
	function doSaveAction()
	{
		var temp = {
				customerId : $('#customerId').val(),
				customerNumber : $('#customerNo').val(),
				contractId : $('#contractId').val(),
				contractNumber : $('#contractNumber').val(),
				validYear : $('#validYear').val(),
				voltageType : $('#voltageType').val(),
				quantity : $('#quantity').val(),
				adjustmentType : $('#adjustmentType').val(),
				month : $('#month').val(),
				price : $('#price').val(),
				tradeType : $('#tradeType').val()
		};
		
		var msgTitle = '添加电量调整';
		if(g_curData != null)
		{
			msgTitle = '修改电量调整';
			temp.id = g_curData.id;
		}
		
		$('div.eem_window_close').click();
    	var progress = showProgress('正在保存电量调整');
    	
		$.ajax({
			url: rootpath + '/' + PATH_DLTZ + '/info',
			type : 'POST',
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