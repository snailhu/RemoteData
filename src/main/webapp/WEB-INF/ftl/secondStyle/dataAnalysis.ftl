<@override name="content_right">
	
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />	
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxdropdownlist.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.pager.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.selection.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.edit.js"></script>


    <script type="text/javascript" src="${base}/static/scripts/demos.js"></script>  

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
				    <form id="fileupload" action="" class="form-horizontal" role="form" >
				        <div class="space-1"></div>
				        <div class="form-group" style="margin:0px auto !important">
				            <label class="col-sm-3 control-label no-padding-right" for="form-beginTime"> 开始时间 </label>
				            <div class="col-sm-9">
				                <input type="text" id="form-beginTime" name="beginTime" placeholder="开始时间" class="col-xs-10 col-sm-5" />
				                <div id="getBeginTime"></div>
				            </div>
				        </div>
				        <div class="space-1"></div>
				        <div class="form-group" style="margin:0px auto !important">
				            <label class="col-sm-3 control-label no-padding-right" for="form-endTime"> 结束时间 </label>
				            <div class="col-sm-9">
				                <input type="text" id="form-endTime" name="endTime" placeholder="结束时间" class="col-xs-10 col-sm-5" />
				                <div id="getEndTime"></div>
				            </div>
				        </div>				
				       </div>
				    </form>   
			</div><!-- /.page-header -->

		  
			<div class="row">
				<div id='jqxWidget' style="font-size: 13px; font-family: Verdana; float: left;margin-left: 30px;margin-top: -70px;">
			        <div id="jqxgrid">
			        </div>
			       	<button data-toggle="modal" data-target="#exampleModal">确定分组</button>
      				<button onclick="getCleared()">清除</button>
      				<button data-toggle="modal"	 onclick="submitGroup()">提交分组</button>
			     </div>			      		     
			</div><!-- /.row -->
			
	
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	<script type="text/javascript">	
		  $(function(){
	/**  
	  $('#getBeginTime').calendar({
	        trigger: '#form-beginTime',
	        zIndex: 999,
			format: 'yyyy-mm-dd',
	        onSelected: function (view, date, data) {
	        },
	        onClose: function (view, date, data) {
	        }
	    });
	  $('#getEndTime').calendar({
	        trigger: '#form-endTime',
	        zIndex: 999,
			format: 'yyyy-mm-dd',
	        onSelected: function (view, date, data) {

	        },
	        onClose: function (view, date, data) {

	        }
	    });
	*/
  })
		var JsonG = {}
		var AllRowselect = [];
		var j=0;
        function getSelected(){      	
        	$('#exampleModal').modal('hide')
            var groupObject={}
            var selectRow = []
            var rowindex = $('#jqxgrid').jqxGrid('getselectedrowindexes');
            var stringName="参数名：";
            var chkObjs = $('input:radio:checked').val();
            if(rowindex.length>0){        	
                for(i=0;i<rowindex.length;i++){
                	var rowObject={}
                    var value = $('#jqxgrid').jqxGrid('getcellvalue', rowindex[i], "name");
                    rowObject.id=rowindex[i]
                    rowObject.name=$('#jqxgrid').jqxGrid('getcellvalue', rowindex[i], "name");
                    rowObject.max=$('#jqxgrid').jqxGrid('getcellvalue', rowindex[i], "max");
                    rowObject.min=$('#jqxgrid').jqxGrid('getcellvalue', rowindex[i], "min");
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
            var group= $("<div name="+j+" class='alert alert-warning alert-dismissible' role='alert'> <button type='button' class='close' onclick='clearGroup(this)'><span aria-hidden='true'>&times;</span></button>"+stringName+"</div>")
            $('#jqxWidget').append(group)
            AllRowselect[j]=groupObject;
            j++;           
            JsonG.alldata=AllRowselect;
            
        }       
         function clearGroup(obj){
        	var clearGroupId = $(obj).parent('.alert').attr("name")
        	$(obj).parent('.alert').remove();
        	AllRowselect.splice(parseInt(clearGroupId),1)          
        }                
         function getCleared(){
             $('#jqxgrid').jqxGrid('clearselection');
        }
        
        function submitGroup(){
        $.post('${base}/showPanel',
        
        	{
        		'JsonG':JSON.stringify(JsonG)
        	
        	},function(){
        	
        		window.location.href="${base}/showPanel"
        	})            
        }	
       $(document).ready(function () {
            var url = "${base}/getConstraint";
            // prepare the data
           
            var source =
            {
               datatype: "json",
               datafields: [
                    { name: 'name' },
                    { name: 'max' },
                    { name: 'min' },

                ],
                id: 'id',            
               url: url
            };
            var dataAdapter = new $.jqx.dataAdapter(source, {

            });
            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {              
                source: dataAdapter,
                pageable: true,
                autoheight: true,
                altrows: true,
                enabletooltips: true,
                editable: true,
                selectionmode: 'checkbox',
                 columns: [
                  { text: 'Name',pinned: true,editable: false, datafield: 'name',  },
                  { text: 'max', datafield: 'max',  },
                  { text: 'min', datafield: 'min', }
              ]

            });	
        });
    </script>	
</@override>	
<@extends name="/secondStyle/contentBase.ftl"/>
