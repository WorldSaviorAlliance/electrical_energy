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
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						getCustomer(ar.data.customerId);
					}
				}
			}
		});
	}

	function getCustomer(customerId)
	{
		$.ajax({
			url: rootpath + '/' + PATH_DLYH + '/info?id=' + customerId,
			type : 'GET',
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						var data = ar.data;
						$('#name').html(data.name);
						$('#nickName').html(data.nickName);
						$('#city').html(data.province + '-' + data.city);
						$('#address').html(data.address);
						$('#natureType').html(getNatureType(data.natureType));
						$('#industryType').html(getIndustryType(data.industryType));
						$('#contactName').html(data.contactName);
						$('#contactPhone').html(data.contactPhone);
						$('#contactPosition').html(data.contactPosition);
						$('#contactEmail').html(data.contactEmail);
						$('#fax').html(data.fax);
					}
				}
			}
		});
	}
});