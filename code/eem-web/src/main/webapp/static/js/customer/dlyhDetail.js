//@ sourceURL=dlyhDetail.js
function DlyhDetail()
{
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
		    	$('div.eem_window_close').click();
		    	showProgress('测试提交进度');
		    	showDynamicMessage('标题', '内容', 1);
		    	return false;
		    }
		});
		
		$('.select').niceSelect();
	}
	
	return this;
}