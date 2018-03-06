/**
 * 
 */
$(function(){
	getCurUser();
	
	function getCurUser()
	{
		$.ajax({
			url: rootpath + '/' + PATH_USER + '/info?id=' + g_params.param,
			type : 'GET', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);console.log(ar);
				}
				else
				{
				}
			}
		});
	}
	
	getCustomer(customerId)
	{
		
	}
});