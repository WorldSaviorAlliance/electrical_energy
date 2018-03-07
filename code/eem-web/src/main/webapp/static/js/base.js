/**
 * 定义基础的方法
 * @author Richard
 */

jQuery(window).load(function() {
   jQuery('#preloader').fadeOut(function(){
      jQuery('body').css({'overflow':'visible'});
   });
});

jQuery.extend(jQuery.validator.messages, {
	  required: "必选字段",
	  remote: "请修正该字段",
	  email: "请输入正确格式的电子邮件",
	  url: "请输入合法的网址",
	  date: "请输入合法的日期",
	  dateISO: "请输入合法的日期 (ISO).",
	  number: "请输入合法的数字",
	  digits: "只能输入整数",
	  creditcard: "请输入合法的信用卡号",
	  equalTo: "请再次输入相同的值",
	  accept: "请输入拥有合法后缀名的字符串",
	  maxlength: jQuery.validator.format("请输入一个长度最多是{0}的字符串"),
	  minlength: jQuery.validator.format("请输入一个长度最少是{0}的字符串"),
	  rangelength: jQuery.validator.format("请输入一个长度介于{0}和{1}之间的字符串"),
	  range: jQuery.validator.format("请输入一个介于{0}和{1}之间的值"),
	  max: jQuery.validator.format("请输入一个最大为{0}的值"),
	  min: jQuery.validator.format("请输入一个最小为{0}的值")
});

function getRootPath() {
	return location.pathname.substring(0, location.pathname.substr(1).indexOf('/') + 1);
}
var rootpath = getRootPath();
var urlRootPath = getRootPath() + '/';

var WINDOW_WIDTH = 900; //窗口的宽度
var WINDOW_HEIGHT = 700; //窗口的高度
var WINDOW_SUB_CONTENT_HEIGHT = 605; //窗口的内容的高度
var WINDOW_NO_BOTTOM_HEIGHT = WINDOW_HEIGHT - 45;;//没有底部按钮的高度
$(function(){
	getWindowSize();
	$(window).resize(function() {
		if(g_params != null)
		{
			getWindowSize();
		}		
	});
});

function findDimensionsWidth() 
{ 
	var winWidth = 0; 
	if (window.innerWidth) 
		winWidth = window.innerWidth; 
	else if ((document.body) && (document.body.clientWidth)) 
		winWidth = document.body.clientWidth; 
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) 
	{ 
		winWidth = document.documentElement.clientWidth; 
	} 
	return winWidth;
} 

function findDimensionsHeight() 
{ 
	var winHeight = 0; 
	if (window.innerHeight) 
		winHeight = window.innerHeight; 
	else if ((document.body) && (document.body.clientHeight)) 
		winHeight = document.body.clientHeight; 
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientHeight) 
	{ 
		winHeight = document.documentElement.clientHeight; 
	} 
	return winHeight;
}

function getWindowSize()
{
	var width = findDimensionsWidth();
	var height = findDimensionsHeight();
	if(width >= 1440)
	{
		WINDOW_WIDTH = 1200;
	}
	else if(width < 1440 && width >= 1000)
	{
		WINDOW_WIDTH = 900;
	}
	else
	{
		WINDOW_WIDTH = 700;
	}
	
	if(height >= 780)
	{
		WINDOW_HEIGHT = 700;
		WINDOW_SUB_CONTENT_HEIGHT = 605;
	}
	else if(height < 780 && height > 660)
	{
		WINDOW_HEIGHT = 640;
		WINDOW_SUB_CONTENT_HEIGHT = 545;
	}
	else
	{
		WINDOW_HEIGHT = 500;
		WINDOW_SUB_CONTENT_HEIGHT = 405;
	}
	
	WINDOW_NO_BOTTOM_HEIGHT = WINDOW_HEIGHT - 45;
}

var g_params = null;  //全局监控Url参数的变量
/**
 * 加载主界面内容
 * @param direct
 */
function loadContent(direct)
{
	g_params = direct;
	$('#content').css('min-height', findDimensionsHeight() - 80);
	$('#content').load(rootpath + '/static/jsp/' + direct.activeItem + '.jsp');
}

