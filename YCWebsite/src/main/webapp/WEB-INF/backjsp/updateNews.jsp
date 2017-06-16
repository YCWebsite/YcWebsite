<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<style>
	p{
		padding:1px 50px;
		margin-top:12px;
	}
	
	#newsdlg-buttons{
		padding-left:489px;
	}
</style>
<div id="global">
<form:form commandName="news" id="news" enctype="multipart/form-data" method="post" buttons="#newsdlg-buttons">
       <form:hidden path="n_id" name="n_id"></form:hidden>
	        <p>
	            <label for="n_title">标题: </label>
	            <form:input id="n_title" path="n_title" style="width:260px;"/>
	        </p>
	        <p>
	            <label for="n_reportor">发布者: </label>
	            <form:input id="n_reportor" path="n_reportor" style="width:248px;"/>
	        </p>
	        <p>
	            <label for="n_sort">排序: </label>
	            <form:input id="n_sort" path="n_sort" style="width:260px;"/>
	        </p>
	        <p>
	            <label for="content2">内容: </label>
	            
	            <div id="neirong" style="overflow: hidden;padding-left:50px;">
					<script id="content" name="content" type="text/plain" style="width:590px;height:144px; ">
						${news.n_content}
					</script>
				</div>
				
				<input type="hidden" value="" name="n_content"/>
	        </p>
</form:form>
</div> 
<div id="newsdlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-ok" onclick="update()" style="width:90px">确认修改</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#update_news').dialog('close')" style="width:90px">取消</a>
</div>

<script type="text/javascript">

$("#news").form({
	url:"update_news.action",
	success: function(data){
		if(data=="true"){
			$.messager.show({title:'提示',msg:'修改成功',timeout:2000,showType:'slide'});
		}else{
			$.messager.alert('提示','修改失败');
		}
		$('#show_news').datagrid('load');
		$('#update_news').dialog('close');
	}
},'json');

function update(){
	var tx=ue2.getContentTxt();
	var text=$.trim(tx);
	$('input[name="n_content"]').val(text);
	$("#news").submit();
}
</script> 
