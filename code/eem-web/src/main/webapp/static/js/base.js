/**
 * 定义基础的方法
 * @author Richard
 */

jQuery(window).load(function() {
   jQuery('#preloader').fadeOut(function(){
      jQuery('body').css({'overflow':'visible'});
   });
});

function getRootPath() {
	return location.pathname.substring(0, location.pathname.substr(1).indexOf('/') + 1);
}
var rootpath = getRootPath();
var urlRootPath = getRootPath() + '/';

var WINDOW_WIDTH = 900; //窗口的宽度
var WINDOW_HEIGHT = 700; //窗口的高度
var WINDOW_SUB_CONTENT_HEIGHT = 605; //窗口的内容的高度
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
	if(typeof msg == "string" && msg.indexOf(STR_SUC) != -1)
	{
		tempType = 0;
	}
	else if(typeof msg == "string" && (msg.indexOf(STR_FAILE) != -1 || msg.indexOf(STR_ERROR) != -1 ))
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