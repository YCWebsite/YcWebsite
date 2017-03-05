<%@ page language="java" contentType="text/html; charset=UTF-8" 
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台首页</title>
<link type="text/css" rel="stylesheet" href="backcss/icon.css"/>
<link type="text/css" rel="stylesheet" href="backcss/easyui.css"/>
<style type="text/css">
	.button{
		padding-left:20px;
		text-decoration:none;
		font-size:14px;
	}
	#img{
		padding-left:500px;
	}
	
</style>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',title:'',split:true" style="height:100px;">
    	<img id="img" src="images/logo1.jpg"/>
    </div>  
    <div data-options="region:'west',title:'菜单管理',split:true" style="width:220px;">
		<div class="easyui-accordion" style="width:200px;height:400px;">
			<div title="师资管理" style="overflow:auto;padding:10px;">
				<ul id="tt1" class="easyui-tree">
					<li class="changefont">
						<a class="button"  href="backTeachers.jsp" >教师管理</a>
						
					</li class="changefont">
					<li>
						<a class="button"  href="backTeam.jsp" >团队管理</a>
						
					</li>
				</ul>
			</div>
			<div title="课程体系管理" style="padding:10px;">
				<ul id="tt1" class="easyui-tree">
					<li class="changefont">
						<a class="button"  href="backCourses.jsp">课程体系管理</a>
						
					</li>
				</ul>
			</div>
			<div title="学员项目管理" style="padding:10px;">
				<ul id="tt1" class="easyui-tree">
					<li class="changefont">
						<a class="button"  href="backProjects.jsp" >学员项目管理</a>
						
					</li>
				</ul>
			</div>
			
			<div title="人才管理" style="padding:10px;">
				<ul id="tt1" class="easyui-tree">
					<li class="changefont">
						<a class="button"  href="backEmployee.jsp" >招聘管理</a>
						
					</li>
				</ul>
			</div>
			<div title="学员就业管理" style="padding:10px;">
				<ul id="tt1" class="easyui-tree">
					<li class="changefont">
						<a class="button"  href="backJodDetail.jsp">学员就业管理</a>
						
					</li>
				</ul>
			</div>
			<div title="公司介绍" style="padding:10px;">
				<ul id="tt1" class="easyui-tree">
					<li class="changefont">
						<a class="button"  href="backActivities.jsp" >公司活动</a>
						
					</li>
					<li class="changefont">
						<a class="button"  href="backNews.jsp" >新闻管理</a>
						
					</li>
					<li class="changefont">
						<a class="button"  href="backHistory.jsp">公司历史</a>
						
					</li>
					<li class="changefont">
						<a class="button"  href="backAboutUs.jsp">关于公司</a>
						
					</li>
				</ul>
			</div>
			
			<div title="技术支持" style="padding:10px;">
				<ul id="tt1" class="easyui-tree">
					<li class="changefont">
						<a class="button"  href="goodType.html" >技术日志发表</a>
					</li>
				</ul>
			</div>
		</div>	
    </div>  
    <div id="main_panel" data-options="region:'center',title:'主界面'" style="padding:5px;">
    	<table id="find_admin"></table>
    </div>  
 
</body>
<!--第三步：引入jquery代码  必须在easyui之前-->
<script type="text/javascript" src="backjs/jquery.min.js"></script>
<!--第四步：引入easyuid  -->
<script type="text/javascript" src="backjs/jquery.easyui.min.js"></script>
<!--第五步：引入语言包  支持中文   locale目录下-->
<script type="text/javascript" src="backjs/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="backjs/ajaxfileupload.js"></script>
<script type="text/javascript"></script>

</html>