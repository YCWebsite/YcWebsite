<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path=request.getContextPath();  //   /SpringMVC7douban (上下文路径)
			//		http             ://         localhost         :        8080          /SpringMVC7douban /
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
 <div>
 	<label>请选择需要备份的文件类型：</label>
 		<select id="type" name="type">
 			<option value="logs">日志</option>
 			<option value="images">图片</option>
 		</select><br/>
	<label>选择文件备份的路径：</label><input type="text" id="targetFilePath" name="targetFilePath" size="30"/><br/>
	<label>&nbsp;新的文件名：</label><input type="text" id="targetFileName" name="targetFileName" size="30"/>
	<input type="button" onclick="javascript:copyFile()" value="备份"/>
</div> 

<script>
function copyFile(){
	var targetFilePath=$('#targetFilePath').val();
	var targetFileName=$('#targetFileName').val();
	var options=$("select[name='type']").val();
	alert("选择的文件："+options);
	$.post('copyFile.action',{targetFileName:targetFileName,targetFilePath:targetFilePath,options:options},function(data){
		if(data=="true"){
			alert("文件备份成功");
		}
		else{
			alert("文件备份失败");
		}
	},'text');
}

    
</script>
