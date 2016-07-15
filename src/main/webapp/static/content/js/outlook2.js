var sy=$.extend({},sy); /* 定义全局对象，类似于命名空间或包的作用 */

$(function () {

    //InitLeftMenu(); //初始化左侧菜单

	//initLeftMenuTree();//初始化左侧菜单树
	
    initDateTime(); //初始化时间

    $('#logOut').click(function () {

        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (r) {
	
            if (r) {
                location.href = '/taskpro/account/logOut.do';
            }
        });
    });
    $('body').append('<div id="myOuterWindow" ></div>');
})

//初始化左侧
function InitLeftMenu() {
    $("#nav").accordion({ animate: true });

    $.each(_menus.menus, function (i, n) {
        var menulist = '';
        menulist += '<ul>';
        $.each(n.menus, function (j, o) {
            if (o.url != "" && o.url.indexOf('.') > 0) {
                //新网址
                menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="' + o.url + '" ><span class="icon ' + o.icon + '" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';

            }
            else {//本站网址
                menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="../../' + o.url + '" ><span class="icon ' + o.icon + '" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
            }
        })
        menulist += '</ul>';

        $('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });

    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).children('.nav').text();

        var url = $(this).attr("rel");

        var menuid = $(this).attr("ref");
        var icon = getIcon(menuid, icon);

        addTab(tabTitle, url, icon);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });
    //选中第一个
    var panels = $('#nav').accordion('panels');
    if (panels != null && panels.length > 0) {
        //如果菜单项为空，就不选中
        var t = panels[0].panel('options').title;
        $('#nav').accordion('select', t);
    }

}
//获取左侧导航的图标
function getIcon(menuid) {
    var icon = 'icon ';
    $.each(_menus.menus, function (i, n) {
        $.each(n.menus, function (j, o) {
            if (o.menuid == menuid) {
                icon += o.icon;
            }
        })
    })

    return icon;
}

function addTab(subtitle, url, icon) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            icon: icon
        });
    } else {
        $('#tabs').tabs('select', subtitle);
        //$('#mm-tabupdate').click();
        var currTab = $('#tabs').tabs('getSelected');
        $('#tabs').tabs('update', {
            tab: currTab,
            options: {
                content: createFrame(url)
            }
        });
    }
    tabClose();
    tabCloseEven();
}

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:99%;overflow-y: auto; "></iframe>';
    return s;
}

function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close', subtitle);
    })
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).children(".tabs-closable").text();

        $('#mm').data("currtab", subtitle);
        $('#tabs').tabs('select', subtitle);
        return false;
    });
}
//绑定右键菜单事件
function tabCloseEven() {
    //刷新
    $('#mm-tabupdate').click(refreshCurrentTab
    );
    //关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    });
    //全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != '我的工作台') {
                $('#tabs').tabs('close', t);
            }
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        $('#mm-tabcloseright').click();
        $('#mm-tabcloseleft').click();
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length > 0) {
            nextall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                if (t != '我的工作台') {
                    $('#tabs').tabs('close', t);
                }

            });
        }
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length > 0) {
            prevall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                if (t != '我的工作台') {
                    $('#tabs').tabs('close', t);
                }
            });
        }
    });

    //退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    });
}

//关闭当前tab页
function closeCurrentTab() {
   // $('#mm-tabclose').click();
	var tab = $('#tabs').tabs('getSelected');
	var index = $('#tabs').tabs('getTabIndex',tab);
    $('#tabs').tabs('close', index);
}
//刷新当前tab页
function refreshCurrentTab() {
    var currTab = $('#tabs').tabs('getSelected');
    var url = $(currTab.panel('options').content).attr('src');
    $('#tabs').tabs('update', {
        tab: currTab,
        options: {
            content: createFrame(url)
        }
    });
}
//关闭指定tab页
function closeTab(subtitle) {
    $('#tabs').tabs('close', subtitle);
}


