//@ sourceURL=gdhyDetail.js
function GdhyDetail(afterSaveCallbk, curData)
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
		    	if($('#file_path').val() == '')
		    	{
		    		showDynamicMessage(STR_CONFIRM, '未选择合约附件', MESSAGE_TYPE_ERROR);
		    		return false;
		    	}
		    	doSaveAction();
		    	return false;
		    }
		});
		$('#validYear').append(getYearSelectStr());
		$('.detail_search').niceSelect();
		$('#cancel').unbind('click').click(function(){
			$('div.eem_window_close').click();
		});
		
		$('input').focus(function(){
			$('label.input_msg').hide();
			$('label.input_msg[for="' + $(this).attr('id')+ '"]').show();
		});
		
		$("#file").on("change",function(){
		    var filePath=$(this).val();
		    var arr=filePath.split('\\');
	        var fileName=arr[arr.length-1];
	        $("#file_path").val(fileName);
		});
		
		if(g_curData == null)
		{
			getAllDysSelecte('supplier');
		}
		
		$('#add_jy').unbind('click').click(function(){
			var addDiv = $('<div style="padding:0px 15px;overflow:auto;height:' + WINDOW_NO_BOTTOM_HEIGHT + 'px;"></div>');
			addDiv.load(rootpath + '/static/jsp/contract/jydl.jsp', function(){
				$(this).EemWindow({
					height : WINDOW_HEIGHT,
					width : WINDOW_WIDTH,
		            title: '交易电量',
		            content: addDiv,
		            hasBottomBtn : false,
		            afterShow : function(){
//		            	g_page_gdhy_detail = new GdhyDetail(getAllData);
		            }
		        });	
			});
		});
	}
	
	function initControlVal()
	{
		
	}
	
	function doSaveAction()
	{
		var temp = {
			name : $('#name').val(),
			number : $('#number').val(),
			supplier: $('#supplier').val(),
			validYear: $('#validYear').val(),
			voltageLevel: $('#voltageLevel').val(),
			quantity: $('#quantity').val(),
			tradeType: $('#tradeType').val(),
			price: $('#price').val()
		};
		
		var msgTitle = '添加购电合约';
		if(g_curData != null)
		{
			msgTitle = '修改购电合约';
			temp.id = g_curData.id;
		}
		
    	var progress = showProgress('正在保存购电合约');
    	$.ajaxFileUpload({
			url: rootpath + '/' + PATH_GDHY + '/info',
			secureuri : false,
			type : 'POST',
			fileElementId : 'file',
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