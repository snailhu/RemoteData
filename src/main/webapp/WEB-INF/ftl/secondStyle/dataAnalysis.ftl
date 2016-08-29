<@override name="content_left">
	<div class="sidebar" id="sidebar">	
	    <div class="sidebar-collapse" id="sidebar-collapse">
	        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	    	<ul id="left_con" class="nav nav-list">
		    	<li><a href="#"> <i class="glyphicon glyphicon-certificate"></i> <span
					class="menu-text"> 飞轮 </span>
				</a></li>
	
				<li><a href="#"> <i class="glyphicon glyphicon-certificate"></i> <span
					class="menu-text"> 陀螺 </span>
				</a></li>
			<ul>
	    </div>	
	</div>
</@override>
<@override name="content_right">	
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
	<link rel="stylesheet" href="${base}/static/content/css/default.css"  type="text/css"/>	
    
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdatetimeinput.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcalendar.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxtooltip.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/globalization/globalize.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdropdownlist.js"></script>
 
    <script type="text/javascript" src="${base}/static/scripts/demos.js"></script>  
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdatatable.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxtreegrid.js"></script>     

    <script type="text/javascript" src="${base}/static/jqwidgets/jqxbuttons.js"></script>

	<link type="text/css" rel="stylesheet" href="${base}/static/content/jeDate/jedate/skin/jedate.css">
    <script type="text/javascript" src="${base}/static/content/jeDate/jedate/jedate.js"></script>
    
	<style>
		.dateStyle{
			float:left;
		}
		.row {
		  margin:0px !important
		}
		.page-header{
			padding:0px !important
		}
		.datainp{
			width: 300px;
			height: 25px;
		}
	</style>
	
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
	  <div class="modal-dialog" role="document" style="margin:30px -200px">
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
					<i class="icon-home home-icon"></i>

					<a href="${base}/conditionMonitoring">${nowSeries}系列</a>
				</li>
	
				<li>

					<a href="#">${nowStar}星</a>

				</li>
				<li class="active">飞轮</li>
			</ul><!-- .breadcrumb -->	
		</div>	
		<div class="page-content">
			<div class="page-header">
				<div class="dateStyle">
					<label>开始日期</label>
					<div id="dateStart-div">
						<input class="datainp" id="dateStart" type="text" placeholder="请选择" readonly>
					</div>
				</div>
				<div class="dateStyle" style="margin-left:20px">
					<label>结束日期</label>
					<div id='dateEnd-div'>
						<input class="datainp" id="dateEnd" type="text" placeholder="请选择" readonly>
					</div>
					<div style="margin-left:320px;margin-top:-25px" id='jqxButton-getParameters'>获取参数</div>
				</div>
				<div style="clear:both"></div>
			</div>
			<!-- /.page-header -->

		<div>
			<div class="col-xs-12 col-sm-12">
				<div class="widget-box">
					<div class="widget-header" id="change-search-box" data-action="collapse">
						<h4>常用模板</h4>
						<div class="widget-toolbar">
							<a href="javascript:void(0);" >
								<i class="icon-chevron-up"></i>
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
		  	
		 <div class="hr hr32 hr-dotted"></div>
			<div class="row">
			<div class="col-xs-12">
					<div style="margin-left:1px;margin-top:0px" id='jqxButton_addgroup'>添加分组</div>
  					<div style="margin-left:110px;margin-top:-25px" id='jqxButton_submitgroup'>提交分组</div>
  					<!--<button onclick="getCleared()">清空已选参数</button>-->
  					<div style="margin-left:560px;margin-top:-26px" id="id_dplist_template"></div>
  		 	</div>
  		 	<div class="col-xs-12">	
  				<div id='jqxWidgett'>
			        <div id="treeGrid"></div>			       	
			     </div>
			</div>	      		     
			</div><!-- /.row -->
			
			<div class="hr hr32 hr-dotted"></div>
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
	jeDate({
		dateCell:"#dateStart",//直接显示日期层的容器，可以是ID  CLASS
		format:"YYYY-MM-DD hh:mm:ss",//日期格式
		isinitVal:false, //是否初始化时间
		festival:false, //是否显示节日
		isTime:true, //是否开启时间选择
		//minDate:"2014-09-19 00:00:00",//最小日期
		maxDate:jeDate.now(0), //设定最大日期为当前日期
	});
	jeDate({
		dateCell:"#dateEnd",//直接显示日期层的容器，可以是ID  CLASS
		format:"YYYY-MM-DD hh:mm:ss",//日期格式
		isinitVal:false, //是否初始化时间
		festival:false, //是否显示节日
		isTime:true, //是否开启时间选择
		//minDate:"2014-09-19 00:00:00",//最小日期
		maxDate:jeDate.now(0), //设定最大日期为当前日期
	});

	$(function() {	
//     	$("#dateStart").jqxDateTimeInput({width: '300px', height: '25px'});
//     	$("#dateEnd").jqxDateTimeInput({width: '300px', height: '25px'});
        
		 $("#jqxButton-getParameters").jqxButton({ width: '100', height: '17'});	
		 $("#jqxButton-getParameters").click( function ()  {    
		 	var beginDate = $("#dateStart").val();
		 	var endDate = $("#dateEnd").val();
            var url = "${base}/getConstraint?beginDate="+beginDate+"&endDate="+endDate;
            updateParamTree(url);
		 });
		 
		 $("#jqxButton_addgroup").jqxButton({ width: '100', height: '17'});	
		 $("#jqxButton_addgroup").click(function(){
		 	getSelected();
		 });
		 
		 $("#jqxButton_submitgroup").jqxButton({ width: '100', height: '17'});	
		 $("#jqxButton_submitgroup").click(function(){
		 	submitGroup();
		 });
		 
		 
		 $('#change-search-box').click();
		 initTemplateTree();
		 intTemplateList();
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
	             { text: '参数名称',  dataField: 'name',editable: false, width: 200 },
	             { text: 'ID',  dataField: 'id',editable: false, width:200, hidden: true },
	             { text: '最大值', dataField: 'max', width: 200 },
	             { text: '最小值', dataField: 'min', width: 160 },
	             { text: 'Y轴', dataField:'yname',width:200,columnType:'template',
					createEditor: function (row, cellValue, editor, cellText, width, height) {
					  var source = ["Y1", "Y2"];
					  editor.jqxDropDownList({autoDropDownHeight: true, placeHolder:'请设置Y轴', source: source, width: '100%', height: '100%' });		 
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
            var btn_savemodel="<button type='button' class='close' onclick='saveToLineTemplate(this)'><span aria-hidden='true'>保存为模板   </span></button>";
            var group= $("<div name="+j+" class='alert alert-block alert-success' role='alert'> <button type='button' class='close' onclick='clearGroup(this)'><span aria-hidden='true'>删除分组;</span></button>"+btn_savemodel+stringName+"</div>")
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
           //在已分组列表上添加“删除”和“保存为模板”按钮
            var btn_savemodel="<button type='button' class='close' onclick='saveToLineTemplate(this)'><span aria-hidden='true'>保存为模板   </span></button>";
			//var btn_savetotemplate_id = "btn_savetotemplate_id_"+j;
	        //var btn_savemodel="<button id='"+btn_savetotemplate_id+"'type='button' class='close' data-toggle='modal' data-target='#id_Modal_template'><span aria-hidden='true'>保存为模板" +j+"  </span></button>";
		    var group= $("<div name="+j+" class='alert alert-warning alert-dismissible' role='alert'> <button type='button' class='close' onclick='clearGroup(this)'><span aria-hidden='true'>删除分组;</span></button>"+btn_savemodel+stringName+"</div>")            
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
        	$.post('${base}/saveTotemplate',        
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
        $.post('${base}/showPanel',        
        	{
        		'JsonG':JSON.stringify(AllRowselect)        	
        	},function(){
        		window.location.href="${base}/showPanel"
        	})            
        } 
                
        //初始化选择模板下拉框
       	function intTemplateList(){
	            var url_templatelist = "getTemplateList";
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
	                height: 25
	            });
	
	            // subscribe to the select event.
	            $("#id_dplist_template").on('select', function (event) {
	                if (event.args) {            
	                    var item = event.args.item;
	                    if (item) {
	                    var url = "${base}/getParamsByTemplateId?templateId="+item.value;
	                    updateParamTree(url);                                           
	                    }
	                }
	            });
            }
            
            //常用曲线模板树 
            function initTemplateTree()
            { 
	        	var url_template = "${base}/getAllTemplate";
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
	                width: 960,
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
	                                $.post('${base}/deleteTemplates',        
								        	{
								        		'templateIds' : keys.join(',') ,						        		    	
								        	});
	                                $("#id_templateTreeGrid").jqxTreeGrid('deleteRow', selecteditems);
	                                	                                	                                
	                            }
	                            else {
	                            var b=$("#id_templateTreeGrid").jqxTreeGrid('getRow', rowKey);	                               
	                                var value = $("#id_templateTreeGrid").jqxTreeGrid('getCellValue', rowKey, 'templateid');	    
									$.post('${base}/deleteTemplates',        
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
	                  { text: '模板名称',  dataField: 'templateName', editable: false, width: 200 },
	                  { text: '模板包含参数',  dataField: 'name', width: 200 },
	                  //{ text: '模板ID',   dataField: 'templateid',editable: false, width:200, hidden: false },
	                  { text: '最大值',   dataField: 'max', width: 200 },
	                  { text: '最小值',    dataField: 'min', width: 160 },
	                  { text: 'Y轴',     dataField: 'yname', width:200, columnType:'custom',
	                  	//cellsRenderer: function (row, column, value, rowData) {if(value=="添加到分组") {return "<input type='button' value='删除模板'></input>"}},
	                  	cellsRenderer: function (row, column, value, rowData) {if(value=="添加到分组") {return ""}}, 
	                  	createEditor: function (row, cellValue, editor, cellText, width, height) {
						  var source = ["y1", "y2"];
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

</@override>	
<@extends name="/secondStyle/contentBase.ftl"/>
