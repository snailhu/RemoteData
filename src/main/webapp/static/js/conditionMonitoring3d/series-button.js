(function ($) {
  $.fn.seriesbtnmenu = function (options) {
    options = $.extend({}, $.fn.seriesbtnmenu.defaults, options || {});
    var target = $(this);
	target.addClass('seriesbutton');
	target.attr('class','seriesbutton')
    if (options.data) {
      init(target, options.data);
    }
    else {
      if (!options.url) return;
      $.getJSON(options.url, options.param, function (data) {
        init(target, data);
      });
    }
    var url = window.location.pathname;
    //menu = target.find("[href='" + url + "']");
    //menu.parent().addClass('active');
    //menu.parent().parentsUntil('.nav-list', 'li').addClass('active').addClass('open');
    function init(target, data) {
      $.each(data, function (i, item) {
        var btn = $('<button></button>');
        btn.attr('type','button');
        var intsatellite="intsatellite("+item.id+")"
        btn.attr('onclick',intsatellite);
        btn.attr('id',item.id);
		btn.addClass('btn btn-info');
        var text = $('<span></span>');
        text.text(item.name);
        btn.append(text);
        target.append(btn);
        //currentseriesbtnid=btn.attr('id');
        //alert("回调函数里的id:"+currentseriesbtnid);
      });
      //调用定时函数
      //time();
    }
  }
 
  $.fn.seriesbtnmenu.defaults = {
    url: null,
    param: null,
    data: null
  };
})(jQuery);