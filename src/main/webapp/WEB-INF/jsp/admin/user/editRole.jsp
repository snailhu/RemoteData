<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/inc/include-easyUI.jsp"></jsp:include>
<style type="text/css">
	/* 表单 */
.l-form
{
    margin: 7px 7px 7px 15px;
}
.l-form .l-group
{
    clear: both;
    margin: 0;
    height: 28px;
    line-height: 28px;
    font-weight: bold;
    font-size: 12px;
    color: #333;
    border-bottom: solid 1px #ebebeb;
    margin-bottom: 14px;
    display: block;
    position: relative;
    clear: both;
}

.l-form ul, .l-form li
{
    list-style: none;
    padding-left: 23px;
}
.l-form ul
{
    clear: both;
    margin-top: 2px;
    margin-bottom: 2px;
    padding-top: 13px;
    padding-bottom: 2px;
}
.l-form li
{
    float: left;
    overflow: hidden;
    text-align: left;
    /*line-height: 20px;*/
    padding: 0;
    padding-top: 13px;
    padding-bottom: 2px;
}
</style>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" style="padding-top: 50px;font-size: large;">
	<form id="frmUserRole" method="post" class="l-form">
		<input id="userId" name="userId" type="hidden" value="${userId}">
		<ul>
			<li style="width: 100px; text-align: right;">用户：</li>
			<li style="width: 220px; text-align: left;">${userName}</li>
		</ul>
		<ul>
			<li style="width: 100px; text-align: right;">角色：</li>
			<li style="width: 220px; text-align: left;"><input id="roleId" name="roleId" /></li>
		</ul>
	</form>
	</div>
	<script type="text/javascript">
		$(function() {
			$("#roleId").combobox({
			    url:'<%=request.getContextPath()%>/admin/role/getRoleComboData?userId=${userId}',
			    valueField:'value',
			    textField:'text'
			}); 
		});		
	</script>

</body>
</html>


