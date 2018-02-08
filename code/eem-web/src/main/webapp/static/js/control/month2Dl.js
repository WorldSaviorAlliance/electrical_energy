/**
 * 针对合约里面的电量和电价的table控件 
 */
//@ sourceURL=month2Dl.js
/**
 * @param dlTableId 电量表格id
 * @param djTableId 电价表格id
 * @param hyData	合约的详细信息
 * 
 */
function Month2DL(dlTableId, djTableId, ntpId, stpId, rtpId, mtpId, hyData)
{
	var g_hyData = hyData;
	var g_dlTableId = dlTableId;
	var g_djTableId = djTableId;
	var g_ntpId = ntpId;
	var g_stpId = stpId;
	var g_rtpId = rtpId;
	var g_mtpId = mtpId;
	var titleData = [{obj : 'january', name : '一月'}, {obj : 'february', name : '二月'}, {obj : 'march', name : '三月'}, {obj : 'april', name : '四月'} ,
	                 {obj : 'may', name : '五月'}, {obj : 'june', name : '六月'}, {obj : 'july', name : '七月'}, {obj : 'august', name : '八月'},
	                 {obj : 'september', name : '九月'}, {obj : 'october', name : '十月'}, {obj : 'november', name : '十一月'}, {obj : 'december', name : '十二月'}];

	initTable();

	/**
	 * 构造对应的数据表格
	 */
	function initTable()
	{
		$('#' + dlTableId).empty();
		$('#' + djTableId).empty();
		var dlStr = '';
		var djStr = '';
		
		for(var i = 0; i < titleData.length; i++)
		{
			var curData = getData(i);
			var total = 0;
			var ntp = '';
			var stp = '';
			var rtp = '';
			var mtp = '';
			if(curData != null)
			{
				var temp = curData.substring(1, curData.length - 1).split(':');
				ntp = parseInt(temp[0]);
				stp = parseInt(temp[1]);
				rtp = parseInt(temp[2]);
				mtp = parseInt(temp[3]);
				total = ntp + stp + rtp + mtp;
			}
			dlStr += '<tr>'+
						'<td>'+
							titleData[i].name+
						'</td>'+
						'<td>' +
							'<span flag="total" month="' + titleData[i].obj + '">' + total + '万kWh</span>'+
						'</td>'+
						'<td>'+
							'<div class="form-group">'+
								'<div class="col-sm-8">'+
									'<input type="number" class="form-control" flag="ntp" month="' + titleData[i].obj + '" value="' + ntp + '"/>'+
								'</div>'+
								'<label class="col-sm-4 control-label" style="text-align: left;">'+
									'万kWh'+
								'</label>'+
							'</div>'+
						'</td>'+
						'<td>'+
							
							'<div class="form-group">'+
								'<div class="col-sm-8">'+
								'<input type="number" flag="stp" class="form-control" month="' + titleData[i].obj + '" value="' + stp + '"/>'+
								'</div>'+
								'<label class="col-sm-4 control-label" style="text-align: left;">'+
									'万kWh'+
								'</label>'+
							'</div>'+
						'</td>'+
						'<td>'+
							'<div class="form-group">'+
								'<div class="col-sm-8">'+
									'<input type="number" flag="rtp" class="form-control" month="' + titleData[i].obj + '" value="' + rtp + '"/>'+
								'</div>'+
								'<label class="col-sm-4 control-label" style="text-align: left;">'+
									'万kWh'+
								'</label>'+
							'</div>'+
						'</td>'+
						'<td>'+
							'<div class="form-group">'+
								'<div class="col-sm-8">'+
									'<input type="number" flag="mtp" class="form-control" month="' + titleData[i].obj + '" value="' + mtp + '"/>'+
								'</div>'+
								'<label class="col-sm-4 control-label" style="text-align: left;">'+
									'万kWh'+
								'</label>'+
							'</div>'+
						'</td>'+
					'</tr>';
			djStr += '<tr>'+
						'<td>'+
							titleData[i].name+
						'</td>'+
						'<td>'+
							'<span flag="ntp" month="' + titleData[i].obj + '"></span>'+
						'</td>'+
						'<td>'+
							'<span flag="stp" month="' + titleData[i].obj + '"></span>'+
						'</td>'+
						'<td>'+
							'<span flag="rtp" month="' + titleData[i].obj + '"></span>'+
						'</td>'+
						'<td>'+
							'<span flag="mtp" month="' + titleData[i].obj + '"></span>'+
						'</td>'+
					'</tr>';
		}
		$('#' + dlTableId).append(dlStr);
		$('#' + djTableId).append(djStr);
		
		initControlAction();
		initPriceTable();
	}
	
	function initPriceTable()
	{
		var p_ntp = $('#' + g_ntpId).val();
		var p_stp = $('#' + g_stpId).val();
		var p_rtp = $('#' + g_rtpId).val();
		var p_mtp = $('#' + g_mtpId).val();
		
		for(var i = 0; i < titleData.length; i++)
		{
			var tempInput = $('input[month="' + titleData[i].obj + '"]');
			var total = 0;
			for(var j = 0; j < tempInput.length; j++)
			{
				if($(tempInput.get(j)).val() != '')
				{
					total += parseInt($(tempInput.get(j)).val());
				}
			}
			
			$('span[flag="total"][month="' + titleData[i].obj + '"]').html(total + '万kWh');
		}
		
		var $allInput = $('#' + dlTableId + ' input');
		for(var i = 0; i < $allInput.length; i++)
		{
			var flag = $($allInput.get(i)).attr('flag');
			var month = $($allInput.get(i)).attr('month');
			var price = 0;
			if($($allInput.get(i)).val() != '')
			{
				if(flag == 'ntp')
				{
					price = parseInt(p_ntp) * parseInt($($allInput.get(i)).val());
				}
				else if(flag == 'stp')
				{
					price = parseInt(p_stp) * parseInt($($allInput.get(i)).val());
				}
				else if(flag == 'rtp')
				{
					price = parseInt(p_rtp) * parseInt($($allInput.get(i)).val());
				}
				else if(flag == 'mtp')
				{
					price = parseInt(p_mtp) * parseInt($($allInput.get(i)).val());
				}
			}
			$('span[flag="' + flag + '"][month="' + month + '"]').html(price);
		}
	}
	
	function initControlAction()
	{
		$('#' + dlTableId + ' input').keyup(function(){
			initPriceTable();
		});
	}
	
	/**
	 * 获取对应的电量数据
	 */
	this.getMonthData = function getMonthData()
	{
		var data = {};
		for(var i = 0; i < titleData.length; i++)
		{
			var ntp = $('input[flag="ntp"][month="' + titleData[i].obj + '"]').val();
			var stp = $('input[flag="stp"][month="' + titleData[i].obj + '"]').val();
			var rtp = $('input[flag="rtp"][month="' + titleData[i].obj + '"]').val();
			var mtp = $('input[flag="mtp"][month="' + titleData[i].obj + '"]').val();
			
			data[titleData[i].obj] = '(' + ntp + ':' + stp + ':' + rtp + ':' + mtp + ')';
		}
		if(g_hyData != null)
		{
			data.id = g_hyData.monthData.id;
		}
		return data;
	};
	
	this.validate = function validate()
	{
		var monthdataInput = $('#' + g_dlTableId + ' input');
		for(var i = 0; i < monthdataInput.length; i++)
		{
			var temp = monthdataInput.get(i);
			if($(temp).val() == '')
			{
				return false;
			}
		}
		return true;
	};
	
	this.updateTable = function updateTable(hyData)
	{
		g_hyData = hyData;
		initTable();
	};
	
	/**
	 * 得到对应的电量的数据
	 */
	function getData(index)
	{
		var data = null;
		if(g_hyData != null && g_hyData.monthData != null)
		{
			data = g_hyData.monthData[titleData[index].obj];
		}
		return data;
	}
	
	return this;
}