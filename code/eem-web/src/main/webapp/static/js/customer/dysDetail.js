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
		$("#detail_form").validate({
		    highlight: function(element) {
		      $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		    },
		    success: function(element) {
		      $(element).closest('.form-group').removeClass('has-error');
		    }
		});
		
		$('.select').niceSelect();
	}
	
	
	return this;
}