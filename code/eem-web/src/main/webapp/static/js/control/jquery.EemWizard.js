(function ($, undefined) {
	$.fn.EemWizard = function(options)
	{
		var CHANGE_TYPE_PRE = -1;
		var CHANGE_TYPE_NEXT = 1;
		var defaultOpts = {
			onStepChanging : function(){return true;},  	//正在切换函数
			onStepChanged : null,   						//切换后的函数
			onFinishing : null,								//结束的函数
			onBeforeFinish : null,							//结束之前的函数
			onCancel : function(){doCancel();},				//取消函数
			onBeforeCancel : null,							//取消之后的函数
			contentHeight : 500,							//内容的高度：这里要通过外层的window的高度去计算
			afterInitControl : null,						//初始化完成后，执行的操作
			subFinish : null,								//子页面的完成操作
			subFinishParams : null,							//子页面的完成操作的参数
			subCancel : null,								//子页面的取消操作
			hasTitle : true,
			preBtnStr : '上一步',
			cancelBtnStr : '取消' ,
			nextBtnStr : '下一步',
			finishBtnStr : '完成'
		};
		var showTime = 500;
		options = $.extend(defaultOpts, options);
		var curIndex = -1;									//当前所在页面的索引值
		var unitWidth = 208;//每个单元的宽度
		var titleContainer = null;
		var cancelBtn = $('<input type="button" value="' + options.cancelBtnStr + '" title="' + options.cancelBtnStr + '" class="btn" flag="wizard_cancel">');
		var preBtn = $('<input type="button" value="' + options.preBtnStr + '" title="' + options.preBtnStr + '" class="btn blue"  style="display:none;" flag="wizard_pre">');
		var nextBtn = $('<input type="button" value="' + options.nextBtnStr + '" title="' + options.nextBtnStr + '" class="btn blue" flag="wizard_next">');
		var finishBtn = $('<input type="button" value="' + options.finishBtnStr + '" title="' + options.finishBtnStr + '" class="btn blue"  style="display:none;" flag="wizard_finish">');
		var content = $('<div flag="wz_content"></div>');						//当前主流程界面
		var detail;
		var allStep = -1;
		$EemWizard = this;
		$EemWizard.addClass('eemwizard-steps');
		initControl();
		
		/**
		 * 初始化
		 */
		function initControl()
		{
			if(options.items == null || options.items.length == 0)
			{
				return;
			}
			
			curIndex = 0;
			addTitle();
			$EemWizard.append(content);			
			addContent();
			
			setTimeout(function(){
				var closeBtn = $('div.eem_window_close');			
				closeBtn.unbind('click').bind('click', function(){
					if(g_subContentIndex <= 0)
					{
						options.onCancel();
					}
					else
					{
						closeSubContent(g_subContentIndex - 1);	
					}				
				});
			}, 1000);
		}
		
		function addOther()
		{
			addButton();
			setTimeout(function(){
				if(options.afterInitControl != null)
				{
					options.afterInitControl();
				}	
			}, 100);			
		}
		
		/**
		 * 添加内容
		 * 先加载首个，然后切换的时候再加载下一个
		 */
		function addContent()
		{
			var width = options.items.length * $EemWizard.width();
			var height = options.contentHeight + (WINDOW_HEIGHT - 700);
			if(!options.hasTitle)
			{
				height += 95;
			}
			detail = $('<ul flag="wz_detail" style="padding:0px;overflow: hidden;list-style: none outside none;width:' + width + 'px;""></ul>');
			var tempContent = $('<li class="content" load="1" style="display:block;height:' + height + 'px;width:'+ $EemWizard.width()+'px;" wz_index="0"></li>');
			if(options.items[0].isUrl)
			{
				tempContent.load(rootpath + '/static/jsp/' + options.items[0].content, function(){
					detail.append(tempContent);
					var liHeight = options.contentHeight + (WINDOW_HEIGHT - 700);
					for(var i = 1; i < options.items.length; i++)
					{
						var temp = $('<li class="content" load="0" style="height:' + liHeight + 'px;width:'+ $EemWizard.width()+'px;" wz_index="' + i + '">'+
										'<div flag="wz_loading_' + i+ '" style="padding:25% 0 0 45%;"><img src="' + rootpath + '/static/images/loading/loading_32.gif" style="margin-right:10px;" width="32px" height="32px">正在加载页面......</div>'+
								    '</li>');
						detail.append(temp);
					}
					
					content.append(detail);
					addOther();
				});	
			}
			else
			{
				tempContent.append(options.items[0].content);
				detail.append(tempContent);
				var liHeight = options.contentHeight + (WINDOW_HEIGHT - 700);
				for(var i = 1; i < options.items.length; i++)
				{
					var temp = $('<li class="content" load="0" style="height:' + liHeight + 'px;width:'+ $EemWizard.width()+'px;" wz_index="' + i + '">'+
									'<div flag="wz_loading_' + i+ '" style="padding:25% 0 0 45%;"><img src="' + rootpath + '/static/images/loading/loading_32.gif" style="margin-right:10px;" width="32px" height="32px">正在加载页面......</div>'+
							    '</li>');
					detail.append(temp);
				}
				
				content.append(detail);
				addOther();
			}
		}
		
		/**
		 * 添加标题
		 */
		function addTitle()
		{
			allStep = options.items.length;
			var titles = '';
			for(var i = 0; i < allStep; i++)
			{
				var cls = i == 0 ? 'active' : '';
				titles += '<li class="' + cls + '" index="' + i + '" flag="wz_title">' +
							  '<span class="label">' + (i+1) + '</span>' +
							  options.items[i].title +
							  '<img style="float:right;display:none;" flag="ok" src="' + rootpath + '/static/images/test_ok.png"/>' +
							  '<img style="float:right;display:none;" flag="error" src="' + rootpath + '/static/images/test_error.png"/>' +
						  '</li>';
			}
			
			var ulWidth = unitWidth * allStep;
			titleContainer = $('<div class="title_container">'+
									'<ul class="wizard_title" flag="wz_li" style="width:' + (ulWidth + 40) + 'px;">' +
										titles+
									'</ul>'+
								'</div>');
			content.append(titleContainer);
			if(!options.hasTitle)
			{
				titleContainer.hide();	
			}
		}
		
		/**
		 * 添加button
		 */
		function addButton()
		{
			var buttonContainer = $('<div class="buttons_container"></div>');
			if(options.items.length == 1)
			{
				cancelBtn = $('<input type="button" value="' + options.cancelBtnStr + '" title="' + options.cancelBtnStr + '" class="btn btn-default btn-sm" flag="wizard_cancel" style="margin: 10px 10px 0px 0px;">');
				preBtn = $('<input type="button" value="' + options.preBtnStr + '" title="' + options.preBtnStr + '" class="btn btn-primary  btn-sm" flag="wizard_pre" style="display:none;margin: 10px 10px 0px 0px;">');
				nextBtn = $('<input type="button" value="' + options.nextBtnStr + '" title="' + options.nextBtnStr + '" class="btn btn-primary btn-sm" flag="wizard_next" style="display:none;margin: 10px 10px 0px 0px;">');
				finishBtn = $('<input type="button" value="' + options.finishBtnStr + '" title="' + options.finishBtnStr + '" class="btn btn-primary btn-sm" flag="wizard_finish" style="margin: 10px 10px 0px 0px;">');
			}
			else
			{
				cancelBtn = $('<input type="button" value="' + options.cancelBtnStr + '" title="' + options.cancelBtnStr + '" class="btn btn-default btn-sm" flag="wizard_cancel" style="margin: 10px 10px 0px 0px;">');
				preBtn = $('<input type="button" value="' + options.preBtnStr + '" title="' + options.preBtnStr + '" class="btn btn-primary btn-sm" flag="wizard_pre" style="display:none;margin: 10px 10px 0px 0px;">');
				nextBtn = $('<input type="button" value="' + options.nextBtnStr + '" title="' + options.nextBtnStr + '" class="btn btn-primary btn-sm" flag="wizard_next" style="margin: 10px 10px 0px 0px;">');
				finishBtn = $('<input type="button" value="' + options.finishBtnStr + '" title="' + options.finishBtnStr + '" class="btn btn-primary btn-sm"  flag="wizard_finish"style="display:none;margin: 10px 10px 0px 0px;">');
			}
			buttonContainer.append(cancelBtn);
			buttonContainer.append(preBtn);
			buttonContainer.append(nextBtn);
			buttonContainer.append(finishBtn);
			cancelBtn.click(options.onCancel);
			preBtn.click(function(){
				doChange(CHANGE_TYPE_PRE);
			});
			nextBtn.click(function(){
				doChange(CHANGE_TYPE_NEXT, $(this));
			});
			finishBtn.click(function(){
				doFinish($(this));
			});
			content.append(buttonContainer);
		}
		
		function loadContent(index)
		{
			var $li = $('li[wz_index="' + curIndex + '"]');
			if($li.attr('load') == 1)
			{
				options.onStepChanged(index);
				return;
			}
			
			var curContent = $('li[wz_index="' + index + '"]');
			if(options.items[index] != null && options.items[index].isUrl)
			{
				curContent.load(rootpath + '/static/jsp/' + options.items[index].content, function(){
					$li.attr('load', 1);
					options.onStepChanged(index);
				});
			}
			else
			{
				$li.attr('load', 1);
				$('div[flag="wz_loading_' + index + '"]').hide();
				curContent.append(options.items[index].content);
				options.onStepChanged(index);
			}
		}
		
		function doFinish($self)
		{
			if(options.onBeforeFinish != null)
			{
				if(!options.onBeforeFinish($self))
				{
					if(options.hasTitle)
					{
						$('li[index="' + curIndex + '"] img[flag="ok"]').hide();
						$('li[index="' + curIndex + '"] img[flag="error"]').show();
//						$('li[index="' + curIndex + '"][flag="wz_title"]').addClass('invalidate');
//						$('li[index="' + curIndex + '"][flag="wz_title"]').removeClass('active');
					}
					return;
				}
			}
			if(options.onFinishing != null)
			{
				options.onFinishing();
				var lastObj = null;
				$('.eem_window_detail').each(function(){
				    if($(this).css("display")=='block'){
				    	$(this).remove();
				    }
				    else
				    {
						lastObj = $(this);					    	
				    }
				});
				if(lastObj != null)
				{
					lastObj.css("display", "block");
				}
				
				if($('.eem_window_detail').length < 1)
				{
					$('.eem_window_bg').remove();
					$('.eem_window_detail').remove();
				}
			}
		}
		
		/**
		 * 执行切换命令
		 * @param type : 1 向前，-1回退
		 */
		function doChange(type, $self)
		{
			if(!options.onStepChanging(curIndex, curIndex + type, $self))
			{
				$('li[index="' + curIndex + '"] img[flag="ok"]').hide();
				$('li[index="' + curIndex + '"] img[flag="error"]').show();
				return;
			}
			curIndex+=type;
			if(type == CHANGE_TYPE_NEXT) //后退不再加载
			{
				loadContent(curIndex);
			}
			else if(type == CHANGE_TYPE_PRE)
			{
				$('li[index="' + (curIndex + 1) + '"] img[flag="ok"]').hide();
				$('li[index="' + (curIndex + 1) + '"] img[flag="error"]').hide();
			}
			
			$('li[flag="wz_title"]').each(function(){
				$(this).removeClass('active');
				$(this).removeClass('invalidate');
			});
			
			$('li[index="' + curIndex + '"][flag="wz_title"]').addClass('active');
			$('li[index="' + (curIndex - 1) + '"] img[flag="ok"]').show();
			$('li[index="' + (curIndex - 1) + '"] img[flag="error"]').hide();
			if(curIndex > 0)
			{
				preBtn.show();
			}
			if(curIndex == 0)
			{
				preBtn.hide();
			}
			
			if(curIndex == allStep-1)
			{
				nextBtn.hide();
				finishBtn.show();
			}
			else
			{
				nextBtn.show();
				finishBtn.hide();
			}
			
			var tcWidth = $EemWizard.width();
			var v = curIndex * unitWidth;
			var right = allStep * unitWidth - tcWidth - v;
			if((v + tcWidth) < (allStep * unitWidth))
			{
				
				if(right < unitWidth)
				{
					v += right + 30;
				}
				if(right < unitWidth)
				{
					v -= right + 30;
				}
				$('ul[flag="wz_li"]').css({
	                'transform': 'translate3d(' + (-v) + 'px, 0px, 0px)',
	                '-webkit-transform': 'translate3d(' + (-v) + 'px, 0px, 0px)'
	            });
			}
			else if(tcWidth < allStep * unitWidth)
			{
				v += right + 30;
				$('ul[flag="wz_li"]').css({
	                'transform': 'translate3d(' + (-v) + 'px, 0px, 0px)',
	                '-webkit-transform': 'translate3d(' + (-v) + 'px, 0px, 0px)'
	            });
			}
			
			
			var contentMove = curIndex * tcWidth;
			detail.css({
                'transform': 'translate3d(' + (-contentMove) + 'px, 0px, 0px)',
                '-webkit-transform': 'translate3d(' + (-contentMove) + 'px, 0px, 0px)'
            });
		}
		
		function doCancel()
		{
			if(options.onBeforeCancel != null)
			{
				options.onBeforeCancel();
			}
			
			$('.eem_window_bg').remove();
			$('.eem_window_detail').remove();
		}

		var g_subContentIndex = 0;//当前子界面的索引，递增
		function addSubContent(hiddenButton)
		{
			var height = content.height();
			var subContent = $('<div curIndex="' + g_subContentIndex + '" flag="wz_subContent" style="height:' + height + 'px;"></div>');	//弹出界面
						
			$EemWizard.append(subContent);
			var contentHeight = options.contentHeight + (WINDOW_HEIGHT - 700);
			if(hiddenButton != null && hiddenButton)
			{
				contentHeight += 60;
			}
			var subDetail = $('<div flag="sub_detail" curIndex="' + g_subContentIndex + '" style="height:' + (contentHeight + 105) + 'px;">' + 
								   '<div flag="sub_detail_load" curIndex="' + g_subContentIndex + '" style="padding:25% 0 0 45%;">' + 
								   		'<img src="' + rootpath + '/static/images/loading/loading_32.gif" style="margin-right:10px;" width="32px" height="32px">正在加载页面......' + 
								   '</div>' +
								   '<div flag="sub_detail_data" curIndex="' + g_subContentIndex + '" style="height:100%;overflow: auto;">' +
								   '</div>'+
							   '</div>');
			subContent.append(subDetail);
			if(hiddenButton == null || !hiddenButton)
			{
				var subFinishBtn = $('<input curIndex="' + g_subContentIndex + '" type="button" value="' + options.finishBtnStr + '" class="btn btn-primary btn-sm" style="margin: 10px 10px 0px 0px;">');
				var subCancelBtn = $('<input curIndex="' + g_subContentIndex + '" type="button" value="' + options.cancelBtnStr + '" class="btn btn-default btn-sm" style="margin: 10px 10px 0px 0px;">');
				var buttonContainer = $('<div class="buttons_container"></div>'); 
				buttonContainer.append(subCancelBtn);
				buttonContainer.append(subFinishBtn);
				subContent.append(buttonContainer);	
				
				subCancelBtn.click(function(){
					var curSubIndex = $(this).attr('curIndex');
					if(g_sub_Fnc[curSubIndex].subCancel != null)
					{
						g_sub_Fnc[curSubIndex].subCancel();
					}
					
					closeSubContent(curSubIndex);
				});
				
				subFinishBtn.click(function(){
					var curSubIndex = $(this).attr('curIndex');
					var isSuc = true;
					if(g_sub_Fnc[curSubIndex].subFinish != null)
					{
						isSuc = g_sub_Fnc[curSubIndex].subFinish(g_sub_Fnc[curSubIndex].subFinishParams, $(this));
					}
					if(isSuc)
					{
						closeSubContent(curSubIndex);
					}
				});
			}
		}
		
		function closeSubContent(curSubIndex)
		{
			if(curSubIndex < 1)
			{
				content.slideDown(showTime, function(){
					$('div[flag="wz_subContent"][curIndex="' + curSubIndex + '"]').remove();
					g_subContentIndex--;
				});
			}
			else
			{
				var preSubContent = $('div[flag="wz_subContent"][curIndex="' + (curSubIndex - 1) + '"]');
				preSubContent.slideDown(showTime, function(){
					$('div[flag="wz_subContent"][curIndex="' + curSubIndex + '"]').remove();
					g_subContentIndex--;
				});
			}
		}
		
		/**
		 * 展示子页面
		 */
		$EemWizard.showSubContent = function(addContent, isUrl, afterShowCk, okCk, okParams, cancelCk, hiddenButton)
		{
			var curSubIndex = g_subContentIndex;
			addSubContent(hiddenButton); //此时还是0
			if(curSubIndex == 0)
			{
				content.slideUp(showTime, function(){
					addSubDetailContent(curSubIndex, addContent, isUrl, afterShowCk, okCk, okParams, cancelCk);
				});
			}
			else
			{
				var preSubContent = $('div[flag="wz_subContent"][curIndex="' + (curSubIndex - 1) + '"]');
				preSubContent.slideUp(showTime, function(){
					addSubDetailContent(curSubIndex, addContent, isUrl, afterShowCk, okCk, okParams, cancelCk);
				});
			}			
		};
		
		
		var g_sub_Fnc = [];  //对应的子页面的操作，释放页面时要把这些方法释放，不然容易出错
		/*
		 * 添加详细内容
		 */
		function addSubDetailContent(curIndex, addContent, isUrl, afterShowCk, okCk, okParams, cancelCk)
		{
			var fnc = {};
			fnc.subFinish = okCk;
			fnc.subFinishParams = okParams;
			fnc.subCancel = cancelCk;
			g_sub_Fnc[g_subContentIndex] = fnc;
			if(isUrl)
			{
				$('div[flag="sub_detail_data"][curIndex="' + curIndex + '"]').unbind('load').load(addContent, function(){
					if(afterShowCk != null)
					{
						afterShowCk();
					}
					$('div[flag="sub_detail_load"][curIndex="' + curIndex + '"]').hide();
					g_subContentIndex++;
				});
			}
			else
			{
				$('div[flag="sub_detail_data"][curIndex="' + curIndex + '"]').append(addContent);
				$('div[flag="sub_detail_load"][curIndex="' + curIndex + '"]').hide();
				g_subContentIndex++;
				if(afterShowCk != null)
				{
					afterShowCk();
				}
			}
		}
		
		return $EemWizard;
	};
}(jQuery));
