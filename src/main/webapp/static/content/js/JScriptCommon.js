
var sy = $.extend({}, sy); /* 全局对象 */

$.parser.auto = false;
$(function () {
    $.messager.progress({
        text: '页面加载中....',
        interval: 100
    });
    $.parser.parse(window.document);
    window.setTimeout(function () {
        $.messager.progress('close');
        if (self != parent) {
            window.setTimeout(function () {
                parent.$.messager.progress('close');
            }, 500);
        }
    }, 1);
    $.parser.auto = true;
    $('body').append('<div id="myWindow" class="easyui-dialog" closed="true"></div>');
});

function createFrame(url) {

    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:99%;overflow-y: auto; "></iframe>';
    return s;
}
/**
* 包含easyui的扩展和常用的方法
* 
* @author 刘奇
*/
$.fn.panel.defaults.onBeforeDestroy = function () {/* tab关闭时回收内存 */
    var frame = $('iframe', this);
    try {
        if (frame.length > 0) {
            frame[0].contentWindow.document.write('');
            frame[0].contentWindow.close();
            frame.remove();
            if ($.browser.msie) {
                CollectGarbage();
            }
        } else {
            $(this).find('.combo-f').each(function () {
                var panel = $(this).data().combo.panel;
                panel.panel('destroy');
            });
        }
    } catch (e) {
    }
};

