//@ sourceURL=dysDetail.js
function DysDetail()
{
	init();
	function init()
	{
		initControlAction();
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
		    	doSaveAction();
		    	return false;
		    }
		});
		
		$('#cancel').unbind('click').click(function(){
			$('div.eem_window_close').click();
		});
		$('.select').niceSelect();
	}
	
	function doSaveAction()
	{
		var name = $('#name').val();
		var nickName = $('#nickName').val();
		var powerType = $('#powerType').val();
		var capacity = $('#capacity').val();
		var province = $('#province').val();
		var city = $('#city').val();
		var address = $('#address').val();
		var natureType = $('#natureType').val();
		var contactName = $('#contactName').val();
		var contactPhone = $('#contactPhone').val();
		var contactPosition = $('#contactPosition').val();
		var contactEmail = $('#contactEmail').val();
		var fax = $('#fax').val();

		var temp = {
			name : name,
			nickName : nickName,
			powerType : powerType, 
			capacity : capacity,
			province : province,
			city : city,
			address : address,
			natureType : natureType,
			contactName : contactName,
			contactPhone : contactPhone,
			contactPosition : contactPosition,
			contactEmail : contactEmail,
			fax : fax
		};
		
		$('div.eem_window_close').click();
    	var progress = showProgress('正在保存电源商');
    	
		$.ajax({
			url: rootpath + '/' + PATH_DYS + '/info',
			type : 'POST',
			dataType: 'json',
		    contentType: 'application/json',
			data : JSON.stringify(temp),
			complete : function(XHR, TS) {
				hideProgress(progress);
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					console.log(ar);
					if(ar.code == 0)
					{
						showDynamicMessage(STR_CONFIRM, '保存电源商信息成功', MESSAGE_TYPE_INFO)
					}
					else
					{
						showDynamicMessage(STR_CONFIRM, '保存电源商信息失败:' + ar.msg, MESSAGE_TYPE_ERROR)
					}
				}
			}
		});
	}
	
	return this;
}