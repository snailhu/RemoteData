<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form id="frmRole" action="" method="post" class="l-form">
	<div class="l-group l-group-hasicon">
		<span>系统角色信息</span>
	</div>
		<input type="hidden" name="id" />
	<ul>
		<li style="width: 100px; text-align: right;">角色名称：</li>
		<li style="width: 220px; text-align: left;"><input name="name"
			class="easyui-validatebox" required="true" validtype="length[1,50]"
			style="width: 214px" /></li>
	</ul>
	<br><br>
	<ul>
		<li style="width: 100px; text-align: right;">角色描述：</li>
		<li style="width: 220px; text-align: left;"><textarea
				name="description" style="resize: none; width: 214px"></textarea></li>
	</ul>
</form>
<script type="text/javascript">
	$(function() {
		$('#frmRole').form('load',
				'<%=request.getContextPath()%>/admin/role/editRoleForm?roleId=' + '${roleId}');

	});
</script>
