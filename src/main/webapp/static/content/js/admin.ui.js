/***************************************
Author: 刘奇
CreateDate: 2012-5-15
Description:控制管理员界面
Department:苏州同元软控信息技术有限公司
****************************************/

// 输出 iframe 框架
admin.ui.outputIframe = function () {
    var scrolling = $.isIE6 == true ? 'yes' : 'auto';
    document.write('<iframe id="content" width="100%" height="100%" class="hide" marginwidth="0" marginheight="0" frameborder="0" scrolling="' + scrolling + '" onload="$(\'#loading\').hide();$(this).show();" src=""></iframe>');
};

// 初始化主窗口布局
admin.ui.loaded = false;
admin.ui.initLayout = function () {
    if ($('#left').size() > 0) {
        var height = $(window).height() - 60 - 28 - 10 - 10 - 10;
        $('#left').css({ height: height });
        $('#left-nav').css({ height: height - 7 });
        $('#right').css({ height: height });
        $(window).bind('resize', function () {
            if (admin.ui.loaded) {
                top.location.reload();
            }
            admin.ui.loaded = true;
        });
    }
};

// 初始化控件
admin.ui.initControl = function () {
    $('a').live('focus', function () { $(this).blur(); });
    $('input[type=radio]').live('focus', function () { $(this).blur(); });
    $('input[type=checkbox]').live('focus', function () { $(this).blur(); });
    $('.btn-middle').css({ 'margin-bottom': ($.isIE6 ? 1 : 3) + 'px' });//修改按钮水平对齐

    // 三态按钮
    $('.btn')
        .live('mousedown', function () { $(this).addClass('btn-active'); })
        .live('mouseup', function () { $(this).removeClass('btn-active'); })
        .live('mouseover', function () { $(this).addClass('btn-hover'); })
        .live('mouseout', function () { $(this).removeClass('btn-active'); $(this).removeClass('btn-hover'); });
    $('.btn-lit')
        .live('mousedown', function () { $(this).addClass('btn-lit-active'); })
        .live('mouseup', function () { $(this).removeClass('btn-lit-active'); })
        .live('mouseover', function () { $(this).addClass('btn-lit-hover'); })
        .live('mouseout', function () { $(this).removeClass('btn-lit-active'); $(this).removeClass('btn-lit-hover'); });
};

// 打开一个地址
admin.ui.goUrl = function (url) {
    if (url != '') {
        $('#loading').show();
        if (url.indexOf('#') == -1) {
            url = url + (url.indexOf('?') == -1 ? '?___t=' : '&___t=') + Math.random();
        } else {
            var arr = url.split('#');
            url = arr[0] + (arr[0].indexOf('?') == -1 ? '?___t=' : '&___t=') + Math.random() + '#' + arr[1];
        }
        $('#content').hide().attr('src', url);
    }
};

