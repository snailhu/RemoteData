<@override name="content_right">	
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
	
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
	        <button type="button" class="btn btn-primary" onclick="getSelected()">确定</button>
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
					<a href="#">J9系</a>

				</li>
	
				<li>
					<a href="#">star2</a>


				</li>
				<li class="active">飞轮</li>
			</ul><!-- .breadcrumb -->
	
<!-- 			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon">
						<input type="text" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div> -->
			<!-- #nav-search -->
		</div>	
		<div class="page-content">
			<div class="page-header">
				<div class="dateStyle"><label>开始日期</label><div id='dateStart'></div></div>
				<div class="dateStyle" style="margin-left:20px"><label>结束日期</label><div id='dateEnd'></div> 
                	<div style="margin-left:320px;margin-top:-25px" id='jqxButton-getParameters'>获取参数</div>
            	</div> 
				<div style="clear:both"></div>
			</div><!-- /.page-header -->
		  			  	
		  	<div >
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
						        <button  onclick="getTemplate()">模板添加到分组</button>
							</div>
						</div>
					</div>
				</div><!-- /.col -->
		  		  	
			<div class="row">	
		     	<button onclick="getSelected()">添加分组</button>
  				<button onclick="getCleared()">清空已选参数</button>
  				<button data-toggle="modal"	 onclick="submitGroup()">提交分组</button>	
  				<div id="id_dplist_template"><div style="font-size: 12px; font-family: Verdana;" id="selectionlog"></div></div>
			</div>
			
			<div class="row"> 
  				<div id='jqxWidget'>
			        <div id="treeGrid"></div>
	            	已有分组：			       	
			     </div>	      		     
			</div><!-- /.row -->
										
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	
<script type="text/javascript">	
	$(function() {	
    	$("#dateStart").jqxDateTimeInput({width: '300px', height: '25px'});
    	$("#dateEnd").jqxDateTimeInput({width: '300px', height: '25px'});
        
		//
		$("#jqxButton-getParameters").jqxButton({ width: '100', height: '17'});
		
		 $("#jqxButton-getParameters").click( function ()  {    
		 	var beginDate = $("#dateStart").val();
		 	var endDate = $("#dateEnd").val();
            var url = "${base}/getConstraint?beginDate="+beginDate+"&endDate="+endDate;
            updateParamTree(url);
		 });
		 $('#change-search-box').click();
		 
		 initTemplateTree()
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
           hierarchicalCheckboxes: false,              	
           columns: [
	             { text: '参数名称',  dataField: 'name',editable: false, width: 200 },
	             { text: 'ID',  dataField: 'id',editable: false, width:200, hidden: true },
	             { text: '最大值', dataField: 'max', width: 200 },
	             { text: '最小值', dataField: 'min', width: 160 },
	             { text: 'Y轴', dataFaield:'yname',width:200,columnType:'template',
					createEditor: function (row, cellValue, editor, cellText, width, height) {
					  var source = ["Y1", "Y2"];
					  editor.jqxDropDownList({autoDropDownHeight: true, source: source, width: '100%', height: '100%' });		 
					},
					initEditor: function (row, cellValue, editor, cellText, width, height) {
						editor.jqxDropDownList('selectItem', cellValue);
					},
					getEditorValue: function (row, cellValue, editor) {
						//$("#treeGrid").jqxTreeGrid('setCellValue', row, 'yname', editor.val());
// 						alert("1"+cellValue+"--"+editor.val());
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

            // var group= $("<div style='margin-top:30px' name="+j+" class='alert alert-warning alert-dismissible' role='alert'> <button type='button' class='close' onclick='clearGroup(this)'><span aria-hidden='true'>&times;</span></button>"+stringName+"</div>")
            //在已分组列表上添加“删除”和“保存为模板”按钮
            var btn_savemodel="<button type='button' class='close' onclick=''><span aria-hidden='true'>生成模板   </span></button>";
            var group= $("<div name="+j+" class='alert alert-warning alert-dismissible' role='alert'> <button type='button' class='close' onclick='clearGroup(this)'><span aria-hidden='true'>删除分组;</span></button>"+btn_savemodel+stringName+"</div>")

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
          if(rowindex.length>0){        	
              for(i=0;i<rowindex.length;i++){
              	var rowObject={}
                  var value = rowindex[i].name;
                  rowObject.id=rowindex[i].id
                  rowObject.name=rowindex[i].name;
                  rowObject.max=rowindex[i].max;
                  rowObject.min=rowindex[i].min;
                  var yname = rowindex[i].null;
                  if(yname=='Y1'){
	                  rowObject.yname=0;
                  }else{
                	  rowObject.yname=1;
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
           //在已分组列表上添加“删除”和“保存为模板”按钮
           var btn_savemodel="<button type='button' class='close' onclick=''><span aria-hidden='true'>生成模板   </span></button>";
           var group= $("<div name="+j+" class='alert alert-warning alert-dismissible' role='alert'> <button type='button' class='close' onclick='clearGroup(this)'><span aria-hidden='true'>删除分组;</span></button>"+btn_savemodel+stringName+"</div>")            
           $('#jqxWidget').append(group)            
           AllRowselect[j]=groupObject;
           j++;           
           JsonG.alldata=AllRowselect;  
       	}else{
       		alert("至少请选择一行参数！");
       	}  
      }
        
        //删除已经生成的分组
     	function clearGroup(obj){
			var clearGroupId = $(obj).parent('.alert').attr("name")
			$(obj).parent('.alert').remove();
			AllRowselect.splice(parseInt(clearGroupId),1)          
        } 
        //清空已选参数按钮响应事件            
         function getCleared(){
     
            //$("#treeGrid").jqxTreeGrid('setCellValue', 1, 'yname', '132');
            var value = $("#treeGrid").jqxTreeGrid('getCellValue', 1, 'yname');
            //var value = $("#id_dplist_template").jqxTreeGrid('getCellValue', 1, 'yname');
           
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
	                editable: true,
	               	checkboxes: true,
	               	hierarchicalCheckboxes: false,              	
	                columns: [
	                  { text: '模板名称',  dataField: 'templateName', editable: false, width: 200 },
	                  { text: '模板包含参数',  dataField: 'name', width: 200 },
	                  //{ text: 'ID',   dataField: 'id',editable: false, width:200, hidden: true },
	                  { text: '最大值',   dataField: 'max', width: 200 },
	                  { text: '最小值',    dataField: 'min', width: 160 },
	                  { text: 'Y轴',     dataField: 'yname', width:200, columnType:'custom',
	                  	cellsRenderer: function (row, column, value, rowData) {if(value=="添加到分组") {return "<input type='button' value='删除模板'></input>"}}, 
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
