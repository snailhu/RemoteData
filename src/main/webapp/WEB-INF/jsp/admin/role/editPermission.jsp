<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <jsp:include page="../inc/meta.jsp"></jsp:include>
     <jsp:include page="../inc/easyui.jsp"></jsp:include>
<!--      <script src="<%=request.getContextPath()%>/Content/js/edit-permission.js" type="text/javascript"></script> -->
     <script src="<%=request.getContextPath()%>/static/content/zTree/js/jquery.ztree.all-3.0.min.js" type="text/javascript"></script>
     <link href="<%=request.getContextPath()%>/static/content/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
     <link href="<%=request.getContextPath()%>/static/content/css/edit-permission.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding: 10px; background: #F8F8F8; border: 1px solid #ccc;">
        <form id="frmRolePermission" action="" method="post" >
        <table class="data-form" cellspacing="0" cellpadding="0">
  
            <tr>
                <th scope="row">
                    角色：
                </th>
                 <td>
                 <input type="hidden" id="roleId" name="roleId"/>
                 <input id="roleName" name="roleName" class="easyui-validatebox" value="${roleName}" disabled="true" style="width: 218px" />	
                </td>
            </tr>
        </table>
            <div class="sep-line"></div>
            <ul id="deptTree" fit="true"></ul>
            <div class="sep-line" style="margin-top:20px;"></div>
                
        </form>
    </div>
    <div region="south" border="false" style="text-align: right; padding: 5px 0;background: #F8F8F8;">
        <a class="easyui-linkbutton" iconcls="icon-save" href="javascript:doSubmit('<%=request.getContextPath()%>/role/editPermission');">
            保存</a> <a class="easyui-linkbutton" iconcls="icon-cancel" href="javascript:top.closeMyWindow()">
                取消</a>
    </div>
    <script type="text/javascript">
    	var deptTree;
        $(function () {
        	deptTree = $("#deptTree").tree({
                url: '<%=request.getContextPath()%>/role/tree?roleId=' + '${roleId}',
                checkbox: true,
            });
        });
        function getChecked(){
			var nodes = $("#deptTree").tree('getChecked');
			var s = '';
			for(var i=0; i<nodes.length; i++){
				if (s != '') s += ',';
				s += nodes[i].id;
			}
			alert(s);
		}
        function doSubmit(url) {
        	var nodes = $("#deptTree").tree('getChecked');
        	if (nodes.length > 0) {
        		var permissionItemId = '';
    			for(var i = 0; i < nodes.length; i++){
    				if (permissionItemId != '') permissionItemId += ',';
    				permissionItemId += nodes[i].id;
    			}
    			$.ajax({
    				url: '<%=request.getContextPath()%>/admin/role/editRolePermission?roleId=${roleId}&permissionItemId=' + permissionItemId,
    				cache: false,
    				dataType: "json",
    				success: function(data) {
    					top.showProcess(false);
                     if (data.success) {
                         top.showMsg('提示', data.msg);
                         top.closeMyWindow();
                     }
                     else {
                         top.showMsg('警告', map.msg);
                     }
    				}
    			});
        	} else {
				top.showMsg("提示", "请选择要关联的权限！");
			}
        }
    </script>
</body>

</html>
