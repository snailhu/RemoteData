<%@ page language="java" import="java.util.*" pageEncoding="UtF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  
  	 <link href="${pageContext.request.contextPath}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
 
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/content/css/default.css"  type="text/css"/>	
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdatetimeinput.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxcalendar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxtooltip.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/globalization/globalize.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdropdownlist.js"></script>
 
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/scripts/demos.js"></script>  
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxdatatable.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxtreegrid.js"></script>     

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jqwidgets/jqxbuttons.js"></script>

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/content/jeDate/jedate/jedate.js"></script>
    
<style>
	.breadcrumbs{
		height:45px;
	}	
	.breadcrumbs .breadcrumb{
		padding:10px 5px;
	}
	.breadcrumb>li+li:before {
    	padding: 0px;
    	color: #333;
    	content: "\003e";
	}
	.breadcrumbs .breadcrumb li a{		
		padding:0px;
	}
	.flywheel-li{
		height: 30px;
    	width: 80px;
    	color: white;
    	background-color: #94A732;
    	border-width: 1px;
    	cursor: pointer;
	}
	.top-li{
		height: 30px;
    	width: 80px;
    	color: white;
    	background-color: #94A732;
    	border-width: 1px;
    	cursor: pointer;
	}	
	.dateStyle{
		float:left;
	}
	.dateStyle span{
		margin-right: 10px;
	}
	.dateStyle input{
		font-size: 14px;
    	text-align: center;
    	width: 175px;
    	height: 30px;
    	margin-right: 30px;
    	color: #b2b2b2;
    	background-color: #F8F8F8;
    	border-width: 1px;
    	cursor: pointer;
	}
	.dateSelect button{
		height: 35px;
    	width: 100px;
    	color: white;
    	background-color: #4B92DD;
    	border-width: 1px;
    	cursor: pointer;
	}
	.dateSelect select{
		height: 35px;
    	width: 100px;
    	color:  #b2b2b2;
    	/* background-color: #4B92DD; */
    	border-width: 1px;
    	cursor: pointer;
    	margin-right:20px;
	}
	#dateStart-div,#dateEnd-div{
		display:inline;
	}
	.row {
		margin:0px !important
	}
	.page-header{
		padding:0px !important;
		border-top: 1px solid #DEDEDE;
    	background-color: white;
    	height: 90px;
    	margin-left: -20px;
    	width:950px;
	}
	.widget-box{border-bottom:none;}
	.widget-body{border:none;}
	.datainp{
		width: 300px;
		height: 25px;
	}
	.dateRange{
		height: 40px;
    	line-height: 40px;
    	padding-left: 40px;
    	color: #0076ca;
    	/* border-bottom: 1px solid #DEDEDE; */	
    	font-family: "微软雅黑";	
    	font-size: 16px;
    	margin-top: -45px;
    	margin-left: -20px;
	}
	.dateSelect{
		height: 90px;
		font-size: 14px;
    	font-weight: 900;
    	padding: 25px 0 35px 30px;
	}
	.widget-header {
    	width: 894px;
    }
    #jqxButton_addgroup,#jqxButton_submitgroup{
    	overflow:hidden;
    	padding: 0px;
    	line-height: 30px;
    	border-radius:20px;
    	cursor: pointer;
    }
    #jqxButton_addgroup{
    	margin-left:1px;
    	margin-top:10px;
    }
    #jqxButton_submitgroup{
    	background-color: #1C76C5;
    	color: white; 
    	margin-left:120px;
    	margin-top:-32px 	
    }
    #id_dplist_template{
    	margin-left:550px;
    	margin-top:-32px;
    }
    .groupButton{
    	margin-bottom:10px;
    }
    .new_hr{
    	margin:25px 0px 10px -20px;
    }
    .parameter-list{
    	margin-top:35px;
    }
    #jqxWidget{
    	height: 40px;
    	line-height: 40px;
    	color: #0076ca;
    	/*padding-left: -20px;
    	border-bottom: 1px solid #DEDEDE;*/
    	font-size:16px;
		margin-bottom:200px;
    }
    #jqxWidget .button{
    	padding: 9px;   	
    	background-color: #fcf9e3;
    }
    #jqxWidget .button button{
    	float: right;
    	width: 90px;
    	height: 30px;
    	margin-left: 20px;
    	border: 1px solid #DADADA;
    	font-size: 14px;
    	background-color: #b0b0b0;
    	color: white;
    	font-weight: normal;
    	line-height: 1;    	 
     	overflow:hidden;
    }
    .keepTemplet{
    	background-color: #efa90e;
    	text-shadow: 0 0 0 #FFF;
     	opacity: 1;  	
    }
    .deleteGroup{
    	text-shadow: 0 0 0 #FFF;
     	opacity: 1;
    }
    .down{
    	position:fixed;
    	bottom:0px;
    	z-index:10;
    }
    
    .selftoolbar {
    display: inline-block;
    padding: 0 10px;
    line-height: 37px;
    float: right;
    position: relative;
}
</style>

	</head>
  
