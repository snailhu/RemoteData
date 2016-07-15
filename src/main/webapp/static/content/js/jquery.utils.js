
/***************************************
Author: 刘奇
CreateDate: 2012-5-15
Description:JS工具库，JQuery的相关扩展
Department:苏州同元软控信息技术有限公司
****************************************/

$.extend({

    isIE: $.browser.msie != undefined,
    isIE6: $.isIE && $.browser.version == '6.0',

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
