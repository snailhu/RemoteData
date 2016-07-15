<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.bar {
	height: 18px;
	background: green;
}
</style>
<script
	src="${pageContext.request.contextPath}/static/content/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script
	src="${pageContext.request.contextPath}/static/content/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script
	src="${pageContext.request.contextPath}/static/content/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script type="text/javascript">
	function fileUpload(filePath) {
		if (filePath != "") {
			sendFileToServer(filePath);
		} else {
			alert("请选择要上传的文件！");
		}
	}

	function callback() {
		if (xmlhttp.readyState == 4) {
			var responseText = xmlhttp.responseText;
		}
	}

	function sendByteStreamToServer(stream, url) {
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
			if (xmlhttp.overrideMimeType) {
				xmlhttp.overrideMimeType("text/xml")
			}
		} else if (window.ActiveXObject) {
			var activexName = [ "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];
			for (var i = 0; i < activexName.length; i++) {
				try {
					xmlhttp = new ActiveXObject(activexName[i]);
					break;
				} catch (e) {
					e.print()
				}
			}
		}
		xmlhttp.onreadystatechange = callback;
		xmlhttp.open("post", url, false);
		boundary = "abcd"
		xmlhttp.setRequestHeader("Content-Type",
				"multipart/form-data,boundary=" + boundary);
		xmlhttp.setRequestHeader("Content-Length", stream.Size);
		//alert(stream.size);  
		xmlhttp.send(stream);
	}
	function sendFileToServer(filePath) {
		var filepath = encodeURI(encodeURI(filePath));
		var stream = new ActiveXObject("ADODB.Stream");
		stream.Type = 1;
		stream.Open();
		stream.Position = 0;//指定或返加对像内数据的当前指针。 
		stream.LoadFromFile(filePath) //将FileName指定的文件装入对像中,参数FileName为指定的用户名。     
		stream.Position = 0;
		var id = document.getElementById("appid").value;
		sendByteStreamToServer(stream,
				"appmanager.do?action=importdate&tag=3&cmd=yhjzqywGetDatazz&filePath="
						+ filepath + "&id=" + id);
		stream.Close();
	}
	var countfiles = 0;
	var countfolders = 0;
	//用于打开浏览对话框，选择路径 

	function BrowseFolder() {
		try {
			var Message = "请选择文件夹"; //选择框提示信息 
			var Shell = new ActiveXObject("Shell.Application");
			var Folder = Shell.BrowseForFolder(0, Message, 0x0040, 0x11);//起始目录为：我的电脑 
			//var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面 
			if (Folder != null) {
				Folder = Folder.items(); // 返回 FolderItems 对象   
				Folder = Folder.item(); // 返回 Folderitem 对象   
				Folder = Folder.Path; // 返回路径 
				if (Folder.charAt(Folder.length - 1) != "\\") {
					Folder = Folder + "\\";
				}
				//document.all.getfolder.value=Folder;   
				return Folder;
			} else {
				Folder = "";
				return Folder;
			}
		} catch (e) {
			alert(e.message + "11");
		}
	}
	//用于遍历 
	function traverse(localPath, textHtml) {
		var fso = new ActiveXObject("Scripting.FileSystemObject");
		var currentFolder = fso.GetFolder(localPath);
		var fileList = new Enumerator(currentFolder.files);
		var subFolderList = "";
		var fileHtml = textHtml;
		var aFile;

		for (; !fileList.atEnd(); fileList.moveNext()) {

			countfiles++;
			aFile = fileList.item();
			fileHtml.push(aFile.Path);
		}

		subFolderList = new Enumerator(currentFolder.SubFolders);
		for (; !subFolderList.atEnd(); subFolderList.moveNext()) {
			countfolders++;
			//fileHtml +="chfile："+ subFolderList.item().Path+"<hr>"; 
			fileHtml = traverse(subFolderList.item().Path, fileHtml)//递归遍历子文件夹   
		}
		//fileHtml+="共遍历文件数："+countfiles+"<br/>"+"共遍历文件夹数："+countfolders;   
		return (fileHtml);
	}
	function browse() {
		document.all.path.value = BrowseFolder();
	}
	function viewfiles() { //var textHtml=""; 
		var textHtml = new Array();
		var textHtmls = new Array();
		var folderpath = document.all.path.value;
		//document.getElementById("path").value=traverse(folderpath,textHtml); 
		//span.innerHTML=traverse(folderpath,textHtml)+"共遍历文件数："+countfiles+"<br/>"+"共遍历文件夹数："+countfolders; 
		textHtmls = traverse(folderpath, textHtml);
		for (var i = 0; i < (textHtmls.length); i++) {
			fileupload(textHtmls[i])
			// alert(textHtmls[i]);       
			//var text='<input name="text" type="text" value="'+textHtmls[i]+'" size="45" id="t'+i+'"> </br>';   } 
			return true;
			//span.innerHTML=traverse(folderpath,textHtml)+"共遍历文件数："+countfiles+"<br/>"+"共遍历文件夹数："+countfolders; 
		}
		var XMLHttpReq;
		var currentSort;
		//创建XMLHttpRequest对象           function createXMLHttpRequest() { 
		if (window.XMLHttpRequest) { //Mozilla 浏览器            
			XMLHttpReq = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // IE浏览器 
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
	}
	//发送请求函数 
	function sendRequest(url) {
		createXMLHttpRequest();
		XMLHttpReq.open("POST", url, true);
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数 
		XMLHttpReq.send(null); // 发送请求     
	}
	// 处理返回信息函数 
	function processResponse() {
		if (XMLHttpReq.readyState == 4) { // 判断对象状态 
			if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息 
				getdate();
			} else { //页面不正常 
				alert("您所请求的页面有异常。");
			}
		}
	}
	function getdate() {
		var res = XMLHttpReq.responseXML.getElementsByTagName("res");
		for (var i = 0; i < res.length; i++) {
			var msg = res[i].firstChild.data;
			//alert("---"+msg); 
			if (msg > 0) {
				var path = document.getElementById("path").value;
				//alert(path); 
				if (path.length == 0) {
					alert("请选择文件或文件夹！");
					return;
				} else {
					//document.forms[0].submit();  
					if (viewfiles()) {
						alert("导入数据成功");
						window.location.href = "appmanager.do?action=viewapp&id=${id}";
					}
				}
			} else {
				alert("修改失败");
				return;
			}
		}
	}
	function submits(tag) {
		var value = "";
		var appid = document.getElementById("appid").value;
		var dyndata = document.getElementsByName("Dyndata");
		for (var i = 0; i < dyndata.length; i++) {
			if (dyndata[i].checked == true) {
				value = dyndata[i].value;
				break;
			}
		}
		var state = document.getElementById("state").value;
		if (state == 1 || state == 3 || state == 4 || state == 5) {
			alert("对不起！此数据已经提交不能修改或再次提交！");
			return false;
		} else {
			var issueTime = document.getElementById("issueTime").value;
			var whenlong = document.getElementById("whenlong").value;
			sendRequest("appmanager.do?action=viewapp&type=ajax&appid=" + appid
					+ "&dyndata=" + value + "&issueTime=" + issueTime
					+ "&whenlong=" + whenlong + "&state=" + tag);
			//document.getElementById("action").value="editor";          
			// document.forms[0].submit();         
		}
	}
	function openFileIIs() {
		// 		var filename = "c:"
		try {
			var obj = new ActiveXObject("wscript.shell");
			if (obj) {
				obj.Run(null, 1, false);
				//obj.run("osk");/*打开屏幕键盘*/  
				//obj.Run('"'+filename+'"');   
				obj = null;
			}
		} catch (e) {
			alert("请确定是否存在该盘符或文件");
		}

	}
