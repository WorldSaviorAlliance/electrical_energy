//@ sourceURL=dysDetail.js
function DysDetail()
{
	$("#basicForm").validate({
	    highlight: function(element) {
	      $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	    },
	    success: function(element) {
	      $(element).closest('.form-group').removeClass('has-error');
	    }
	});
}