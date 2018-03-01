$(function(){
	$('#username').focus();
	
	$('#loginBtn').click(function(){
		doLoginAction();
	});
	$('input').keyup(function (event) {
		if (event.keyCode == "13") {
			$("#loginBtn").click();
			return false;	
		}
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
});