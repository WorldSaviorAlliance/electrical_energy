var max_z_index = 2147483647 - 1000;  	//记录当前最底层的index
var allZIndexCount = 100; 				//目前允许层数为100。
;(function($) {	
	function getZIndex()
	{
		var zIndex = max_z_index - allZIndexCount;
		allZIndexCount--;
		return zIndex;
	}
	/**
	 * 添加具体内容
	 */
	function addContent(options)
	{
		var detail_id = getAdBrowserDateTime();
		var detail = $('<div style="z-index:' + getZIndex() + ';" class="eem_window_detail" id="' + detail_id + '"></div>');
		$(this).attr('detail_id', detail_id);
		options.detail = detail;
		var close = $('<div class="eem_window_close" title="关闭"></div>');
		var title = $('<div class="eem_window_title"></div>');
		var content = $('<div class="eem_window_content"></div>');
		
		var btns = $('<div class="eem_window_button_container"></div>');
		detail.html('');
		if(options.hasCloseBtn)
		{
			detail.append(close);
		}
		detail.append(title);		
		detail.append(content);
		if(options.hasBottomBtn)
		{
			var okBtn = $('<input type="button" flag="win_ok_btn" value="' + options.okBtnStr + '" class="btn btn-primary btn-sm" style="margin: 10px 10px 0px 0px;" title="' + options.okBtnStr + '">');
			var cancelBtn = $('<input type="button" flag="win_cancel_btn" value="' + options.cancelBtnStr + '" class="btn btn-default btn-sm" style="margin: 10px 10px 0px 0px;" title="' + options.cancelBtnStr + '">');
			okBtn.click(function(){
				if(options.onOkBtnFn(options.okFnParams)){
					options.onClosed();
				}
			});
			cancelBtn.click(function(){
				options.onCancelBtnFn(options.cancelFnParams);
				options.onClosed();
			});
			
			detail.append(btns);
			if(options.showCancelBtn)
			{
				btns.append(cancelBtn);
				$(cancelBtn).focus();
			}
			else
			{
				if(options.showOkBtn)
    			{
					$(okBtn).focus();
    			}
			}
			if(options.showOkBtn)
			{
				btns.append(okBtn);
			}			
            content.css("height", options.height - 90);
		}
		else
		{
            content.css("height", options.height - 45);
		}
	
		$(document).unbind('keydown');
		$(document).keydown(function(event){
			if(event.which == 13 && options.hasBottomBtn){
				$('input').blur();
				if(options.onOkBtnFn(options.okFnParams)){
					options.onClosed();
				}
			}
			if(event.which == 27 && options.autoHide)
			{
				options.onClosed();
			}
		});
        title.html(options.title);
        content.html(options.content);
		
		detail.css("display","block"); 
        detail.css("position","absolute");
        detail.css("left", (($(window).get(0).innerWidth - options.width) / 2 + $(window).scrollLeft()) +"px");
        detail.css("top", (($(window).get(0).innerHeight - options.height) / 2+ + $(window).scrollTop()) +"px");
        detail.css("width",options.width);
        detail.css("height",options.height);
		detail.css('background-color', '#FFFFFF');
		detail.css('box-shadow', '0 4px 23px 5px rgba(0, 0, 0, 0.2), 0 2px 6px rgba(0,0,0,0.15)');
		detail.css('border-radius', '3px');
		$('body').append(detail); 
        close.bind("click",function(){			
        	options.onClosed();
        });
		
		setTimeout(function () {
			options.afterShow();
		}, 50);
		
		$('div.eem_window_bg').unbind('click').click(function(){
			var $detail = $('div.eem_window_detail');
			for(var i = 0; i < $detail.length; i++)
			{
				var temp = $detail.get(i); 
				if($(temp).is(':visible'))
				{
					var left = ($(window).get(0).innerWidth - $(temp).width()) / 2 + $(window).scrollLeft();
					$(temp).animate({left: (left + 10) + 'px'}, 50);
					$(temp).animate({left: left + 'px'}, 50);
					$(temp).animate({left: (left + 10) + 'px'}, 50);
					$(temp).animate({left: left + 'px'}, 50);
				}
			}
		});
	}
	var methods = {
	        init: function (options) {
	    		var defaults = {
	    			 width:400,
	                 height:200,
	    			 hasCloseBtn : true, //是否含有右上角关闭按钮
	    			 hasBottomBtn : true,
	    			 showOkBtn : true,
	    			 showCancelBtn : true,
	    			 autoHide : true,  //按Esc退出
	    			 onClosed:function(){//关闭时执行的动作
	    				if($('.eem_window_detail') != null && $('.eem_window_detail').length > 1)
	    				{
	    	    			$('.eem_window_detail').slideDown(300);
	    				}
	    				else 
	    				{
	    					if(options.bg != null)
		    				{
		    					options.bg.remove();
		    				}
	    				}
	    				$('div.eem_tip').hide();
	    				options.detail.remove();
	    				$(document).unbind('keydown');
	    			 },
	    			 onOkBtnFn : function(){return true;},
	    			 okFnParams : null,
	    			 cancelFnParams : null,
	    			 onCancelBtnFn : function(){options.onClosed();},
	    			 okBtnStr : '确&nbsp;&nbsp;&nbsp;定',
	    			 cancelBtnStr : '取&nbsp;&nbsp;&nbsp;消',
	    			 afterShow : function(){},
	    			 bg : null,
	    			 detail : null
	    		};
	    		var options = $.extend(defaults, options);
	    		var EemWindow = $(this);
	    		if($('.eem_window_bg') == null || $('.eem_window_bg').length == 0)
	    		{
	    			var bg = $('<div class="eem_window_bg" style="z-index:' + getZIndex() + ';"></div>');
		    		bg.css("width",$(window).width());
		            bg.css("height",$(window).height());
		            options.bg = bg;
		            $('body').append(bg);
		            addContent(options);
	    		}
	    		else
	    		{
	    			if($('.eem_window_detail').length == 0)
	    			{
	    				addContent(options);
	    			}
	    			else
	    			{
	    				$('.eem_window_detail').slideUp(100, function(){addContent(options);});
	    			}
	    		}
	    		$(window).resize(function(){
	    			if(options.bg != null)
	    			{
	    				options.bg.css("width",$(window).width());
	    				options.bg.css("height",$(window).height());
	    			}
	    			
	    			options.detail.css("left", (($(window).get(0).innerWidth - options.width) / 2 + $(window).scrollLeft()) +"px");
	    			options.detail.css("top", (($(window).get(0).innerHeight - options.height) / 2+ + $(window).scrollTop()) +"px");
	    		});
	    		EemWindow.bg = options.bg;
	    		EemWindow.detail = options.detail;
	    		return EemWindow;
	        },
	        destory: function () {
	        	var $EemWindow = this;
	        	document.body.style.overflow = 'auto';
	        	if($EemWindow.bg != null)
	        	{
	        		$EemWindow.bg.remove();
	        	}
	        	$EemWindow.detail.remove();
	        }
	    };
	
	$.fn.EemWindow = function(method){
		if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.EemWindow');
        }
	};
})(jQuery);