<body> 
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"  >
	  <div class="modal-dialog" role="document" style="margin:30px -200px">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">分组配置</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	        	<div class="radio">
					<label>
					<input type="radio" name="optionsRadios" id="optionsRadios1" value="1" checked> 单个y轴
					</label>
				</div>
				<div class="radio">
					<label>
					<input type="radio" name="optionsRadios" id="optionsRadios2" value="2">
					双Y轴
					</label>
				</div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">单y轴名称</label>
	            <input type="text" class="form-control" id="firsty-name">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">第二个y轴名称</label>
	            <input type="text" class="form-control" id="secondy-name">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="postTemplate()">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!--保存为模板弹出框-->
    <div class="modal fade" id="id_Modal_template" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"  >
	  <div class="modal-dialog" role="document" style="margin:50px auto;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="Modal_template">保存为模板</h4>
			<input id="Modal_template_input" style="display:none"/>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">模板名称</label>
	            <input type="text" class="form-control" id="id_template_name">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">模板描述</label>
	            <input type="text" class="form-control" id="id_template_description">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="postTemplate()">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	        
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>
			<ul class="breadcrumb">
				<li>					
					<a href="${pageContext.request.contextPath}/conditionMonitoring"><i class="icon-home home-icon"></i></a>
					<span class="menu-text">${nowSeries}</span>
				</li>
	
				<li>
					<a href="javascript:void(0);"  onclick="this.setAttribute('disabled','disabled')"></a>
					<span class="menu-text">${nowStar}</span>
				</li>
				
				<li class="active"  value="">
					<span class="menu-text" id="SatelliteComponents" name="${nowParameterTypeValue}">${nowParameterTypeName}</span>					
				</li>
			</ul><!-- .breadcrumb -->		
		</div>	
		<div class="page-content">
			<div class="page-header">
			<div class="dateRange">日期范围</div>
				<div class="dateSelect">
				<button id="btn_flywheel"  style="display:none;">
					飞轮
				</button>
				<button id="btn_top"  style="display:none;">
					陀螺
				</button>
				
				<div class="dateStyle">
					<span>开始日期</span>
					<div id="dateStart-div">
						<input class="datainp" id="dateStart" type="text" placeholder="--请选择--" readonly>
					</div>
				</div>
				<div class="dateStyle">
					<span>结束日期</span>
					<div id='dateEnd-div'>
						<input class="datainp" id="dateEnd" type="text" placeholder="--请选择--" readonly>
					</div>				
				</div>
				<div class="dateStyle">
				<select class="form-control" id="edit_component" name="component" style="display:none;">
					<option value="">选择设备<span class="caret"></span></option>
				</select>
				</div>
				<button style="height: 35px;"  id='jqxButton-getParameters' onMouseOut="this.style.backgroundColor=''" onMouseOver="this.style.backgroundColor='#00A1CB'">获取参数</button>
				</div>
				<div style="clear:both"></div>
			</div>
			<!-- /.page-header -->

		<div style="margin-left:-20px;">
			<div class="col-xs-12 col-sm-12">
				<div class="widget-box">
					<div class="widget-header" id="change-search-box" data-action="collapse">
						<h4>常用模板</h4>
						<div class="selftoolbar">
							<a href="javascript:void(0);" >
								<img id="toolimg" src="${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png">
							</a>
						</div>						
					</div>
					<div class="widget-body">									        					        
						<div class="widget-main">				
					        <div id="id_templateTreeGrid"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
		 </div>
		  	
		 <!--<div class="hr hr32 hr-dotted"></div>-->
		 
			<div class="row">
			<div class="groupButton col-xs-12">
					<div id='jqxButton_addgroup' onMouseOut="this.style.backgroundColor=''" onMouseOver="this.style.backgroundColor='#aaa'">添加分组</div>
  					<div id='jqxButton_submitgroup' onMouseOut="this.style.backgroundColor=''" onMouseOver="this.style.backgroundColor='#00A1CB'">提交分组</div>
  					<!--<button onclick="getCleared()">清空已选参数</button>-->
  					<div id="id_dplist_template"></div>
  		 	</div>
  		 		
  				<div id='jqxWidgett'>
			        <div id="treeGrid"></div>			       	
			     </div>
				      		     
			</div><!-- /.row -->
			
			<div class="new_hr hr hr32 hr-dotted"></div>
			
			<div class="row">
			<div class="col-xs-12">	
  				<div id='jqxWidget'>
			              已有分组：		       	
			    </div>
			 </div>
			 </div>
									
		</div><!-- /.page-content -->
			
	</div><!-- /.main-content -->
	
