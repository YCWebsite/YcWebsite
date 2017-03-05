<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员项目管理</title>
</head>
<body>
<table id="show_projects" ></table>
<script type="text/javascript">
	$('#show_projects').datagrid({    
	    url:'../member.action',
	    queryParams:{op:'findByPage'},
	    pagination:true,
	    pageNumber:1,      //初始化显示页码
	    pageSize:2,        //初始化每页显示的记录数
	    pageList:[1,2,3,4,5],  //自定义每页显示的记录数
	    columns:[[ 
					{field:'ck',checkbox:true,width:80},
					{field:'p_id',title:'编号',width:80},   
	                {field:'p_name',title:'项目名',width:80}, 
	                {field:'p_developer',title:'开发者',width:100},   
	                {field:'p_time',title:'开发时间',width:200},
	                {field:'p_pic',title:'会员图片',width:100,formatter:function(value,row,index){
	                	if(value!=null&& value!=""){
	                		return "<img src='../../../"+value+"/>";
	                	}else{
	                		return '无学员图片';
	                	}
	                }}
	    ]]
	});  
</script>

</body>
</html>