//弹出右下角提示框
function showMsg(title, msg) {
    $.messager.show({ title: title, msg: msg, showType: 'show' });
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function alertMsg(title, msgString) {
    $.messager.alert(title, msgString, 'error');
}


//显示窗口
function showMyWindow(title, iconCls,href, width, height, modal, minimizable, maximizable) {
     $('#myOuterWindow').window({
         title: title,
         iconCls:iconCls===undefined?'':iconCls,
         width: width === undefined ? 600 : width,
         height: height === undefined ? 400 : height,
         content: createFrame(href),
        // href:href,
         modal: modal === undefined ? true : modal,
         minimizable: minimizable === undefined ? false : minimizable,
         maximizable: maximizable === undefined ? false : maximizable,
         shadow: false,
         cache: false,
         closed: false,
         collapsible: false,
         loadingMessage: '正在加载数据，请稍等片刻......'
     });
 }
 //关闭窗口
 function closeMyWindow() {
     $('#myOuterWindow').window('close');
 }

// 初始化日期时间
function initDateTime() {
    function _getNum(num) { return num < 10 ? '0' + num : num; }
    function _getTime() {
        var date = new Date();
        var weeks = ['<span class="txt-orange">星期天</span>', '星期一', '星期二', '星期三', '星期四', '星期五', '<span class="txt-orange">星期六</span>'];
        $('#time').html(date.getFullYear() + '年' + _getNum(date.getMonth() + 1) + '月' + _getNum(date.getDate()) + '日 ' + weeks[date.getDay()] + ' ' + _getNum(date.getHours()) + ":" + _getNum(date.getMinutes()) + ":" + _getNum(date.getSeconds()));
    }
    if ($('#time').size() > 0) {
        _getTime();
        window.setInterval(_getTime, 1000);
    }
};


//位于顶层的对话框
sy.dialog = function (options) {
    var opts = $.extend({
        modal: true,
        onClose: function () {
            $(this).dialog('destroy');
        }
    }, options);
    return $('<div id="topDialog"/>').dialog(opts);
};
/**
 * 返回任务状态
 */
sy.taskStatus = function(status){
    switch (status) {
    case -1:
        return "等待";
    case 0:
        return "已准备";
    case 1:
        return "正在运行";
    case 2:
        return "完成";
    case 3:
        return "失败";
    case 4:
        return "暂停";
    case 5:
        return "取消";
    case 6:
        return "未知";
    default:
    	return "";
    }
}

/**
 * 返回任务状态颜色 
 */
sy.taskStatusColor=function(value){
    switch (value) {
    	case -1:
    		return 'background-color:#83E4DB;';
    	case 0:
    		return 'background-color:#83E4DB;';
    	case 1:
    		return 'background-color:#86E18C;';
    	case 2:
    		return 'background-color:#86E18C;';
    	case 3:
    		return 'background-color:#F08377;';
    	case 4:
    		return 'background-color:#E9E916;';
    	case 5:
    		return 'background-color:gray;';
    	default:
    		return 'background-color:transparent';
    }
	
}
/**
 * 返回作业状态
 */
sy.workStatus = function(value){
	switch (value) {
		case -1:
			return "等待";
		case 0:
			return "已准备";
		case 1:
			return "正在运行";
		case 2:
			return "完成";
		case 3:
			return "失败";
		case 4:
			return "取消";
		case 5:
			return "未知";
		case 6:
			return "等待";
		case 7:
			return "暂停";
		case 8:
			return "错误";
		default:
			return "";
	}
}
/**
 * 返回作业状态的颜色
 */
sy.workStatusColor = function(value){
	switch (value) {
	case -1:
		return 'background-color:#83E4DB;';
	case 0:
		return 'background-color:#83E4DB;';
	case 1:
		return 'background-color:#86E18C;';
	case 2:
		return 'background-color:#86E18C;';
	case 3:
		return 'background-color:#F08377;';
	case 4:
		return 'background-color:gray;';
	case 5:
		return 'background-color:gray;';
	case 6:
		return 'background-color:#83E4DB;';
	case 7:
		return 'background-color:#ffaad5;';
	case 8:
		return 'background-color:#F08377;';
	default:
		return 'background-color:transparent';
	}
}

sy.runStatusColor = function(value){
	switch (value) {
	case -1:
		return 'background-color:#83E4DB;';
	case 0:
		return 'background-color:#83E4DB;';
	case 1:
		return 'background-color:#86E18C;';
	case 2:
		return 'background-color:#86E18C;';
	case 3:
		return 'background-color:#F08377;';
	case 4:
		return 'background-color:gray;';
	case 5:
		return 'background-color:gray;';
	case 6:
		return 'background-color:#83E4DB;';
	case 7:
		return 'background-color:#ffaad5;';
	case 8:
		return 'background-color:#F08377;';
	default:
		return 'background-color:transparent';
	}
}

/**
 * 返回任务类型
 */
sy.taskType = function(value){
    switch (value) {
    case -1:
        return 'SingleExecute';
    case 0:
        return 'ParametricStudy';
    case 1:
        return 'DOE';
    case 2:
        return 'OPT';
    default:
    	return "";
    }
}

/**
 * 返回运行状态
 */
sy.runStatus = function(value){
	switch (value) {
		case -1:
			return "等待";
		case 0:
			return "已准备";
		case 1:
			return "正在运行";
		case 2:
			return "完成";
		case 3:
			return "失败";
		case 4:
			return "取消";
		case 5:
			return "未知";
		case 6:
			return "等待";
		case 7:
			return "暂停";
		default:
			return "";
	}
}

/**
* 显示进度条
*/
function showProcess(isShow,title,msg){
    if (!isShow) {
        $.messager.progress('close');
        return;   
    }
  $.messager.progress({
        title:title,
        msg: msg
    });
}
