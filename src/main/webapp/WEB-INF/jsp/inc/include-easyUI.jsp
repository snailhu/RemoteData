
<link
	href="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/themes/gray/easyui.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/themes/icon.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/jquery.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/jquery.easyui.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/content/js/JScriptCommon.js"
	type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/static/content/css/Common.css"
	rel="stylesheet" type="text/css" />
	
<style type="text/css">
/* easyUI统一去除按钮背景图片  */
.icon-remove {
    background: no-repeat center center;
}
.icon-edit {
    background: no-repeat center center;
}
.icon-undo {
    background: no-repeat center center;
}
/* 弹出框居中样式  */
.sweet-alert h2 {
	color: rgb(87, 87, 87);
	font-size: 30px;
	text-align: center;
	font-weight: 600;
	text-transform: none;
	position: relative;
	line-height: 40px;
	display: block;
	margin: 25px 0px;
	padding: 0px;
}

.sweet-alert p {
	color: rgb(121, 121, 121);
	font-size: 16px;
	font-weight: 300;
	position: relative;
	text-align: inherit;
	float: none;
	line-height: normal;
	margin: 0px;
	padding: 0px;
}

.sweet-alert .sa-error-container {
	background-color: rgb(241, 241, 241);
	margin-left: -17px;
	margin-right: -17px;
	max-height: 0px;
	overflow: hidden;
	padding: 0px 10px;
	transition: padding 0.15s, max-height 0.15s;
}

.sweet-alert button.cancel {
	background-color: rgb(193, 193, 193);
}

.sweet-alert button {
	font-size: 14px;
	width: 100px;
	height: 32px;
	border-width: 0;
	margin-right: 20px;
/* 	background-color: rgb(140, 212, 245);
	color: white;
	box-shadow: none;
	font-size: 17px;
	font-weight: 500;
	cursor: pointer;
	border: none;
	border-radius: 5px;
	padding: 10px 32px;
	margin: 26px 30px 0px; */
}

.sweet-alert .sa-confirm-button-container {
	display: inline-block;
	position: relative;
	/*     padding-left: 20px; */
}

.sa-button-container {
    margin-top: 20px;
	text-align: center;
}

.sa-confirm-button-container{
	text-align: center;
}
/* 按钮 */
.subbutton_1 {
	color: white;
	font-size: 14px;
	width: 100px;
	background-color: #1d76c5;
	height: 32px;
	border-width: 0;
	margin-right: 20px;
}
.cancelbutton_1 {
	color: white;
    font-size: 14px;
    width: 100px;
    background-color: #9C9C9C;
    height: 32px;
    border-width: 0;
}
</style>