// 初始化日期时间
admin.ui.initDateTime = function () {
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

// 初始化右边内容块
admin.ui.initBlock = function () {
    var block = $('#block-wp');
    if (block.size() > 0) {
        if (top.location.href == location.href)
            top.location.replace(admin.config.indexUrl);
        else if ($.isIE6 == false) {
            window.setTimeout(function () { if (block.height() < $(document).height() - 3) { block.css({ 'margin-right': '0px' }); } }, 100);
        }
    }
};

// 输出分页
admin.ui.pager = {};
admin.ui.outputPager = function (urlFormat, pageSize, pageIndex, pageCount, recordCount) {
    pageSize = parseInt(pageSize, 10);
    pageIndex = parseInt(pageIndex, 10);
    pageCount = parseInt(pageCount, 10);
    recordCount = parseInt(recordCount, 10);

    if (pageIndex < 1)
        pageIndex = 1;
    if (pageIndex > pageCount)
        pageIndex = pageCount;

    admin.ui.pager.urlFormat = urlFormat;
    admin.ui.pager.pageCount = pageCount;

    function _getLink(text, enabled, urlFormat, index) {
        if (enabled == false)
            return $.format(' <a class="button-white" style="filter:Alpha(Opacity=60);opacity:0.6;" href="javascript:void(0);"><span>{0}</span></a>', text);
        else
            return $.format(' <a class="button-white" href="javascript:goUrl(\'' + urlFormat + '\');"><span>{1}</span></a>', index, text);
    }

    var html = [];
    html.push('<div class="pager-bar">');
    html.push($.format('<div class="msg">共{0}条记录，当前第{1}/{2}，每页{3}条记录</div>', recordCount, pageIndex, pageCount, pageSize));
    html.push(_getLink('首页', pageIndex > 1, urlFormat, 1));
    html.push(_getLink('上一页', pageIndex > 1, urlFormat, pageIndex - 1));
    html.push(_getLink('下一页', pageCount > 0 && pageIndex < pageCount, urlFormat, pageIndex + 1));
    html.push(_getLink('未页', pageCount > 0 && pageIndex < pageCount, urlFormat, pageCount));
    html.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
    html.push('第<input id="current-index" onkeydown="$.enterSubmit(event, admin.ui.pagerJump);" class="input-small" type="text" value="' + (pageIndex > 0 ? pageIndex : '') + '" />页');
    html.push('&nbsp;&nbsp;&nbsp;&nbsp;');
    html.push('<a class="button-white"' + (pageCount <= 1 ? ' style="filter:Alpha(Opacity=60);opacity:0.6;" href="javascript:void(0);"' : ' href="javascript:admin.ui.pagerJump();"') + '><span>跳转</span></a>');
    html.push('</div>');

    document.write(html.join(''));
};
// 分页跳转
admin.ui.pagerJump = function () {
    var index = $('#current-index').trim();
    if ($.isIntPlus(index) == false || parseInt(index) < 1 || parseInt(index) > admin.ui.pager.pageCount) {
        $('#current-index').val('').focus();
        return;
    }
    goUrl($.format(admin.ui.pager.urlFormat, index));
}

admin.ui.goBack = function () {
    window.history.back();
};

admin.ui.doSubmit = function (name) {
    var form = (name == undefined || name == '') ? document.forms[0] : document.forms[name];
    if ($.isObject(form)) {
        $('.btn-lit').setDisabled(true).click(function () { return false; });
        if (jBox != undefined) {
            jBox.tip('正在提交...', 'loading');
        }
        form.submit();
    }
};

admin.ui.doLogout = function () {
    $.jBox.confirm('确定要退出系统吗？', '提示', function (v, h, f) {
        if (v == 'ok') {
            location.href = "/Account/LogOut";
        }
        return true;
    });
};

admin.ui.doRefreshCode = function () {
    var img = $('#verifycode-img');
    if (img.size() > 0) {
        $('#verifycode-img').attr('src','/Account/ValidateCode/' + '?' + Math.random());
    }
};

admin.ui.tip = function (text, icon) {
    if (jBox == undefined) {
        $('#login-tip').text(text);
    }
    else {
        jBox.tip(text, icon, { top: '35px' });
    }
};

admin.ui.doLogin = function () {
    var username = $('#username').val();
    var password = $('#password').val();
    var verifycode = $('#verifycode').val();
    var rememberMe = $('#rememberMe').get(0).checked;

    if (username == '') {
        admin.ui.tip('请输入用户！', 'warning');
        $('#username').focus();
        return;
    }
    if (password == '') {
        admin.ui.tip('请输入密码！', 'warning');
        $('#password').focus();
        return;
    }
    if (verifycode == '') {
        admin.ui.tip('请输入验证码！', 'warning');
        $('#verifycode').focus();
        return;
    }
    var data = { username: username, password: password, rememberMe: rememberMe, verifycode: verifycode };

    admin.ui.tip('正在登录，请稍后...', 'loading');
    $.postJson("/Account/ValidateUser", data, function (data) {
        if (data.code == 1) {
           top.location = "/Home/Index";
        } else {
            admin.ui.doRefreshCode();
            if (data.code == 2) {
                admin.ui.tip('验证码已失效，请重新输入！', 'error');
                $('#verifycode').val('').focus();
            } else if (data.code == 3) {
                admin.ui.tip('验证码错误，请重新输入！', 'error');
                $('#verifycode').select();
            } else {
                admin.ui.tip(data.message,'error');
                $('#username').select();
            }
        }
    }, function () {
        admin.ui.tip('登录出错！', 'error');
    });
};
