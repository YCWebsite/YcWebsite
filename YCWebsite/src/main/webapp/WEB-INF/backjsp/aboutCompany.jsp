<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<style type="text/css">
.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 10px 0px;
	margin-bottom: 15px;
	border-bottom: 1px solid #ccc;
	width:64%;
}
</style>
<div style="padding-left:40px;">
<div class="ftitle">关于公司</div>
	<div style="overflow: hidden;">
			<script id="aboutcompany" name="aboutcompany" type="text/plain" style="width:80%;height:250px;">
			${aboutCompany}
		</script>
	</div>
	<form id="aboutcompanyfm" method="post">
		<input type="hidden" value="关于公司" name="d_type"/>
		<input type="hidden" value="" name="d_desc"/>
	</form>
<br/>
<button type="button" onclick="update()">保存</button>
</div>

<!-- 配置文件-->
<script type="text/javascript" src="utf8-jsp/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="utf8-jsp/ueditor.all.js"></script>

<script type="text/javascript">
	var ue=UE.getEditor('aboutcompany');//初始化对象
	
	$("#aboutcompanyfm").form({
		url:"updateDataByType.action",
		success: function(data){
			if(data=="true"){
				$.messager.show({title:'提示',msg:'修改成功!',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert('提示','修改失败'); 
			}
	 	}
	}, 'json');
	//保存
	function update(){
		var tx=ue.getContentTxt();
		var text=$.trim(tx);
		$('input[name="d_desc"]').val(text);
		$("#aboutcompanyfm").submit();
	}
	
</script>
