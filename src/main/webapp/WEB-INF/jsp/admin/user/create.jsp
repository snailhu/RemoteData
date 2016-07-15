<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style>
	ul{
		padding-top: 5px;
		
		padding-bottom: 5px;
	}
	.input {
		width: 300px;
		height: 25px;
		border: 1px solid #20B2AA;
	}
</style>
<form id="frmUser"  method="post" class="l-form">
	<div class="l-group l-group-hasicon">
		<span>用户信息</span>
	</div>

	<ul>
		<li style="width: 100px; text-align: right;">用户名：</li>
		<li style="width: 225px; text-align: left;"><input name="userName"
			class="easyui-validatebox" required="true" validtype="length[1,50]"
			style="width: 214px;height: 20px;" /></li>
	</ul>
<!-- 	<ul> -->
<!-- 		<li style="width: 100px; text-align: right;">真实姓名：</li> -->
<!-- 		<li style="width: 225px; text-align: left;"><input -->
<!-- 			name="realName" class="easyui-validatebox" validtype="length[1,50]" -->
<!-- 			style="width: 214px;height: 20px;" /></li> -->
<!-- 	</ul> -->
<!-- 	<ul> -->
<!-- 		<li style="width: 100px; text-align: right;">性别：</li> -->
<!-- 		<li style="width: 225px; text-align: left;"><input name="gender" -->
<!-- 			type="radio" title="男" checked="checked" value="1" />男<input -->
<!-- 			name="gender" type="radio" title="女" value="0" />女</li> -->
<!-- 	</ul> -->
<!-- 	<ul> -->
<!-- 		<li style="width: 100px; text-align: right;">所属部门：</li> -->
<!-- 		<li style="width: 225px; text-align: left;"><input -->
<!-- 			name="departmentId" id="departmentId" class="easyui-combotree" required="true" value="${departmentId}" -->
<!-- 			style="width: 218px;height: 25px;" /> -->
<!-- 			<span class="required">*</span> -->
<!-- 			</li> -->
<!-- 	</ul> -->
<!-- 	<ul> -->
<!-- 		<li style="width: 100px; text-align: left;">状态：</li> -->
<!-- 		<li style="width: 220px; text-align: left;"><input name="status" -->
<!-- 			type="checkbox" title="是否禁用" value="1" />是否禁用</li> -->
<!-- 	</ul> -->
	<ul>
		<li style="width: 100px; text-align: right;">密码：</li>
		<li style="width: 225px; text-align: left;"><input
			type="password" name="password" id="pw1" class="easyui-validatebox"
			required="true"  style="width: 214px;height: 20px;" validType="checkPassWord"/>
<!-- 			<span class="required">*</span> -->
			<div id="msg1" style="color:red;"></div></li>
	</ul>
	<ul>
		<li style="width: 100px; text-align: right;"></li>
		<li style="width: 300px; text-align: left;margin-left: 100px;">
			<span class="required"> 密码最小长度为6位、必须包含数字与字母、 </span><br/> 
			<span class="required"> 不允许包含 @、#、$、%等特殊字符 </span>
		</li>
	</ul>
	<ul>
		<li style="width: 100px; text-align: right;">确认密码：</li>
		<li style="width: 225px; text-align: left;"><input
			type="password" name="passwordAgin" id="pw2" 
			class="easyui-validatebox" required="true" validtype="eqPassword[pw1]"
			style="width: 214px;height: 20px;" />
<!-- 			<span class="required">*</span> -->
			<div id="msg2" style="color:red;"></div></li>
	</ul>

	<ul>
		<li style="width: 100px; text-align: right;">联系方式：</li>
		<li style="width: 225px; text-align: left;"><input name="mobile"
			id="mobile" class="easyui-validatebox" validType="phoneRex"
			style="width: 214px;height: 20px;" /></li>
	</ul>

	<ul>
		<li style="width: 100px; text-align: right;">邮箱：</li>
		<li style="width: 225px; text-align: left;"><input name="email"
			id="email" class="easyui-validatebox" validType="email"
			style="width: 214px;height: 20px;" /></li>
	</ul>

</form>


<script type="text/javascript">

// 	获取部门树
// 	$("#departmentId").combotree({
// 		url : '<%=request.getContextPath()%>/department/tree',
// 		onShowPanel : function() {
// 			$("#departmentId").combotree('reload');
// 		}
// 	});
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
// 		var psw = $("#pw1").val();
// 		if(psw.length >0 && !check()){
// 			$.messager.alert("注意", "密码格式不正确!", "error");
// 		}
// 	}
	function ckphone(ph){
		var phone = $("#mobile").val();
		var reg = /^1[3578]\d{9}$/;
		if(!reg.test(phone)){
			alert("手机号码格式不对！");
		}
	}
	function ckemail(n){
		var email = $("#email").val();
		var reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
		if(! reg.test(email)){
			alert("邮箱格式不对！");
		}
	}
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
