<%@ page language="java" contentType="text/html; charset=UTF-8" 
   pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();  //   /SpringMVC7douban (上下文路径)
				//		http             ://         localhost         :        8080          /SpringMVC7douban /
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 加入基底网址，避免路径出错(只适用于jsp页面) -->
<base href="<%=basePath%>">

<title>后台首页</title>
<link type="text/css" rel="stylesheet" href="backcss/icon.css"/>
<link type="text/css" rel="stylesheet" href="backcss/easyui.css"/>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north'" style="height:145px;">
    	<div id="head_panel" class="easyui-panel" data-options="fit:true,border:false"><img id="img" src="images/logo(1).png" style="padding-left:380px;padding-top:10px;"/></div>
    </div>
    <div data-options="region:'west',title:'操作菜单'" style="width:200px;">
		<div class="easyui-accordion" style="width:200px;height:400px;">
			<div title="内容管理" style="overflow:auto;padding:10px;">
				<ul class="link">
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true"  href="toBackTeachers.action" >教师管理</a>
					</li class="changefont">
					<li>
						<a class="easyui-linkbutton" data-options="plain:true" href="toBackTeam.action" >团队管理</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toBackCourses.action">课程体系管理</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toProjects.action">学员项目管理</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toEmploy.action" >招聘管理</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toStudents.action" >报名信息</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toJobDetails.action">学员就业管理</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toActivities.action" >公司活动</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toNews.action" >新闻管理</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toHistory.action?type=公司历史">公司历史</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toAboutCompany.action?type=关于公司">关于公司</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toFootWords.action?type=页脚文字">页脚文字</a>
					</li>
				</ul>
			</div>
			<div title="网站管理" style="padding:10px;">
				<ul class="link">
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toShowCopySqls.action" >数据库备份</a>
					</li>
					<!-- <li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toRecoverSql.action" >数据库还原</a>
					</li> -->
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toCopyFile.action" >文件备份</a>
					</li>
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toRecoverFile.action" >文件还原</a>
					</li>
				</ul>
			</div>
			<div title="日志操作" style="padding:10px;">
				<ul class="link">
					<li class="changefont">
						<a class="easyui-linkbutton" data-options="plain:true" href="toShowLog.action">日志详情</a>
					</li>
				</ul>
			</div>
		</div>	
    </div>  
    <div id="main_panel" data-options="region:'center',title:'主界面'" style="padding:5px;">
    	<p style="font-size:15px;">welcome</p>
    </div> 
 
</body>
<!--第三步：引入jquery代码  必须在easyui之前-->
<script type="text/javascript" src="backjs/jquery.min.js"></script>
<!--第四步：引入easyui  -->
<script type="text/javascript" src="backjs/jquery.easyui.min.js"></script>
<!--第五步：引入语言包  支持中文   locale目录下-->
<script type="text/javascript" src="backjs/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
//linkbutton添加点击事件
$('.link>li>a').click(function(){
	var href=$(this).attr('href');
	$('#main_panel').panel('refresh',href);
	return false;	
});
</script>

</html>