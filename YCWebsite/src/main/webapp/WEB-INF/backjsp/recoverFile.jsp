<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path=request.getContextPath();  //   /SpringMVC7douban (上下文路径)
			//		http             ://         localhost         :        8080          /SpringMVC7douban /
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<div>
	<form id="recoverFile" method="post" enctype="multipart/form-data">
		<label>请选择需要还原的文件类型：</label>
	 		<select id="type" name="type">
	 			<option value="images">图片</option>
	 			<option value="logs">日志</option>
	 		</select><br/>
	 		<label>请选择要还原的文件日期：</label><input type="date" id="fileDate" name="date" size="30"/><br/>
		<label>请选择要还原的文件：</label><input type="file" multiple="multiple" id="selectFiles" name="path" size="30"/>
		<input type="button" onclick="backSubmit()" value="还原"/>
	</form>
</div>
<script>
	$("#recoverFile").form({
		url:"recoverFile.action",
		success:function(data){
			if(data=="true"){
				$.messager.alert('提示','文件还原成功');
			}else{
				$.messager.alert('提示','文件还原失败');
			}
			$('#fileDate').val('');
			$('#selectFiles').val('');
		},
		error:function(data){
			$.messager.alert('提示','文件还原失败');
			$('#fileDate').val('');
			$('#selectFiles').val('');
		}
	},'text');

	function backSubmit(){
		$("#recoverFile").submit();
	}
</script>