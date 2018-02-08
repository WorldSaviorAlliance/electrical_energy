$(function(){
	$('#username').focus();
	
	$('#loginBtn').click(function(){
		doLoginAction();
	});
	
	function doLoginAction()
	{
		var username = $('#username').val();
		var password = $('#password').val();
		
		if(username == null || username == '')
		{
			$('#error_msg').html('用户名不能为空');
			$('#error_msg').show();
			return;
		}
		
		if(password == null || password == '')
		{
			$('#error_msg').html('密码不能为空');
			$('#error_msg').show();
			return;
		}
		
		$('#error_msg').hide();
		$('#loginBtn').fadeOut(function(){
			$('#wait_msg').show();
			$('#password').attr("disabled",true);
			$('#username').attr("disabled",true);
			
			var param = {
				userName : username,
				password : password,
			};
			
			$.ajax({
				url: rootpath + '/logon',
				type : 'POST', 
				dataType: 'json',
				data : JSON.stringify(param),
			    contentType: 'application/json',
				complete : function(XHR, TS) {
					if (TS == "success") {
						var ar = JSON.parse(XHR.responseText);
						if (ar.code == 0)
						{
							location.replace(rootpath + '/home');
							return;
						}
						else
						{
							$('#error_msg').html("登录失败：" + ar.msg);
						}
					}
					
					$('#password').attr("disabled",false);
					$('#username').attr("disabled",false);
					$('#error_msg').show();
					$('#loginBtn').fadeIn();
					$('#wait_msg').hide();
				}
			});
		});
	}
	
	/*var temp = {
			"name": "客户",
	        "nickName": "客户1",
	        "province": "四川",
	        "city": "城市",
	        "address": "地址1",
	        "natureType": 1,
	        "contactName": "ganbin",
	        "contactPhone": "13679085415",
	        "contactPosition": "经理",
	        "contactEmail": "8716@qq.com",
	        "fax": "2222222"
			};
	$.ajax({
		url: rootpath + '/power_customer/info',
		type : 'POST',
		dataType: 'json',
	    contentType: 'application/json',
		data : JSON.stringify(temp),
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);console.log(ar);
			}
		}
	});
	
	var search = {
			"name": "客户",
	        "city": "城市",
	        "province": "四川"
			};
	$.ajax({
		url: rootpath + '/power_customer/list?page=0&per_page=10',
		type : 'POST',
		dataType: 'json',
		data : JSON.stringify(search),
	    contentType: 'application/json',
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);console.log(ar);
			}
		}
	});*/
});