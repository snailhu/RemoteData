<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

  </head>
  
  <body>
        <div class="right">
        <div class="right_head">
            <img src="${pageContext.request.contextPath}/static/new/img/DataImport/home.png">
            <span>位置:</span>
            <span>用户管理></span>
        </div>

        <div class="right_content">
            <div class="title">
                搜索
                <img src="${pageContext.request.contextPath}/static/new/img/DataImport_manage/xia.png">
            </div>
            <div class="input_content">
                <div>
                    <span>开始时间</span><input/>
                </div>
                <div>
                    <span>文件类型</span><input/>
                </div>
                <div class="last">
                    文件类型
                    <label class="first_label">
                        <input type="radio" name="type" value="*.dat" checked/>
                        *.dat
                    </label>
                    <label>
                        <input type="radio" name="type" value="*.csv"/>
                        *.csv
                    </label>
                </div>

                <button class="button_1">搜索</button>
                <button class="button_2">取消</button>
            </div>
        </div>

        <div class="content_table">
            <div class="table_title">
                文件列表
            </div>
            <div class="table_content">
                <div class="button">
                    <button><span>下载</span></button>
                    <button class="choose"><span>删除</span></button>
                    <button><span>取消选中</span></button>
                </div>
                <table border="1">
                    <tr class="color">
                        <th class="hao"></th>
                        <th class="hao"><input type="checkbox"/></th>
                        <th class="flite">文件名</th>
                        <th class="sum">大小</th>
                        <th class="time">上传时间</th>
                    </tr>
                    <tr class="xuan">
                        <td class="hao color">1</td>
                        <td class="hao"><input type="checkbox" name="checkbox" checked/></td>
                        <td class="flite">j9-02--2010-01-01.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:53:52</td>
                    </tr>
                    <tr>
                        <td class="hao color">2</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-02.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:51:53</td>
                    </tr>

                    <tr>
                        <td class="hao color">3</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-03.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:15:54</td>
                    </tr>
                    <tr>
                        <td class="hao color">4</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-04.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:45:54</td>
                    </tr>
                    <tr>
                        <td class="hao color">5</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-05.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:35:54</td>
                    </tr>
                    <tr>
                        <td class="hao color">6</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-06.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:43:54</td>
                    </tr>
                    <tr>
                        <td class="hao color">7</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-07.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:51:54</td>
                    </tr>
                    <tr>
                        <td class="hao color">8</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-08.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:05:54</td>
                    </tr>
                    <tr>
                        <td class="hao color">9</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-09.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:33:54</td>
                    </tr>
                    <tr>
                        <td class="hao color">10</td>
                        <td class="hao"><input type="checkbox" name="checkbox"/></td>
                        <td class="flite">j9-02--2010-01-10.csv</td>
                        <td class="sum">35.0 M</td>
                        <td class="time">2016-09-26 10:22:54</td>
                    </tr>
                </table>
                <div class="page">
                    <div>
                        <input class="float_left" type="number" value="1">
                        <span class="float_left">/</span>
                        <span class="float_left">20</span>
                        <button class="float_left">上一页</button>
                        <div class="xuan float_left">1</div>
                        <div class="float_left">2</div>
                        <div class="float_left">3</div>
                        <span class="float_left">...</span>
                        <button>下一页</button>
                        <button>最后一页</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>
