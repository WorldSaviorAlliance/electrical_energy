/**
 * 页码 
 */
(function ($) {
	$.fn.EemPage = function(opts){
		opts = $.extend({
			totalPage : 0,
			curPage : 0,
			changePageFnc : null
		}, opts);
		var $EemPage = this;
		init();
		
		function init()
		{
			if(opts.totalPage == null || opts.totalPage < 2)
			{
				return;
			}
			var page = '';
			var isSub = opts.curPage % 5 == 0 ? 1 : 0;
			var isEndPage = opts.curPage % 5 == 0 ? 2 : 0;
			var endPage = parseInt(opts.curPage/5) * 5 + (5 - isEndPage);
			var startPage = parseInt(opts.curPage/5) * 5 + (1 - isEndPage); 
			for(var i = startPage; i <= opts.totalPage && i <= endPage; i++)
			{
				var active = i == opts.curPage ? 'active' : '';
				page += '<li class="ng-scope ' + active + '">'+
							'<a href="javascript:void(1)" class="ng-binding" type="2">' + i + '</a>'+
						'</li>';
			}
			
			var isFirstDisabled = opts.curPage <= 1 ? 'disabled' : '';
			var isLastDisabled = opts.curPage == opts.totalPage ? 'disabled' : '';
			var isPreDisabled = opts.curPage <= 1 ? 'disabled' : '';
			var isNextDisabled = opts.curPage == opts.totalPage ? 'disabled' : '';
				
			var table = '<div class="pull-right" style="margin-right: 0px;">'+
							'<ul class="pagination ng-isolate-scope eem_page">'+
								'<li class="ng-scope ' + isFirstDisabled + '">'+
									'<a href="javascript:void(1)" class="ng-binding ' + isFirstDisabled + '" type="0">首页</a>'+
								'</li>'+
								'<li class="ng-scope ' + isPreDisabled + '">'+
									'<a href="javascript:void(0)" class="ng-binding ' + isPreDisabled + '" type="1">前一页</a>'+
								'</li>'+
								page + 
								'<li class="ng-scope ' + isNextDisabled + '">'+
									'<a href="javascript:void(2)" class="ng-binding ' + isNextDisabled + '" type="3">下一页</a>'+
								'</li>'+
								'<li class="ng-scope ' + isLastDisabled + '">'+
									'<a href="javascript:void(4)" class="ng-binding ' + isLastDisabled + '" type="4">末页</a>'+
								'</li>'+
							'</ul>'+
						'</div>';
			
			$EemPage.append($(table));
			$('.eem_page a').click(function(){
	            var isDisabled = $(this).attr('class').indexOf('disabled') == -1 ? false : true;
	            if(isDisabled)
	            	return;
	            var type = parseInt($(this).attr('type'));
	            switch(type)
	            {
	            case 0:
	            	opts.changePageFnc(1);
	            	break;
	            case 1:
	            	opts.changePageFnc(parseInt(opts.curPage - 1));
	            	break;
	            case 2:
	            	opts.changePageFnc($(this).text());
	            	break;
	            case 3:
	            	opts.changePageFnc(parseInt(opts.curPage) + 1);
	            	break;
	            case 4:
	            	opts.changePageFnc(opts.totalPage);
	            	break;
	            }
			});
		}
		
		
		return $EemPage;
	}; 
})(jQuery);
