<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<style>
	p{
		padding:0px 120px;
		margin-top:18px;
	}
	
	#buttons{
		padding-left:170px;
	}
</style>
<div id="global">
<form:form commandName="employ" id="employ" enctype="multipart/form-data" action="update_employ.action" method="post">
       <form:hidden path="e_id" id="e_id" name="e_id"></form:hidden>
        <p>
            <label for="e_position">职&nbsp;&nbsp;位:&nbsp; </label>
            <form:input id="e_position" name="e_position" path="e_position" style="width:200px;"/>
        </p>
        <p>
            <label for="e_validtime">有效时间:&nbsp; </label>
            <form:input type="date" id="e_validtime" name="e_validtime" path="e_validtime" style="width:200px;"/>
        </p>
        <p>
            <label for="e_salary">薪资待遇: &nbsp;</label>
            <form:input id="e_salary" name="e_salary" path="e_salary" style="width:200px;"/>
        </p>
        <p>
            <label for="e_amount">招聘人数: &nbsp;</label>
            <form:input id="e_amount" name="e_amount" path="e_amount" style="width:200px;"/>
        </p>
        <p>
            <label for="e_detail">详细说明: &nbsp;</label>
            <form:textarea id="e_detail" name="e_detail" path="e_detail" style="width:200px;height:100px;"/>
        </p>
        <p>
            <label for="e_addr">简历投递地址: </label>
            <form:input id="e_addr" name="e_addr" path="e_addr" style="width:200px;"/>
        </p>
        <p>
            <label for="todoa">状&nbsp;&nbsp;态: &nbsp;</label>
            <form:input id="todoa" name="todoa" path="todoa" style="width:200px;"/>
        </p>
        
        <p id="buttons">
           <a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="update()" style="width:90px">确认修改</a>&nbsp;&nbsp; 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#update_employ').dialog('close')" style="width:90px">取消</a>
        </p> 
        
</form:form>
</div> 

<script type="text/javascript">
$("#employ").form({
	url:"update_employ.action",
	success: function(data){
		if(data=="true"){
			$.messager.show({title:'提示',msg:'修改项目成功'});
		}else{
			$.messager.alert('info','修改项目失败');
		}
		
		$('#show_employ').datagrid('load');
		$('#update_employ').dialog('close');
	}
},'json');

function update(){
	$("#employ").submit();
}
</script> 
