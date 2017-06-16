<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();  //   /SpringMVC7douban (上下文路径)
				//		http             ://         localhost         :        8080          /SpringMVC7douban /
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
 <div>
	<label>选择备份路径：</label><input type="text" id="filePath" name="path" size="30"/><br/>
	<label>&nbsp;备份文件名：</label><input type="text" id="fileName" name="name" size="30"/>
	<input type="button" onclick="javascript:copy()" value="备份"/>
</div> 

<script>
function copy(){
	var filePath=$('#filePath').val();
	var fileName=$('#fileName').val();
	$.post('copySQL.action',{path:filePath,name:fileName},function(data){
		if(data=="success"){
			alert("备份成功");
		}
		else{
			alert("备份失败");
		}
	},'text');
}

    
</script>
