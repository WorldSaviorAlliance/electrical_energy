/**
 * 重构alert和confirm
 */
;(function($, window, document, undefined){
	/**
	 * 构造数据
	 */
	function constructContent(type, msg)
	{       
		var content = '<div style="margin-top:50px;"><img style="vertical-align:middle;margin-left: 20px;float:left;" src="';
		switch(type)
		{
		case 0: //info
			content += rootpath + '/static/images/window/info.png">';
			break;
		case 1: //error
			content += rootpath + '/static/images/window/error.png">';
			break;
		case 2: //warning
			content += rootpath + '/static/images/window/warning.png">';
			break;
		case 3: //question
			content += rootpath + '/static/images/window/question.png">';
			break;
		}
		content += '<div style="float:left;margin-top:20px;width:300px;"><span style="font-size:14px;display:inline-block;margin-left:10px;">' + msg + '</span><div>';
		return content;
	}
	
	function eemAlert(options)
	{
		var settings = $.extend({}, {
			'type' : 0,
			'content' : '',
			'title' : '温馨提示',
			'showOkBtn' : true,
			'showCancelBtn' : false
			}, options);
		
		var content = $(constructContent(settings.type, settings.content));
		$(this).EemWindow({
			height : 260,
			width : 400,
            title: settings.title,
            showOkBtn : settings.showOkBtn,
			showCancelBtn : settings.showCancelBtn,
            content: content
        });
	}
	
	function eemConfirm(options)
	{
		var settings = $.extend({}, {
			'type' : 3,
			'content' : '',
			'title' : '温馨提示',
			'okFn': function(){return true;},
			'cancelFn' : function(){return true;}
			}, options);
		
		var content = $(constructContent(settings.type, settings.content));
		$(this).EemWindow({
			height : 260,
			width : 400,
            title: settings.title,
            content: content,
            onOkBtnFn : settings.okFn,
            okFnParams : settings.okParams,
            onCancelBtnFn : settings.cancelFn,
            cancelFnParams : settings.cancelParams
        });
	}
	
	var methods = {
        alert : function(options){
        	return eemAlert(options);
        },
        confirm : function(options){
        	return eemConfirm(options);
        }
    };
			
    $.fn.EemMessageWindow = function(method) {		
		if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.EemMessageWindow');
        }		      
    };
})(jQuery, window, document);
