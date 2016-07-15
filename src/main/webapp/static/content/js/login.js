/* File Created: 九月 5, 2012 */

function doResize() {
    var a = $("#table");
    a.css('left', (document.body.clientWidth / 2 - a.width() / 2) + "px");
    a.css('top', (document.body.scrollTop + document.body.clientHeight / 2 - a.height() / 2) + "px");
    a.show();
}

function tooltip() { xOffset = -20; yOffset = 10; $("a,input").hover(function (e) { if (this.title != '' && this.title != undefined) { this.t = this.title; this.title = ""; $("body").append("<p id='tooltip'>" + this.t + "</p>"); $("#tooltip").css("top", (e.pageY - xOffset) + "px").css("left", (e.pageX + yOffset) + "px").fadeIn("fast"); } }, function () { if (this.t != '' && this.t != undefined) { this.title = this.t; $("#tooltip").remove(); } }); $("a,input").mousemove(function (e) { $("#tooltip").css("top", (e.pageY - xOffset) + "px").css("left", (e.pageX + yOffset) + "px"); }); $("img").hover(function (e) { if (this.alt != '' && this.alt != undefined) { this.t = this.alt; this.alt = ""; $("body").append("<p id='tooltip'>" + this.t + "</p>"); $("#tooltip").css("top", (e.pageY - xOffset) + "px").css("left", (e.pageX + yOffset) + "px").fadeIn("fast"); } }, function () { if (this.t != '' && this.t != undefined) { this.alt = this.t; $("#tooltip").remove(); } }); $("img").mousemove(function (e) { $("#tooltip").css("top", (e.pageY - xOffset) + "px").css("left", (e.pageX + yOffset) + "px"); }); };
function setInput() {
    $("input[type=text],input[type=password]").focus(function () {
        $(this).addClass("focus"); //.removeClass("error");
    }).blur(function () {
        $(this).removeClass("focus");
    }).bind('keydown', function (event) {
        clearError("");
    }); ;
}
function focusInput() {
    setTimeout(function () {
        $("#userName").select();
        $("#userName").focus();
    }, 100);
}
function bindEnterKey() {
    $(document).bind('keyup', function (event) {
        if (event.keyCode == 13) {
            submit.submit();
        }
    });
}
function refreshCode() {
    obj = $("#authCodeImg");
    obj.attr("src", makeAuthUrl());
    $(loginForm.authcode).val("");
    $(loginForm.authcode).focus();
}
function error(msg) {
    $("#notice").html(msg);
}
function clearError() {
    $("#notice").html("");
}
function submit() {
    var _this = this;
    this.userName = null;
    this.passWord = null;
    this.authcode = null;
    this.checkForm = function () {
        this.userName = $("#userName");
        this.passWord = $("#passWord");
        this.authcode = $("#authcode");
        if (this.userName.val() == "") {
            error('请填写帐户名');
            this.userName.focus();
            return false;
        }
        if (this.passWord.val() == "") {
            error('请填写密码');
            this.passWord.focus();
            return false;
        }
        return true;
    }
    this.submit = function () {
        if (this.checkForm()) {
            error("登录中 请稍等...");
            this.doLogin();
        }
    }
    this.doLogin = function () {
        $.ajax({
            type: "post",
            url: "/taskpro/account/validateUser.do",
            data: "userName=" + this.userName.val() + "&password=" + this.passWord.val() ,
            dataType:'json',
            success: function (data) {
                try {
                    if (data.success) {
                       window.location.href = '/taskpro/index.jsp';
                    	//window.location.href = '/taskpro/home/Index.do';
                    } else {
                        error(data.msg);
                       _this.passWord.focus(); 	
                    }
                } catch (e) {
                }
            }
        });
    }
}
submit = new submit();
$(window).resize(function () {
    doResize();
});
$(document).ready(function () {
    tooltip();
    focusInput();
    setInput();
    bindEnterKey();
    $("#submitbtn").click(function () {
        submit.submit();
    });
}); 