<@override name="link">
	<link href="${base}/static/css/cssnew.css" rel="stylesheet"/>	
	<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
</@override>


<@override name="script">

<script type="text/javascript" src="/static/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxsortable.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxkanban.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxdata.js"></script>
<script type="text/javascript" src="${base}/static/scripts/demos.js"></script>	
<script type="text/javascript">
	$(document).ready(function () {
	    var fields = [
	             { name: "id", type: "string" },
	             { name: "status", map: "state", type: "string" },
	             { name: "text", map: "label", type: "string" },
	             { name: "tags", type: "string" },
	             { name: "color", map: "hex", type: "string" },
	             { name: "resourceId", type: "number" }
	    ];
	
	    var source =
	     {
	         localData: [
	                  { id: "1161", state: "new", label: "Combine Orders", tags: "orders, combine", hex: "#5dc3f0", resourceId: 3 },
	                  { id: "1645", state: "work", label: "Change Billing Address", tags: "billing", hex: "#f19b60", resourceId: 1 },
	                  { id: "9213", state: "new", label: "One item added to the cart", tags: "cart", hex: "#5dc3f0", resourceId: 3 },
	                  { id: "6546", state: "done", label: "Edit Item Price", tags: "price, edit", hex: "#5dc3f0", resourceId: 4 },
	                  { id: "9034", state: "new", label: "Login 404 issue", tags: "issue, login", hex: "#6bbd49" }
	         ],
	         dataType: "array",
	         dataFields: fields
	     };
	
	    var dataAdapter = new $.jqx.dataAdapter(source);
	
	    var resourcesAdapterFunc = function () {
	        var resourcesSource =
	        {
	            localData: [
	                  { id: 0, name: "No name", image: "${base}/static/jqwidgets/styles/images/common.png", common: true },
	                  { id: 1, name: "Andrew Fuller", image: "${base}/static/images/andrew.png" },
	                  { id: 2, name: "Janet Leverling", image: "${base}/static/images/janet.png" },
	                  { id: 3, name: "Steven Buchanan", image: "${base}/static/images/steven.png" },
	                  { id: 4, name: "Nancy Davolio", image: "${base}/static/images/nancy.png" },
	                  { id: 5, name: "Michael Buchanan", image: "${base}/static/images/Michael.png" },
	                  { id: 6, name: "Margaret Buchanan", image: "${base}/static/images/margaret.png" },
	                  { id: 7, name: "Robert Buchanan", image: "${base}/static/images/robert.png" },
	                  { id: 8, name: "Laura Buchanan", image: "${base}/static/images/Laura.png" },
	                  { id: 9, name: "Laura Buchanan", image: "${base}/static/images/Anne.png" }
	            ],
	            dataType: "array",
	            dataFields: [
	                 { name: "id", type: "number" },
	                 { name: "name", type: "string" },
	                 { name: "image", type: "string" },
	                 { name: "common", type: "boolean" }
	            ]
	        };
	
	        var resourcesDataAdapter = new $.jqx.dataAdapter(resourcesSource);
	        return resourcesDataAdapter;
	    }
	    
	    var log = new Array();
	    var updateLog = function () {
	        var count = 0;
	        var str = "";
	        for (var i = log.length - 1; i >= 0; i--) {
	            str += log[i] + "<br/>";
	            count++;
	            if (count > 10)
	                break;
	        }
	        $("#log").html(str);
	    }
	
	    $('#kanban').jqxKanban({
	        resources: resourcesAdapterFunc(),
	        source: dataAdapter,
	        columns: [
	            { text: "Backlog", dataField: "new" },
	            { text: "In Progress", dataField: "work" },
	            { text: "Done", dataField: "done" }
	        ]
	    });
	
	    $('#kanban').on('itemMoved', function (event) {
	        var args = event.args;
	        var itemId = args.itemId;
	        var oldParentId = args.oldParentId;
	        var newParentId = args.newParentId;
	        var itemData = args.itemData;
	        var oldColumn = args.oldColumn;
	        var newColumn = args.newColumn;
	
	        log.push("itemMoved is raised");
	        updateLog();
	    });
	
	    $('#kanban').on('columnCollapsed', function (event) {
	        var args = event.args;
	        var column = args.column;
	
	        log.push("columnCollapsed is raised");
	        updateLog();
	    });
	
	    $('#kanban').on('columnExpanded', function (event) {
	        var args = event.args;
	        var column = args.column;
	
	        log.push("columnExpanded is raised");
	        updateLog();
	    });
	
	    $('#kanban').on('itemAttrClicked', function (event) {
	        var args = event.args;
	        var itemId = args.itemId;
	        var attribute = args.attribute; // template, colorStatus, content, keyword, text, avatar
	
	        log.push("itemAttrClicked is raised");
	        updateLog();
	    });
	
	    $('#kanban').on('columnAttrClicked', function (event) {
	        var args = event.args;
	        var column = args.column;
	        var cancelToggle = args.cancelToggle; // false by default. Set to true to cancel toggling dynamically.
	        var attribute = args.attribute; // title, button
	
	        log.push("columnAttrClicked is raised");
	        updateLog();
	    });
	});
	</script>	
</@override>
<@override name="content_right">
		<div class="content_right_header">123</div>
		<div class="content_right_main	">
			<div id="kanban"></div>  		
		</div>
	</@override>
<@extends name="contentBase.ftl"/>