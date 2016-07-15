<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
	ul{
		padding-top: 5px;
		padding-bottom: 5px;
	}
</style>
         <form id="frmUser" action="" method="post" class="l-form">
           <div class="l-group l-group-hasicon">
            <span>用户信息</span>
              <input type="hidden" name="id" value="${userId}"/>
           </div>     
          <ul>
            <li style="width: 100px; text-align: right;">用户名：</li>
            <li style="width: 225px; text-align: left;">
                <input name="userName" readonly="readonly" class="easyui-validatebox" required="true" validtype="length[1,50]" 
                style="width: 214px;height: 20px;" />
            </li>
        </ul>
<!--           <ul> -->
<!--             <li style="width: 100px; text-align: right;">真实姓名：</li> -->
<!--             <li style="width: 225px; text-align: left;"> -->
<!--               <input name="realName" class="easyui-validatebox"  validtype="length[1,50]" style="width: 214px;height: 20px;" /> -->
<!--             </li> -->
<!--         </ul> -->
<!--        <ul> -->
<!--             <li style="width: 100px; text-align: right;">性别：</li> -->
<!--             <li style="width: 225px; text-align: left;"> -->
<!--                 <input name="gender" type="radio" title="男" value="1" />男<input name="gender" type="radio" title="女" value="0" />女 -->
<!--             </li> -->
<!--         </ul> -->
<!--         <ul> -->
<!--             <li style="width: 100px; text-align: right;">所属部门：</li> -->
<!--             <li style="width: 220px; text-align: left;"> -->
<!--                 <input name="departmentId" id="departmentId" class="easyui-combotree" style="width: 218px;height: 25px;" /> -->
<!--             </li> -->
<!--         </ul> -->
<!--        <ul> -->
<!--             <li style="width: 100px; text-align: left;">状态：</li> -->
<!--             <li style="width: 220px; text-align: left;"> -->
<!--                   <input type="checkbox" name="status" title="是否禁用" checked="checked" value="1" />是否禁用 -->
<!--             </li> -->
<!--         </ul>  -->
       <ul>
           <li style="width: 100px; text-align: right;">新密码：</li>
           <li style="width: 220px; text-align: left;">
              <input name="password" type="password" id="pw1" class="easyui-validatebox" style="width: 214px;height: 20px;" validType="checkPassWord"/>
           </li>
       </ul>
       <ul>
           <li style="width: 100px; text-align: right;">确认密码：</li>
           <li style="width: 220px; text-align: left;">
              <input name="passwordAgin" type="password" id="pw2" class="easyui-validatebox"  validtype="eqPassword[pw1]" style="width: 214px;height: 20px;" />
           <div id="msg" style="color:red;"></div>
           </li>
       </ul>
       
         <ul>
            <li style="width: 100px; text-align: right;">联系方式：</li>
            <li style="width: 220px; text-align: left;">
                <input name="mobile" class="easyui-validatebox" validType="phoneRex"  style="width: 214px;height: 20px;" />
            </li>
        </ul>
         <ul>
            <li style="width: 100px; text-align: right;">邮箱：</li>
            <li style="width: 220px; text-align: left;">
                <input name="email" class="easyui-validatebox"  validType="email" style="width: 214px;height: 20px;" />
            </li>
        </ul>
          
        </form>

    <script type="text/javascript">
        $(function () {
            $('#frmUser').form('load', '<%=request.getContextPath()%>/admin/user/editUserForm' + '?userId=' + '${userId}');
       		//获取部门树
// 			$("#departmentId").combotree({
// 				url : '<%=request.getContextPath()%>/department/tree',
// 				onShowPanel : function() {
// 					$("#departmentId").combotree('reload');
// 				}
// 		    });
       
        });

        function doSubmit(url) {
            var p1 = document.getElementById("pw1").value;
            var p2 = document.getElementById("pw2").value;
            if (p1 != p2) {
                document.getElementById("pw1").focus();
                document.getElementById("msg").innerHTML = "两次输入密码不一致，请重新输入";
                return;
            }
       }
    </script>
<script type="text/javascript">
	function check(){
		var psw = $("#pw1").val();
		if(psw.length < 6)
		    return false;
		if(/[~!@#$%^&*()_+]/.test(psw))
		    return false;
		var number = /[0-9]/.test(psw);
		var lowerCase = /[a-z]/.test(psw);
		var upperCase = /[A-Z]/.test(psw);
		if((number && lowerCase) || (number && upperCase))
		    return true;
		else
			return false;
	}
// 	function checkPass(){
// 		if(!check()){
// 			$.messager.alert("注意", "密码格式不正确!", "error");
// 		}
// 	}
	//扩展easyui表单的验证  
	$.extend($.fn.validatebox.defaults.rules, {
		checkPassWord: {
			validator: function(value){
				if(check()){
					return true;
				}else{
					return false;
				}
			},
			message: '密码格式不正确'
		},
		phoneRex: {
		validator: function(value){
		var rex=/^1[3-8]+\d{9}$/;
		//var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
		//区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
		//电话号码：7-8位数字： \d{7,8
		//分机号：一般都是3位数字： \d{3,}
		 //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/		 
		var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
		if(rex.test(value)||rex2.test(value))
		{
		  // alert('t'+value);
		  return true;
		}else
		{
		 //alert('false '+value);
		   return false;
		}
		  
		},
		message: '请输入正确电话或手机格式'
		}
	});
</script>