$.fn.panel.defaults.loadingMessage = '数据加载中，请稍候....';
$.fn.datagrid.defaults.loadMsg = '数据加载中，请稍候....';
$.fn.tree.defaults.loadMsg = '数据加载中，请稍候....';
var easyuiErrorFunction = function (XMLHttpRequest) {
    /* $.messager.progress('close'); */
    /* alert(XMLHttpRequest.responseText.split('<script')[0]); */
    $.messager.alert('错误', XMLHttpRequest.responseText.split('<script')[0]);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

var easyuiPanelOnMove = function (left, top) {/* 防止超出浏览器边界 */
    if (left < 0) {
        $(this).window('move', {
            left: 1
        });
    }
    if (top < 0) {
        $(this).window('move', {
            top: 1
        });
    }
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;

$.extend($.fn.validatebox.defaults.rules, {
    eqPassword: {/* 扩展验证两次密码 */
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '密码不一致！'
    },
    onlyNumber: {/* 扩展验证只能为数字 */
        validator: function (value, param) {
            return /^(\-?)(\d+)$/.test(value) && value.length <= param[0];
        },
        message: '只能输入{0}位以下正负整数!'
    },
    onlyFloat: {/* 扩展验证只能为数字 */
        validator: function (value, param) {
            return /\d+(\.\d)?\d*/.test(value) && value.length <= param[0];
        },
        message: '只能输入{0}位以下实数!'
    },
    permissionItemCode: {
        validator: function (value, param) {
            return /^[A-Za-z][A-Za-z0-9_]{0,49}$/.test(value);
        },
        message: '权限项代码只能以字母开头，由1-50个字母、数字或下划线组成！'
    }

});

$.extend($.fn.datagrid.defaults.editors, {
    combocheckboxtree: {
        init: function (container, options) {
            var editor = $('<input/>').appendTo(container);
            options.multiple = true;
            editor.combotree(options);
            return editor;
        },
        destroy: function (target) {
            $(target).combotree('destroy');
        },
        getValue: function (target) {
            return $(target).combotree('getValues').join(',');
        },
        setValue: function (target, value) {
            $(target).combotree('setValues', sy.getList(value));
        },
        resize: function (target, width) {
            $(target).combotree('resize', width);
        }
    }
});

/**
* 获得项目根路径
* 
* 使用方法：sy.bp();
*/
sy.bp= function(){
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
};

/**
* 增加formatString功能
* 
* 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
*/
sy.fs = function (str) {
    for (var i = 0; i < arguments.length - 1; i++) {
        if (arguments[i + 1] == null) {
            arguments[i + 1] = "";
        }
        str = str.replace("{" + i + "}", arguments[i + 1]);
    }
    return str;
};

/**
* 增加命名空间功能
* 
* 使用方法：sy.ns('jQuery.bbb.ccc','jQuery.eee.fff');
*/
sy.ns = function () {
    var o = {}, d;
    for (var i = 0; i < arguments.length; i++) {
        d = arguments[i].split(".");
        o = window[d[0]] = window[d[0]] || {};
        for (var k = 0; k < d.slice(1).length; k++) {
            o = o[d[k + 1]] = o[d[k + 1]] || {};
        }
    }
    return o;
};

/**
* 生成UUID
*/
sy.random4 = function () {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
sy.UUID = function () {
    return (sy.random4() + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + sy.random4() + sy.random4());
};

/**
* 获得URL参数
*/
sy.getUrlParam = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
};

sy.getList = function (value) {
    if (value) {
        var values = [];
        var t = value.split(',');
        for (var i = 0; i < t.length; i++) {
            values.push('' + t[i]); /* 避免他将ID当成数字 */
        }
        return values;
    } else {
        return [];
    }
};

sy.png = function () {
    var imgArr = document.getElementsByTagName("IMG");
    for (var i = 0; i < imgArr.length; i++) {
        if (imgArr[i].src.toLowerCase().lastIndexOf(".png") != -1) {
            imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + imgArr[i].src + "', sizingMethod='auto')";
            imgArr[i].src = "images/blank.gif";
        }
        if (imgArr[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
            var img = imgArr[i].currentStyle.backgroundImage.substring(5, imgArr[i].currentStyle.backgroundImage.length - 2);
            imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img + "', sizingMethod='crop')";
            imgArr[i].style.backgroundImage = "url('images/blank.gif')";
        }
    }
};
sy.bgPng = function (bgElements) {
    for (var i = 0; i < bgElements.length; i++) {
        if (bgElements[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
            var img = bgElements[i].currentStyle.backgroundImage.substring(5, bgElements[i].currentStyle.backgroundImage.length - 2);
            bgElements[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img + "', sizingMethod='crop')";
            bgElements[i].style.backgroundImage = "url('images/blank.gif')";
        }
    }
};

sy.isLessThanIe8 = function () {/* 判断浏览器是否是IE并且版本小于8 */
    return ($.browser.msie && $.browser.version < 8);
};
/* 
*扩展AJAX出现错误的提示 
*/
$.ajaxSetup({
    type: 'POST',
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        $.messager.progress('close');
        
        $.messager.alert('错误', XMLHttpRequest.responseText.split('<script')[0]);
    }
});

function showConfirm(title,msg,callback){
    $.messager.confirm(title,msg,function(r){
        if (r) {
            if (jQuery.isFunction(callback)) {
                callback.call();
            } 
        }
    });
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
//显示窗口
function showMyWindow(title, iconCls,href, width, height, modal, minimizable, maximizable) {
     $('#myWindow').window({
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
     $('#myWindow').window('close');    
 }
 //弹出右下角提示框
 function showMsg(title, msg) {
     $.messager.show({ title: title, msg: msg, showType: 'show' });
 }

 //弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
 function alertMsg(title, msgString, msgType) {
     $.messager.alert(title, msgString, msgType);
 }


$.extend({

//    isIE: $.browser.msie != 'undefined',
//    isIE6: $.isIE && $.browser.version == '6.0',

    copyText: function (obj) { var str = $.isElement(obj) ? obj.value : ($(obj).size() > 0 ? $(obj).val() : obj); if (window.clipboardData && clipboardData.setData && window.clipboardData.setData("Text", str)) { return true; } else { if ($.isElement(obj)) o.select(); return false; } },
    addBookMark: function (url, title) { try { if (window.sidebar) { window.sidebar.addPanel(title, url, ''); } else if ($.isIE) { window.external.AddFavorite(url, title); } else if (window.opera && window.print) { return true; } } catch (e) { alert("Your browser does not support it."); } },
    setHomePage: function (url) { try { document.body.style.behavior = 'url(#default#homepage)'; document.body.setHomePage(url); } catch (e) { if (window.netscape) { try { netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); } catch (e) { alert("Your browser does not support it."); } var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch); prefs.setCharPref('browser.startup.homepage', url); } } },

    getCookie: function (name) { var r = new RegExp('(^|;|\\s+)' + name + '=([^;]*)(;|$)'); var m = document.cookie.match(r); return (!m ? '' : decodeURIComponent(m[2])); },
    setCookie: function (name, value, expire, domain, path) { var s = name + '=' + encodeURIComponent(value); if (!$.isUndefined(path)) s = s + '; path=' + path; if (expire > 0) { var d = new Date(); d.setTime(d.getTime() + expire * 1000); if (!$.isUndefined(domain)) s = s + '; domain=' + domain; s = s + '; expires=' + d.toGMTString(); } document.cookie = s; },
    removeCookie: function (name, domain, path) { var s = name + '='; if (!$.isUndefined(domain)) s = s + '; domain=' + domain; if (!$.isUndefined(path)) s = s + '; path=' + path; s = s + '; expires=Fri, 02-Jan-1970 00:00:00 GMT'; document.cookie = s; },

    isUndefined: function (obj) { return typeof obj == 'undefined'; },
    isObject: function (obj) { return typeof obj == 'object'; },
    isNumber: function (obj) { return typeof obj == 'number'; },
    isString: function (obj) { return typeof obj == 'string'; },
    isElement: function (obj) { return obj && obj.nodeType == 1; },
    isFunction: function (obj) { return typeof obj == 'function'; },
    isArray: function (obj) { return Object.prototype.toString.call(obj) === '[object Array]'; },

    isInt: function (str) { return /^-?\\d+$/.test(str); },
    isFloat: function (str) { return /^(-?\\d+)(\\.\\d+)?$/.test(str); },
    isIntPlus: function (str) { return /^[0-9]*[1-9][0-9]*$/.test(str); },
    isFloatPlus: function (str) { return /^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/.test(str); },
    isEnglish: function (str) { return /^[A-Za-z]+$/.test(str); },
    isChinese: function (str) { return /^[\u0391-\uFFE5]+$/.test(str); },
    isZipCode: function (str) { return /^[1-9]\d{5}$/.test(str); },
    isEmail: function (str) { return /^[A-Z_a-z0-9-\.]+@([A-Z_a-z0-9-]+\.)+[a-z0-9A-Z]{2,4}$/.test(str); },
    isMobile: function (str) { return /^((\(\d{2,3}\))|(\d{3}\-))?((1[35]\d{9})|(18[89]\d{8}))$/.test(str); },
    isUrl: function (str) { return /^(http:|ftp:)\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"])*$/.test(str); },
    isIpAddress: function (str) { return /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/.test(str); },


    encode: function (str) { return encodeURIComponent(str); },
    decode: function (str) { return decodeURIComponent(str); },
    format: function () { if (arguments.length == 0) return ''; if (arguments.length == 1) return arguments[0]; var args = $.cloneArray(arguments); args.splice(0, 1); return arguments[0].replace(/{(\d+)?}/g, function ($0, $1) { return args[parseInt($1)]; }); },

    escapeHtml: function (str) { return str.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;"); },
    unescapeHtml: function (str) { return str.replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&nbsp;/g, " ").replace(/&quot;/g, "\"").replace(/&amp;/g, "&"); },
    filterHtml: function (str) { str = str.replace(/\<(.*?)\>/g, '', str); str = str.replace(/\<\/(.*?)\>/g, '', str); return str; },

    cloneArray: function (arr) { var cloned = []; for (var i = 0, j = arr.length; i < j; i++) { cloned[i] = arr[i]; } return cloned; },

    getKeyCode: function (e) { var evt = window.event || e; return evt.keyCode ? evt.keyCode : evt.which ? evt.which : evt.charCode; },
    enterSubmit: function (e, v) { if ($.getKeyCode(e) == 13) { if ($.isFunction(v)) { v(); } else if ($.isString(v)) { $(v)[0].click(); } } },
    ctrlEnterSubmit: function (e, v) { var evt = window.event || e; if (evt.ctrlKey && $.getKeyCode(evt) == 13) { if ($.isFunction(v)) { v(); } else if ($.isString(v)) { $(v)[0].click(); } } },

    getUrlQuery: function (key, decode, url) { url = url || window.location.href; if (url.indexOf("#") !== -1) url = url.substring(0, url.indexOf("#")); var rts = [], rt; queryReg = new RegExp("(^|\\?|&)" + key + "=([^&]*)(?=&|#|$)", "g"); while ((rt = queryReg.exec(url)) != null) { if (decode && decode == true) rts.push(decodeURIComponent(rt[2])); else rts.push(rt[2]); } return rts.length == 0 ? '' : (rts.length == 1 ? rts[0] : rts); },

    getJson: function (url, data, success, error, option) {
        var op = {
            type: 'GET',
            url: url,
            data: data,
            dataType: 'json',
            cache: false,
            success: function (data, textStatus) {
                if (data == null || data == undefined) {
                    if (typeof error == 'function') error();
                }
                else {
                    if (typeof error == 'function') success(data, textStatus);
                }
            },
            error: error
        };
        $.extend(op, option);
        $.ajax(op);
    },
    postJson: function (url, data, success, error, option) {
        var op = {
            type: 'POST',
            url: url,
            data: data,
            dataType: 'json',
            cache: false,
            success: function (data, textStatus) {
                if (data == null || data == undefined) {
                    if (typeof error == 'function') error();
                }
                else {
                    if (typeof error == 'function') success(data, textStatus);
                }
            },
            error: error
        };
        $.extend(op, option);
        $.ajax(op);
    },
    preloadImages: function () {
        for (var i = 0; i < arguments.length; i++) {
            var img = new Image();
            img.src = arguments[i];
        }
    }
});

//扩展一些常用函数
$.fn.extend({
    trim: function () { return $.trim(this.val()); },
    lTrim: function () { return this.val().replace(/^\s+/, ''); },
    rTrim: function () { return this.val().replace(/\s+$/, ''); },

    setDisabled: function (disabled) {
        return this.each(function () { $(this).attr('disabled', disabled).css('opacity', disabled ? 0.5 : 1.0); });
    },
    setReadOnly: function (readonly) {
        return this.each(function () { $(this).attr('readonly', readonly).css('opacity', readonly ? 0.5 : 1.0); });
    },
    setChecked: function (checked, value) {
        return this.each(function () { if (value == undefined) { $(this).attr('checked', checked); } else if ($(this).val() == value.toString()) { $(this).attr('checked', checked); } });
    }
});
