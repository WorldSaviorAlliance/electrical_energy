//@ sourceURL=gdhyDetail.js
function GdhyDetail(afterSaveCallbk, curData)
{
	var g_afterSaveCallbk = afterSaveCallbk;
	var g_curData = curData;
	var g_page_jydl = null; //交易电价的界面
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
		            onOkBtnFn : function(){
		            	if(!g_page_jydl.validate())
		            	{
		            		return false;
		            	}
		            	addJydl2Table();
		            	return true;
		            },
		            afterShow : function(){
		            	g_page_jydl = new Jydl();
		            }
		        });	
			});
		});
	}
	
	/**
	 * 添加新的交易电量到表格中
	 */
	function addJydl2Table()
	{
		$('#jy_datas tr[type="empty_msg"]').hide();
		var data = g_page_jydl.getJydlData();
		if(data.id == null)//新增一行
		{
			var tr = '<tr type="data" customerId="' + data.customerId + '" price="' + data.price + '">' +
						 '<td>'+
						 	data.name+
						 '</td>'+
						 '<td flag="price">'+
						 	data.price + '万kWh' +
						 '</td>'+
						 '<td>'+
						 	'<a class="btn btn-primary btn-xs" style="margin-right: 20px;" flag="jydl_modify" customerId="' + data.id + '" price="' + data.price + '">修改</a>'+
							'<a class="btn btn-danger btn-xs" flag="jydl_del">删除</a>'+
						 '</td>'+
					  '</tr>';
			$('#jy_datas').append(tr);
		}
		else //更新对应行
		{
			$('#jy_datas tr[type="data"][customerId="' + data.id + '"]').attr('price', data.price);
			$('#jy_datas tr[type="data"][customerId="' + data.id + '"] td[flag="price"]').html(data.price + '万kWh');			
		}
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
		
		var infos = '';
		var djtrs = $('#jy_datas tr[type="data"]');
		if(djtrs.length != 0)
		{
			var tempdj = [];
			for(var i = 0; i < djtrs.length; i++)
			{
				var $t = $(djtrs.get(i));
				tempdj.push({'powerUserId' : parseInt($t.attr('customerId')), 'quantity' : parseInt($t.attr('price'))});
			}
			infos = JSON.stringify(tempdj);
		}
		temp.infos = infos;
		
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