<script type="text/javascript">
		//管理员通过点击按钮选择陀螺或者飞轮
		function setSatelliteComponents(type){
		if(type == 'flywheel'){
			$('#SatelliteComponents').html('飞轮');
			$('#SatelliteComponents').attr('name','flywheel');			
			}
			else
			{
				$('#SatelliteComponents').html('陀螺');
				$('#SatelliteComponents').attr('name','top');	
			}
		}
		
		
	jeDate({
		dateCell:"#dateStart",//直接显示日期层的容器，可以是ID  CLASS
		ormat:"YYYY-MM-DD hh:mm:ss", //日期格式
minDate:"1900-01-01 00:00:00", //最小日期
maxDate:"2099-12-31 23:59:59", //最大日期
isinitVal:false, //是否初始化时间
isTime:true, //是否开启时间选择
//isClear:true, //是否显示清空
festival:false, //是否显示节日
zIndex:999,  //弹出层的层级高度
marks:null, //给日期做标注
choosefun:function(val) {},  //选中日期后的回调
clearfun:function(val) {},   //清除日期后的回调
okfun:function(val) {}       //点击确定后的回调
	});
	jeDate({
		dateCell:"#dateEnd",//直接显示日期层的容器，可以是ID  CLASS
		format:"YYYY-MM-DD hh:mm:ss",//日期格式
		isinitVal:false, //是否初始化时间
		festival:false, //是否显示节日
		isTime:true, //是否开启时间选择
		ishmsLimit:false,                     //时分秒限制
		ishmsVal:true,                        //是否限制时分秒输入框输入，默认可以直接输入时间
		//minDate:"2014-09-19 00:00:00",//最小日期
		maxDate:jeDate.now(0), //设定最大日期为当前日期
	});
	$(function() {	
		//左菜单栏
		$("#conditionmonitoring-img").attr("src","${pageContext.request.contextPath}/static/new/img/images/a_86.png");
		$("#conditionmonitoring-text").css("color","#5d90d6");	
		//intTemplateList();
		var  flag=false;
		//$(".selftoolbar").click(function(){
		$('#change-search-box').click(function(){
		if(flag){
			$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia.png")
			$(".widget-body").slideUp("slow");
			flag=false;
		}else{
			initTemplateTree();
			$("#toolimg").attr("src","${pageContext.request.contextPath}/static/imgs/DataImport_manage/xia2.png")
			$(".widget-body").css("border","1px solid #ccc");
    		$(".widget-body").slideDown("slow");   		
			flag=true;
		}
		
		})
		
		$('#edit_component').append("<option value='flywheel'>飞轮</option>");
		$('#edit_component').append("<option value='top'>陀螺</option>");
		$("#edit_component").change(function(){		
		 	var component = $('#edit_component').val();	
		 	setSatelliteComponents(component);
	    });
		//陀螺或者飞轮权限选择按钮的显示与否
		var activeUser = '${activeUser}';
		if(activeUser != ''){
			var permissionItemsJSON = '${activeUser.permissionItemsJSON}';
			var map = $.parseJSON(permissionItemsJSON); 
			if((map.flywheel == 'flywheel') & (map.top == 'top')){
				$("#btn_flywheel").hide();
				$("#btn_top").hide();
				$("#edit_component").show();
			}
			else{
			$("#btn_flywheel").hide();
			$("#btn_top").hide();
			$("#edit_component").hide();
			}
		}
		
		 $("#btn_flywheel").click( function ()  {    
		 	setSatelliteComponents('flywheel');
		 });
		 $("#btn_top").click( function ()  {    
		 	setSatelliteComponents('top');
		 });
	
	
//     	$("#dateStart").jqxDateTimeInput({width: '175px', height: '30px'});
//     	$("#dateEnd").jqxDateTimeInput({width: '175px', height: '30px'});
        
         $("#btn_flywheel").jqxButton({ width: '60', height: '30'});
         $("#btn_top").jqxButton({ width: '60', height: '30'});
         $("#edit_component").jqxButton({ width: '60', height: '20'});
         
		 $("#jqxButton-getParameters").jqxButton({ width: '100', height: '30'});	
		 $("#jqxButton-getParameters").click( function ()  {    
		 	var beginDate = $("#dateStart").val();
		 	var endDate = $("#dateEnd").val();		 	
		 	var Series_current="${nowSeries}";
		 	var Star_current="${nowStar}";
		 	//var type_current="${nowParameterTypeName}";
		 	//var type_current="${nowParameterTypeValue}";
		 	var type_current = $('#SatelliteComponents').attr('name');
		 	console.log("当前"+Series_current+"----"+Star_current);
            var url = "${pageContext.request.contextPath}/getConstraint?beginDate="+beginDate+"&endDate="+endDate+"&Series_current="+Series_current+"&Star_current="+Star_current+"&type_current="+type_current;
            updateParamTree(url);
		 });
		 
		 $("#jqxButton_addgroup").jqxButton({ width: '95', height: '30'});	
		 $("#jqxButton_addgroup").click(function(){
		 	getSelected();
		 });
		 
		 $("#jqxButton_submitgroup").jqxButton({ width: '95', height: '30'});	
		 $("#jqxButton_submitgroup").click(function(){
		 	submitGroup();
		 });
		 
		 initTemplateTree();
		 intTemplateList();
		 $(".widget-body").slideUp("slow"); 
		 //$('#change-search-box').click();
	});
		
	//刷新参数列表树
	function updateParamTree(url){
       var source =
       	{
           dataType: "json",
           dataFields: [
               { name: 'id', type: 'number' },
               { name: 'parentId', type: 'number' },
               { name: 'name', type: 'string' },
               { name: 'max', type: 'number' },
               { name: 'min', type: 'number' },
               { name: 'yname',type:'string'}
           ],
           hierarchy:
           {
               keyDataField: { name: 'id' },
               parentDataField: { name: 'parentId' }
           },
           id: 'id',
           url: url
       };
       var dataAdapter = new $.jqx.dataAdapter(source);
       $("#treeGrid").jqxTreeGrid(
       {
           width: 760,                
           source: dataAdapter,
           sortable: true,
           editable: true,
           checkboxes: true,
           theme: 'energyblue',
           hierarchicalCheckboxes: true,              	
           columns: [
	             { text: '参数名称',  dataField: 'name',editable: false, width: 310 },
	             { text: 'ID',  dataField: 'id',editable: false, width:200, hidden: true },
	             { text: '最大值', dataField: 'max', width: 150 },
	             { text: '最小值', dataField: 'min', width: 150 },
	             { text: 'Y轴', dataField:'yname',width:150,columnType:'template',
	             	cellsRenderer: function (row, column, value, rowData) {if(value=="0") {return "Y1"};if(value=="1"){return "Y2"}},
					createEditor: function (row, cellValue, editor, cellText, width, height) {
					  var source = ["Y1", "Y2"];
					  editor.jqxDropDownList({selectedIndex: 0,autoDropDownHeight: true, placeHolder:'请设置Y轴', source: source, width: '100%', height: '100%' });		 
					},
					initEditor: function (row, cellValue, editor, cellText, width, height) {
						editor.jqxDropDownList('selectItem', cellValue);
					},
					getEditorValue: function (row, cellValue, editor) {
					    var yname = editor.val();
						//alert(row+yname);
						$("#treeGrid").jqxTreeGrid('setCellValue', row, 'yname', yname);
						return editor.val();
					}				
				}                     
			]
       });
	} 
	       	
    //添加分组功能
    var JsonG = {}
	var AllRowselect = [];
	var j=0;
		/*//模板树提交分组按钮响应事件
        function getTemplate(){      	
            var groupObject={}
            var selectRow = []
            var rowindex = $("#id_templateTreeGrid").jqxTreeGrid('getCheckedRows');
            var stringName="参数名：";
            var chkObjs = $('input:radio:checked').val();
            if(rowindex.length>0){        	
                for(i=0;i<rowindex.length;i++){
                	var rowObject={}
                    var value = rowindex[i].name;
                    rowObject.id=rowindex[i].id
                    rowObject.name=rowindex[i].name;
                    rowObject.max=rowindex[i].max;
                    rowObject.min=rowindex[i].min;
                    rowObject.yname=rowindex[i].yname;
                    if(rowObject.yname=="y2"){
                    	chkObjs=2;
                    }
                    selectRow.push( rowObject);
                    stringName+=value+",";
                }
            }
            groupObject.id=j
            groupObject.secectRow = selectRow;
            groupObject.Ycount = chkObjs;
            groupObject.Y1name=$("#firsty-name").val();
            if(chkObjs=="2"){
            	groupObject.Y2name= $("#secondy-name").val();
            }
            //在已分组列表上添加“删除”和“保存为模板”按钮
            var btn_savemodel="<button type='button' class='keepTemplet close' onclick='saveToLineTemplate(this)' style='background-color: #efa90e;'><span aria-hidden='true'>保存为模板</span></button>";
            var group= $("<div name="+j+" class='button alert alert-block alert-success' role='alert'> <button type='button' class='deleteGroup close' onclick='clearGroup(this)'><span aria-hidden='true'>删除分组</span></button>"+btn_savemodel+stringName+"</div>")
            $('#jqxWidget').append(group)
            
            AllRowselect[j]=groupObject;
            j++;           
            JsonG.alldata=AllRowselect;
            
        }*/
	//添加分组按钮响应事件
      function getSelected(){      	
          var groupObject={};
          var selectRow = [];
          var rowindex = $("#treeGrid").jqxTreeGrid('getCheckedRows');            
          var stringName="参数名：";
          var chkObjs = $('input:radio:checked').val();
          var beginDate = $("#dateStart").val();
		  var endDate = $("#dateEnd").val();
          if(rowindex.length>0){        	
              for(i=0;i<rowindex.length;i++){
              	var rowObject={}
                  var parentId = rowindex[i].parentId;
              	  console.log("parentId: " + parentId);
                  if(parentId != 0){
	                  var value = rowindex[i].name;
	                  rowObject.id=rowindex[i].id
	                  rowObject.name=rowindex[i].name;
	                  rowObject.max=rowindex[i].max;
	                  rowObject.min=rowindex[i].min;
	                  var yname = rowindex[i].yname;
	                  if(yname=='Y2'){
	                  //这里统一一下（Y1轴的话用大写的‘Y1’,Y2轴用大写的‘Y2’）
	                	  rowObject.yname=1;
	                  }else{
		                  rowObject.yname=0;
	                  }
	                  selectRow.push( rowObject);
	                  stringName+=value+",";
	              }
              }
           groupObject.id=j
           groupObject.secectRow = selectRow;
//            groupObject.Ycount = chkObjs;
//            groupObject.Y1name=$("#firsty-name").val();
//            if(chkObjs=="2"){
//            	groupObject.Y2name= $("#secondy-name").val();
//            }
			//设置每一组的开始和结束时间
			groupObject.beginDate = beginDate;
			groupObject.endDate = endDate;
			groupObject.nowSeries = "${nowSeries}";
			groupObject.nowStar ="${nowStar}";
			groupObject.component = $('#SatelliteComponents').attr('name');	
           //在已分组列表上添加“删除”和“保存为模板”按钮
            var btn_savemodel="<button type='button' class='keepTemplet close' onclick='saveToLineTemplate(this)' style='background-color: #efa90e;'><span aria-hidden='true'>保存为模板</span></button>";
			//var btn_savetotemplate_id = "btn_savetotemplate_id_"+j;
	        //var btn_savemodel="<button id='"+btn_savetotemplate_id+"'type='button' class='keepTemplet close' data-toggle='modal' data-target='#id_Modal_template' style='background-color: #efa90e;'><span aria-hidden='true'>保存为模板" +j+"  </span></button>";
		    var group= $("<div name="+j+" class='button alert alert-warning alert-dismissible' role='alert'> <button type='button' class='deleteGroup close' onclick='clearGroup(this)'><span aria-hidden='true'>删除分组</span></button>"+btn_savemodel+stringName+"</div>")            
	        $('#jqxWidget').append(group)            
	        AllRowselect[j]=groupObject;
	        j++;           
	        JsonG.alldata=AllRowselect;   
       	}else{
       		alert("至少请选择一行参数！");
       	}  
      }
        
		
        //设定模板的名称和描述，隐藏保存为模板弹出框；
        var templateNmae_dialog=" ";
		var templateDescription_dialog=" ";
		var JsonParams = {};
		//一个参数对象数组
        var paramarray=[];
        //把已经生成的分组保存为曲线模板
        function saveToLineTemplate(obj){
			$(id_Modal_template).modal('show');			
        	var GroupId = $(obj).parent('.alert').attr("name")
        	var group=AllRowselect[GroupId].secectRow
        	var template=group
        	paramarray = [];
        	for(i=0;i<template.length;i++)
        	{
        		var param={}
        		param.name=template[i].name;
        		param.max=template[i].max;
        		param.min=template[i].min;
        		param.yname=template[i].yname;
        		paramarray.push(param);
        		//alert(param.name+"max:"+param.max+"min:"+param.min+param.yname);
        	}
        	/*var a=AllRowselect[1].secectRow;
        	var b=a[1];
        	c=b.name;*/
        	//alert(a.getname());
        	JsonParams.alldata=paramarray;
        }
        function postTemplate()
        {	        	
        	templateNmae_dialog=$(id_template_name).val();
        	templateDescription_dialog=$(id_template_description).val();
        	$(id_Modal_template).modal('hide');	
        	$.post('${pageContext.request.contextPath}/saveTotemplate',        
        	{
        		'templateNmae':templateNmae_dialog,
        		'templateDescription':templateDescription_dialog,
        		'JsonParams':JSON.stringify(paramarray)        	
        	},
        	function(data){
        		//添加模板后刷新常用模板
        		initTemplateTree();
        		//添加模板后刷新模板下拉框
        		intTemplateList(); 
        	})
        	$(id_template_name).val("");
        	$(id_template_description).val("")
        	      	
        }
        //删除已经生成的分组
     	function clearGroup(obj){
			var clearGroupId = $(obj).parent('.alert').attr("name")
			$(obj).parent('.alert').remove();
			AllRowselect.splice(parseInt(clearGroupId),1)          
        } 
        //清空已选参数按钮响应事件            
         function getCleared(){      
            var value = $("#treeGrid").jqxTreeGrid('getCellValue', 1, 'yname');            
            var value2 = $("#treeGrid").jqxTreeGrid('getCellValue', 1, 'max');
            //var value = $("#id_dplist_template").jqxTreeGrid('getCellValue', 2, 'yname');
            //var value2 = $("#id_dplist_template").jqxTreeGrid('getCellValue', 2, 'max');
            alert(value2+"---"+value);
        } 
        //提交分组响应事件
        function submitGroup(){
        $.post('${pageContext.request.contextPath}/showPanel',        
        	{
        		'JsonG':JSON.stringify(AllRowselect)        	
        	},function(){
        		window.location.href="${pageContext.request.contextPath}/showPanel"
        	})            
        } 
                
        //初始化选择模板下拉框
       	function intTemplateList(){
	            var url_templatelist = "${pageContext.request.contextPath}/getTemplateList";
	            //var url_templatelist = "getTemplateList";
	            var source_templatelist =
	            {
	                datatype: "json",
	                datafields: [
	                    { name: 'name' },
	                    { name: 'id' }
	                ],
	                url: url_templatelist,
	                async: true
	            };
	            var dataAdapter = new $.jqx.dataAdapter(source_templatelist);
	
	            // Create a jqxDropDownList
	            $("#id_dplist_template").jqxDropDownList({
	                selectedIndex: 2, 
	                source: dataAdapter, 
	                displayMember: "name", 
	                valueMember: "id",
	                placeHolder:"请选择模板",
	                //theme: 'energyblue',
	                width: 200, 
	                height: 30
	            });
	
	            // subscribe to the select event.
	            $("#id_dplist_template").on('select', function (event) {
	                if (event.args) {            
	                    var item = event.args.item;
	                    if (item) {
	                    var url = "${pageContext.request.contextPath}/getParamsByTemplateId?templateId="+item.value;
	                    updateParamTree(url);                                           
	                    }
	                }
	            });
            }
            
            //常用曲线模板树 
            function initTemplateTree()
            { 
	        	var url_template = "${pageContext.request.contextPath}/getAllTemplate";
	            var source_template =
	            {
	                dataType: "json",
	                dataFields: [
	                    { name: 'id', type: 'number' },                    
	                    { name: 'name', type: 'string' },
	                    { name: 'max', type: 'number' },
	                    { name: 'min', type: 'number' },
	                    { name: 'yname',type:'string'},
	                    { name: 'templateName', type: 'string' },
	                    { name: 'templateid', type: 'number' },
	                    { name: 'rowid', type: 'number'},
	                    { name: 'parentid',type: 'number'}
	                ],
	                hierarchy:
	                {
	                    keyDataField: { name: 'rowid' },
	                    parentDataField: { name: 'parentid' }
	                    //root: "templateid"
	                },
	                id: 'rowid',
	                url: url_template
	            };
	            var data_templateTree = new $.jqx.dataAdapter(source_template);         
	            $("#id_templateTreeGrid").jqxTreeGrid(
	            {
	                width: 880,
	                source: data_templateTree,
	                sortable: true,
	                editable: false,
	               	//checkboxes: true,
	               	showToolbar: true,
	               	toolbarHeight: 35,
                	altRows: true,
	               	hierarchicalCheckboxes: false,
	               	renderToolbar: function(toolBar)
                	{
	                    var toTheme = function (className) {
	                        if (theme == "") return className;
	                        return className + " " + className + "-" + theme;
	                    }
	                    //添加删除按钮
	                    var container = $("<div style='overflow: hidden; position: relative; height: 100%; width: 100%;'></div>");
	                    var buttonTemplate = "<div style='float: left; padding: 3px; margin: 2px;'><div style='margin: 4px; width: 16px; height: 16px;'></div></div>";                    
	                    var deleteButton = $(buttonTemplate);                   
	                    container.append(deleteButton);                    
	                    toolBar.append(container);					
	                    deleteButton.jqxButton({ cursor: "pointer", disabled: true, enableDefault: false,  height: 25, width: 25 });
	                    deleteButton.find('div:first').addClass(toTheme('jqx-icon-delete'));
	                    deleteButton.jqxTooltip({ position: 'bottom', content: "删除模板"});
	                    var updateButtons = function (action) {
	                        switch (action) {
	                            case "Select":                              
	                                deleteButton.jqxButton({ disabled: false });                                
	                                break;
	                            case "Unselect":
	                                deleteButton.jqxButton({ disabled: true });
	                                break;
	                            case "Edit":
	                                deleteButton.jqxButton({ disabled: true });                              
	                                break;
	                            case "End Edit":
	                                deleteButton.jqxButton({ disabled: false });
	                                break;
	                        }
	                    }
	                    var rowKey = null;
	                    $("#id_templateTreeGrid").on('rowSelect', function (event) {
	                        var args = event.args;
	                        rowKey = args.key;
	                        updateButtons('Select');
	                    });
	                    $("#id_templateTreeGrid").on('rowUnselect', function (event) {
	                        updateButtons('Unselect');
	                    });
	                    $("#id_templateTreeGrid").on('rowEndEdit', function (event) {
	                        updateButtons('End Edit');
	                    });
	                    $("#id_templateTreeGrid").on('rowBeginEdit', function (event) {
	                        updateButtons('Edit');
	                    });
	
	                    deleteButton.click(function () {	                    
	                        if (!deleteButton.jqxButton('disabled')) {
	                            var selection = $("#id_templateTreeGrid").jqxTreeGrid('getSelection');
	                            if (selection.length > 1) {
	                                var keys = new Array();
	                                var selecteditems = new Array();
	                                for (var i = 0; i < selection.length; i++) {
	                                    var key = $("#id_templateTreeGrid").jqxTreeGrid('getKey', selection[i]);
	                                    keys.push($("#id_templateTreeGrid").jqxTreeGrid('getCellValue', key, 'templateid'));	                                    	                                    
	                                	selecteditems.push(key);
	                                }
	                                $.post('${pageContext.request.contextPath}/deleteTemplates',        
								        	{
								        		'templateIds' : keys.join(',') ,						        		    	
								        	});
	                                $("#id_templateTreeGrid").jqxTreeGrid('deleteRow', selecteditems);
	                                	                                	                                
	                            }
	                            else {
	                            var b=$("#id_templateTreeGrid").jqxTreeGrid('getRow', rowKey);	                               
	                                var value = $("#id_templateTreeGrid").jqxTreeGrid('getCellValue', rowKey, 'templateid');	    
									$.post('${pageContext.request.contextPath}/deleteTemplates',        
								        	{
								        		'templateIds' : value,						        		    	
								        	});
	                              $("#id_templateTreeGrid").jqxTreeGrid('deleteRow', rowKey);   
	                            }
	                            updateButtons('delete');
	                            intTemplateList();
	                        }
	                    });
	                },              	
	                columns: [
	                  { text: '模板名称',  dataField: 'templateName', editable: false, width: 210 },
	                  { text: '模板包含参数',  dataField: 'name', width: 220 },
	                  //{ text: '模板ID',   dataField: 'templateid',editable: false, width:180, hidden: false },
	                  { text: '最大值',   dataField: 'max', width: 150 },
	                  { text: '最小值',    dataField: 'min', width: 150 },
	                  { text: 'Y轴',     dataField: 'yname', width:150, columnType:'custom',
	                  	//cellsRenderer: function (row, column, value, rowData) {if(value=="添加到分组") {return "<input type='button' value='删除模板'></input>"}},
	                  	cellsRenderer: function (row, column, value, rowData) { if(value=="0") {return "Y1";} else if(value=="1"){return "Y2"} else{ return "";}}, 
	                  	createEditor: function (row, cellValue, editor, cellText, width, height) {
						  var source = ["Y1", "Y2"];
	                      editor.jqxDropDownList({autoDropDownHeight: true, source: source, width: '100%', height: '100%' });		 
						},
						initEditor: function (row, cellValue, editor, cellText, width, height) {
							editor.jqxDropDownList('selectItem', cellValue);
						},
						getEditorValue: function (row, cellValue, editor) {
							return editor.val();
						}
	                  }                                     
	                ]
	            });
            }	
</script>
   		 
  </body>
</html>