//重写window的alert和confirm
window.alert = function(msg, type)
{
	var tempType = (type != undefined) ? type : 0;
	if(typeof msg == "string" && msg.indexOf('成功') != -1)
	{
		tempType = 0;
	}
	else if(typeof msg == "string" && (msg.indexOf('失败') != -1 || msg.indexOf('错误') != -1 ))
	{
		tempType = 1;
	}
	else
	{
		tempType = 0;
	}
	$('body').EemMessageWindow('alert', {type: tempType, content: msg});
};

window.confirm = function(msg, okFn, okParams, cancelFn, cancelParams)
{
	$('body').EemMessageWindow('confirm', {content: msg, okFn: okFn, okParams: okParams, cancelFn : cancelFn, cancelParams : cancelParams});
};

function getAdBrowserDateTime(date)
{
	if(date == null)
	{
		return Date.parse(new Date());
	}
	else
	{
		return Date.parse(date.replace(/-/g,"/"));
	}	
}


/**
 * 显示进度
 * @param msg
 * @returns
 */
function showProgress(msg)
{
	var id = $.gritter.add({
        title:  STR_CONFIRM ,
        text: msg,
        image: rootpath + '/static/images/loading/loading_32.gif',
        sticky: true,
        class_name : 'gritter-light',
        class_name: 'my-sticky-class'
    });	
	return id;
}

/**
 * 隐藏进度
 * @param id
 */
function hideProgress(id)
{
	if($('#gritter-item-' + id).length == 0)
	{
		return;
	}
	$.gritter.remove(id, {
        fade: true,
        speed: 'slow'
    });
}

/**
 * @param title 
 * @param content
 * @param type
 */
function showDynamicMessage(title, content, type)
{
	setTimeout(function () {
		var image = '';
		switch(type)
		{
		case MESSAGE_TYPE_ERROR:
			image = rootpath + '/static/js/gritter/error.png';
			break;
		case MESSAGE_TYPE_INFO:
			image = rootpath + '/static/js/gritter/info.png';
			break;
		case MESSAGE_TYPE_QUESTION:
			image = rootpath + '/static/js/gritter/question.png';
			break;
		case MESSAGE_TYPE_WARNING:
			image = rootpath + '/static/js/gritter/warning.png';
			break;
		case MESSAGE_TYPE_LOADING :
			image = rootpath + '/static/images/loading/loading_32.gif';
			break;
		default :
			image = rootpath + '/static/js/gritter/info.png';
			break;
		}
		var unique_id = $.gritter.add({
	        title: title,
	        text: content,
	        image: image,
	        sticky: true,
	        time: '',
	        class_name : 'gritter-light',
	        class_name: 'my-sticky-class'
	    });
		setTimeout(function () {
	        $.gritter.remove(unique_id, {
	            fade: true,
	            speed: 'slow'
	        });
	    }, 10000);
	}, 100);
}

/**
 * 获得电源类型
 * @param powerType
 * @returns {String}
 */
function getPowerTypeStr(powerType)
{
	var str = '';
	if(powerType != null)
	{
		switch (powerType) {
		case 0:
			str = '火电';
			break;
		case 1:
			str = '水电';
			break;
		case 2:
			str = '风电';
			break;
		case 3:
			str = '核电';
			break;
		case 4:
			str = '其他';
			break;
		default:
			break;
		}
	}
	return str;
}

/*
 * 获得企业性质
 */
function getNatureType(natureType)
{
	var str = '';
	if(natureType != null)
	{
		switch (natureType) {
		case 0:
			str = '国营企业';
			break;
		case 1:
			str = '民营企业';
			break;
		}
	}
	return str;
}

var industryTypeArray = ['石油化工', '煤炭采选', '冶炼铸造', '机械制造', '汽车制造', '船舶制造', '水泥建材', '玻璃陶瓷', 
                         '纺织化纤', '精细化工', '电气设备', '电子电器', '食品饲料', '烟草加工', '酿酒行业', '医药行业', 
                         '通讯信息', '航天航空', '交运物流', '港口水运', '造纸印刷', '包装装饰', '民用商业', '医疗卫生', '其他行业'];
function getIndustryType(industryType)
{
	var str = '';
	if(industryType != null)
	{
		str = industryTypeArray[industryType];
	}
	return str;
}

/**
 * 获取行业下拉列表内容
 */
function getIndustryTypeSelectStr()
{
	var str = '';
	for(var i = 0; i < industryTypeArray.length; i++)
	{
		str += '<option value="' + i + '">' + industryTypeArray[i] + '</option>';
	}
	return str;
}


