/**
 * 针对合约里面的电量和电价的table控件 
 */

/**
 * @param dlTableId 电量表格id
 * @param djTableId 电价表格id
 * @param hyData	合约的详细信息
 */
function Month2DL(dlTableId, djTableId, hyData)
{
	var titleData = [{obj : 'january', name : '一月'}, {obj : 'february', name : '二月'}, {obj : 'march', name : '三月'}, {obj : 'april', name : '四月'} ,
	                 {obj : 'may', name : '五月'}, {obj : 'june', name : '六月'}, {obj : 'july', name : '七月'}, {obj : 'august', name : '八月'},
	                 {obj : 'september', name : '九月'}, {obj : 'october', name : '十月'}, {obj : 'november', name : '十一月'}, {obj : 'december', name : '十二月'}];

	initTable();

	/**
	 * 构造对应的数据表格
	 */
	function initTable()
	{
		var dlStr = '';
		var djStr = '';
		for(var i = 0; i < titleData.length; i++)
		{
			console.log(getData(i));
			dlStr += '<tr>'+
						'<td>'+
							titleData[i].name+
						'</td>'+
						'<td>' +
							'<span flag="total" month="' + titleData[i].obj + '"></span>'+
						'</td>'+
						'<td>'+
							'<input type="number" class="form-control" flag="ntp" month="' + titleData[i].obj + '"/>'+
						'</td>'+
						'<td>'+
							'<input type="number" class="form-control" flag="stp" month="' + titleData[i].obj + '"/>'+
						'</td>'+
						'<td>'+
							'<input type="number" class="form-control" flag="rtp" month="' + titleData[i].obj + '"/>'+
						'</td>'+
						'<td>'+
							'<input type="number" class="form-control" flag="mtp" month="' + titleData[i].obj + '"/>'+
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
	}
	
	
	/**
	 * 获取对应的电量数据
	 */
	this.getMonthData = function getMonthData()
	{
		var data = {};
		for(var i = 0; i < titleData.length; i++)
		{
			var ntp = $('input[flag="ntp"][month="' + titleData[i].obj + '"]');
			var stp = $('input[flag="stp"][month="' + titleData[i].obj + '"]');
			var rtp = $('input[flag="rtp"][month="' + titleData[i].obj + '"]');
			var mtp = $('input[flag="mtp"][month="' + titleData[i].obj + '"]');
			
			data[titleData[i].obj] = '';
		}
		return data;
	};
	
	/**
	 * 得到对应的电量的数据
	 */
	function getData(index)
	{
		var data = null;
		if(hyData != null && hyData.monthData != null)
		{
			data = hyData.monthData[titleData[index].obj];
		}
		return data;
	}
	
	return this;
}