</script>
</head>

<body>
	<div class="main-content">
		<!-- 		<div class="breadcrumbs" id="breadcrumbs"> -->
		<!-- 			<script type="text/javascript"> -->
		<!-- 				try { -->
		<!-- 					ace.settings.check('breadcrumbs', 'fixed') -->
		<!-- 				} catch (e) { -->
		<!-- 				} -->
		<!-- 			</script> -->
		<!-- 			<ul class="breadcrumb"> -->
		<!-- 				<li><i class="icon-home home-icon"></i> <a href="javascript:void(0);">首页</a></li> -->
		<!-- 				<li class="active">欢迎页面</li> -->
		<!-- 			</ul>.breadcrumb -->
		<!-- 			<div class="nav-search" id="nav-search"> -->
		<!-- 				<form class="form-search"> -->
		<!-- 					<span class="input-icon"> <input type="text" -->
		<!-- 						placeholder="Search ..." class="nav-search-input" -->
		<!-- 						id="nav-search-input" autocomplete="off" /> <i -->
		<!-- 						class="icon-search nav-search-icon"></i> -->
		<!-- 					</span> -->
		<!-- 				</form> -->
		<!-- 			</div>#nav-search -->
		<!-- 		</div> -->
		<div class="page-content">
			<div class="page-header">
				<h1>欢迎进入mongodbyp</h1>
			</div>
			<!-- /.page-header -->
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<!-- 					<input  type="text"  id="path"  name="path" /> <input -->
					<!-- 						 type="button" name="选择"  value="选择"  onclick="browse()" />  -->
					<!-- 					<form action="admin/file/uploadFile" method="post" -->
					<!-- 						enctype="multipart/form-data"> -->
					<!-- 						<inputtype ="text" id="path"  name="path" /> -->
					<!-- 						<input type="button" name="选择"  value="选择" -->
					<!-- 							 onclick="openFileIIs()" /> -->
					<!-- 					</form> -->
					<input id="fileupload" type="file" name="files[]" multiple />
					<input id="startUpload" type="button" value="startUpload"/>
					<div id="fileNameMsg"></div>
					<div id="progress">
						<div class="bar" style="width: 0%;"></div>
					</div>
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content -->
	</div>
	<!-- /.main-content -->
	
<script type="text/javascript">
$(function () {
    $('#fileupload').fileupload({
    	url: 'admin/file/uploadFile',
//     	sequentialUploads: true,
		maxChunkSize: 10000000, // 10 MB //只上传10M
        dataType: 'json',
        add: function (e, data) { 
        	 $.each(data.files, function (index, file) {
        		 $('#fileNameMsg').append("<p>"+file.name+"</p>");
//                  alert(file.name);
             });
//         	alert("11")
        	 $('#startUpload').click(function () {
                 data.submit();
             });
        },
        drop: function (e, data) {
            $.each(data.files, function (index, file) {
                alert('Dropped file: ' + file.name);
            });
        },
        done: function (e, data) {
        	 alert("22");
        	 data.context.text('Upload finished.');
        },
        change: function (e, data) {
//             $.each(data.files, function (index, file) {
//                 alert('Selected file: ' + file.name);
//             });
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        }
    });
//     $('#fileupload').fileupload('disable');
//     $('#fileupload').fileupload('enable');
});
</script>
</body>
</html>
