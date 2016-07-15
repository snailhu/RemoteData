/***************************************
Author: 刘奇
CreateDate: 2012-5-14
Description:控制登录逻辑JS文件
Department:苏州同元软控信息技术有限公司
****************************************/

var admin = {};

admin.ui = {};
admin.config = {};

$.isIE = $.browser.msie != undefined;
$.isIE6 = $.isIE && $.browser.version == '6.0';

$(function () {
    admin.ui.initLayout();
    admin.ui.initControl();
    admin.ui.initBlock();
    admin.ui.initDateTime();

    if (typeof (admin.ui.initMenu) == 'function') {
        admin.ui.initMenu();
    }
    else {
        admin.ui.doRefreshCode();
        $('#login-btn').click(admin.ui.doLogin);
        $('#refresh-btn').click(admin.ui.doRefreshCode);
        $('#verifycode-img').click(admin.ui.doRefreshCode).load(function () { $(this).show(); });
        $('#username').keydown(function (event) { $.enterSubmit(event, admin.ui.doLogin); });
        $('#password').keydown(function (event) { $.enterSubmit(event, admin.ui.doLogin); });
        $('#verifycode').keydown(function (event) { $.enterSubmit(event, admin.ui.doLogin); });
        $('#username').focus();
    }
    // 隔行变色
    $('tr:even').addClass('odd');
    $('input[type="checkbox"]').css('border', 'none'); 
});

