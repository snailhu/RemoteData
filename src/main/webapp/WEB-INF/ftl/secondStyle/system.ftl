<@override name="content_left">
	<div class="sidebar" id="sidebar">
	    <ul class="nav nav-list">
	    	<li>
				<a href="javascript:void(0);" onclick="$('#SatelliteComponents').html('飞轮');">
					<i class="glyphicon glyphicon-certificate"></i>
					<span class="menu-text"> 系统日志 </span>
				</a>
			</li>

			<li>
				<a href="${base}/admin/user/index" onclick="$('#SatelliteComponents').html('陀螺');">
					<i class="glyphicon glyphicon-certificate"></i>
					<span class="menu-text"> 权限管理 </span>
				</a>
			</li>
	    </ul>

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
 	<script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.pager.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.sort.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.filter.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.columnsresize.js"></script>
    <script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.selection.js"></script> 

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

	<div class="main-content">
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
</script>
 <script type="text/javascript">
        $(document).ready(function () {
            var url = "admin/showSystemLog";
            // prepare the data
            var source =
            {
                datatype: "json",
                datafields: [
                    { name: 'userName',  type: 'string' },
                    { name: 'loginTime',  type: 'String' },
                    { name: 'logOutTime', type: 'string' },
                    { name: 'loginIp', type: 'string' },
                    { name: 'operations', type: 'string' },
                    { name: 'role', type: 'string' }
                ],     
                id: 'id',
                url: url,
                pager: function (pagenum, pagesize, oldpagenum) {
                    // callback called when a page or page size is changed.
                }
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            $("#jqxgrid").jqxGrid(
            {
                width: 850,
                source: dataAdapter,
                selectionmode: 'multiplerowsextended',
                sortable: true,
                pageable: true,
                autoheight: true,
                columnsresize: true,
                pagermode: 'simple',
                columns: [
                  { text: '用户名', datafield: 'userName', width: 250 },
                  { text: '登录时间', datafield: 'loginTime', width: 230, cellsformat: 'D' },
                  { text: '登出时间', datafield: 'logOutTime', width: 130, cellsformat: 'F2', cellsalign: 'right' },
                  { text: '登录ip', datafield: 'loginIp', width: 350 },
                  { text: '操作', datafield: 'operations', width: 100 },
                  { text: '角色', datafield: 'role', width: 100 }
                ]
            });
       
        });
    </script>
</@override>
<@extends name="/secondStyle/contentBase.ftl"/>