/**
 * 获取年份下拉列表数据
 */
function getYearSelectStr()
{
	var str = '<option></option>';
	for(var i = 0; i < 50; i++)
	{
		str += '<option value="' + (2018 + i) + '">' + (2018 + i) + '</option>';
	}
	return str;
}

var tradeType = ['常规直购电', '精准扶持直购电', '自备替代直购电'];
/**
 * 获取交易品种数据
 */
function getTradeTypeSelectStr()
{
	var str = '<option value="-1">--请选择交易品种--</option>';
	for(var i = 0; i < tradeType.length; i++)
	{
		str += '<option value="' + i + '">' + tradeType[i] + '</option>';
	}
	return str;
}


function gettradeType(type)
{
	return tradeType[type];
}

/**
 * 通过id获取对应的数据
 */
function getCurDataById(id, all_datas)
{
	var data = null;
	if(all_datas != null && all_datas.length != 0)
	{
		for(var i = 0; i < all_datas.length; i++)
		{
			if(id == all_datas[i].id)
			{
				data = all_datas[i];
				break;
			}
		}
	}
	return data;
}

/**
 * 获取对应的字符串
 * @param obj
 * @returns
 */
function getObjStr(obj)
{
	return obj == null ? '' : obj;
}

function showSystemError()
{
	showDynamicMessage(STR_CONFIRM, '系统错误，请联系管理员', MESSAGE_TYPE_ERROR);
}

/**
 * 获取所有的电力用户的下拉列表
 */
function getAllDlyhSelecte(contorlId, valId)
{
	$('#' + contorlId).empty();
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
					if(datas != null && datas.length != 0)
					{
						var opts = '<option value="-1">--请选择电力用户--</option>';
						for(var i = 0; i < datas.length; i++)
						{
							opts += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
						}
						$('#' + contorlId).append(opts);
						if(valId != null)
						{
							$('#' + contorlId).val(valId);
						}
					}
					$('#' + contorlId).niceSelect();
				}
			}
			else
			{
				showSystemError();
			}
		}
	});
}

/**
 * 获取所有的电源商的下拉列表
 */
function getAllDysSelecte(contorlId, valId)
{
	$('#' + contorlId).empty();
	$.ajax({
		url: rootpath + '/' + PATH_DYS + '/list?page=0&per_page=10000',
		type : 'POST', 
		dataType: 'json',
	    contentType: 'application/json',
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);
				if(ar.code == 0)
				{
					var datas = ar.data;
					if(datas != null && datas.length != 0)
					{
						var opts = '<option>--请选择电源商--</option>';
						for(var i = 0; i < datas.length; i++)
						{
							opts += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
						}
						$('#' + contorlId).append(opts);
						if(valId != null)
						{
							$('#' + contorlId).val(valId);
						}
					}
					$('#' + contorlId).niceSelect();
				}
			}
			else
			{
				showSystemError();
			}
		}
	});
}


/**
 * 获取所有的电压等级的下拉列表
 */
function getAllDydjSelecte(contorlId, valId)
{
	$('#' + contorlId).empty();
	var search = {
			'startPage' : FIRST_PAGE,
			'perPageCnt' : MAX_COUNT
		};
	$.ajax({
		url: rootpath + '/' + PATH_DYDJ + '/list',
		type : 'POST', 
		dataType: 'json',
		data : JSON.stringify(search),
	    contentType: 'application/json',
		complete : function(XHR, TS) {
			if (TS == "success") {
				var ar = JSON.parse(XHR.responseText);
				if(ar.code == 0)
				{
					var datas = ar.data;
					if(datas != null && datas.length != 0)
					{
						var opts = '<option>--请选择电压等级--</option>';
						for(var i = 0; i < datas.length; i++)
						{
							opts += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
						}
						$('#' + contorlId).append(opts);
						if(valId != null)
						{
							$('#' + contorlId).val(valId);
						}
					}
					$('#' + contorlId).niceSelect();
				}
			}
			else
			{
				showSystemError();
			}
		}
	});
}


/**
 * 获得所有页数
 * @param allCount
 * @returns
 */
function getTotalPage(allCount)
{
	return allCount / PAGE_COUNT + (allCount % PAGE_COUNT != 0 ? 1 : 0);
}

