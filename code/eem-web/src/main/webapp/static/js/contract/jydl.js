/**
 * 交易电价
 */
//@ sourceURL=jydl.js
function Jydl(customerId, price)
{
	var g_customer_id = customerId;
	var g_price = price;
	var g_all_dlyh = null;
	init();
	function init()
	{
		initControlAction();
	}
	
	function initControlAction()
	{
		$.ajax({
			url: rootpath + '/' + PATH_DLYH + '/list?page=0&per_page=10000',
			type : 'POST', 
			dataType: 'json',
		    contentType: 'application/json',
			complete : function(XHR, TS) {
				if (TS == "success") {
					var ar = JSON.parse(XHR.responseText);
					if(ar.code == 0)
					{
						var datas = ar.data;
						g_all_dlyh = datas;
						if(datas != null && datas.length != 0)
						{
							var opts = '<option></option>';
							for(var i = 0; i < datas.length; i++)
							{
								opts += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
							}
							$('#all_dlyh').append(opts);
							if(g_customer_id != null)
							{
								$('#all_dlyh').val(g_customer_id);
							}
						}
						$('#all_dlyh').niceSelect();
					}
				}
				else
				{
					showSystemError();
				}
			}
		});
	}
	
	this.getJydlData = function getJydlData()
	{
		var temp = {};
		var all_dlyh = $('#all_dlyh').val();
		var jy_price = $('#jy_price').val();
		if(g_all_dlyh != null && g_all_dlyh.length != 0)
		{
			for(var i = 0; i < g_all_dlyh.length; i++)
			{
				if(g_all_dlyh[i].id == all_dlyh)
				{
					temp.name = g_all_dlyh[i].name;
					temp.customerId = g_all_dlyh[i].id;
					temp.price = jy_price;
					break;
				}	
			}
		}
		return temp;
	};
	
	this.validate = function validate()
	{
		var all_dlyh = $('#all_dlyh').val();
		var jy_price = $('#jy_price').val();
		if(all_dlyh == null || all_dlyh == '')
		{
			$('#jy_error').html('请选择电力用户');
			$('#jy_error').show();
			return false;
		}
		
		if(jy_price == '')
		{
			$('#jy_error').html('请输入交易电量');
			$('#jy_error').show();
			return false;
		}
		if($('#jy_datas tr[type="data"][customerId="' + all_dlyh + '"]').length != 0)
		{
			$('#jy_error').html('当前电力用户的交易电量已存在，请重新选择');
			$('#jy_error').show();
			return false;
		}
		$('#jy_error').hide();
		return true;
	};
	return this;
}