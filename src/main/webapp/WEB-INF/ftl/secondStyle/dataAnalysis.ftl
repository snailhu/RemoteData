<@override name="content_right">	
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />	
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
    <script type="text/javascript" src="${base}/static//jqwidgets/jqxbuttons.js"></script>
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
					<a href="#">Home</a>
				</li>
	
				<li>
					<a href="#">Tables</a>
				</li>
				<li class="active">jqGrid plugin</li>
			</ul><!-- .breadcrumb -->
	
			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="icon-search nav-search-icon"></i>
					</span>
				</form>
			</div><!-- #nav-search -->
		</div>	
		<div class="page-content">
			<div class="page-header">
				<div class="dateStyle"><label>开始日期</label><div id='dateStart'></div></div>
				<div class="dateStyle" style="margin-left:20px"><label>结束日期</label><div id='dateEnd'></div> 
                	<div style="margin-left:320px;margin-top:-25px" id='jqxButton-getParameters'>获取参数</div>
            	</div> 
				<div style="clear:both"></div>
			</div><!-- /.page-header -->
		  
			<div class="row"> 
				<div id='jqxWidget'>
			        <div id="treeGrid"></div>
			       	<button onclick="getSelected()">确定分组</button>
      				<button onclick="getCleared()">清除</button>
      				<button data-toggle="modal"	 onclick="submitGroup()">上传分组</button>
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
		 	//alert(beginDate + "--" + endDate);
            // prepare the data
            var url = "${base}/getConstraint?beginDate="+beginDate+"&endDate="+endDate;
            var source =
            {
                dataType: "json",
                dataFields: [
                    { name: 'id', type: 'number' },
                    { name: 'parentId', type: 'number' },
                    { name: 'name', type: 'string' },
                    { name: 'max', type: 'number' },
                    { name: 'min', type: 'number' }
  
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
            // create Tree Grid
            $("#treeGrid").jqxTreeGrid(
            {
                width: 920,
                source: dataAdapter,
                sortable: true,
                editable: true,
                checkboxes: true,
                columns: [
                  { text: 'ID',  dataField: 'id',editable: false, width: 200 },
                  { text: '参数名',  dataField: 'name',editable: false, width: 250 },
                  { text: '最大值', dataField: 'max', width: 160 },
                  { text: '最小值', dataField: 'min', width: 160 },
                  { text: 'y2轴名称', dataField: 'y2Name',  width:160,  }
                ]

            });
        });
    });
	
	
		var JsonG = {}
		var AllRowselect = [];
		var j=0;
        function getSelected(){      	
        	//$('#exampleModal').modal('hide')
            var groupObject={}
            var selectRow = []
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
                    rowObject.y2Name=rowindex[i].y2Name;
                    selectRow.push( rowObject);
                    stringName+=value+",";
                }
	            groupObject.id=j
	            groupObject.secectRow = selectRow;
	            groupObject.Ycount = chkObjs;
	            groupObject.Y1name=$("#firsty-name").val();
	            if(chkObjs=="2"){
	            	groupObject.Y2name= $("#secondy-name").val();
	            }
	            var group= $("<div name="+j+" class='alert alert-warning alert-dismissible' role='alert'> <button type='button' class='close' onclick='clearGroup(this)'><span aria-hidden='true'>&times;</span></button>"+stringName+"</div>")
	            $('#jqxWidget').append(group)
	            AllRowselect[j]=groupObject;
	            j++;           
	            JsonG.alldata=AllRowselect;
            }else{
            	alert("至少请选择一行参数！");
            }
        }    
         function clearGroup(obj){
        	var clearGroupId = $(obj).parent('.alert').attr("name")
        	$(obj).parent('.alert').remove();
        	AllRowselect.splice(parseInt(clearGroupId),1)          
        }                
         function getCleared(){
        
           $("#treeGrid").jqxTreeGrid('refresh');
        }
        
        function submitGroup(){
        $.post('${base}/showPanel',
        
        	{
        		'JsonG':JSON.stringify(AllRowselect)
        	
        	},function(){
        	
        		window.location.href="${base}/showPanel"
        	})            
        }	
        
        
        

    </script>	
</@override>	
<@extends name="/secondStyle/contentBase.ftl"/>
