<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<style>
	p{
		padding:8px 125px;
		margin-top:20px;
	}
	
	#buttons{
		padding-left:170px;
	}
	#showUploadPic1{
		display:none;
		margin-left:130px;
		margin-top:0px;
	}
	#showPic{
		display:none;
		margin-left:150px;
	}
</style>
<div id="global">
<form:form commandName="team" id="team" enctype="multipart/form-data" method="post">
       <form:hidden path="t_id" name="t_id"></form:hidden>
        <p>
            <label for="t_name">姓名: </label>
            <form:input id="t_name" name="t_name" path="t_name"/>
        </p>
        <p>
            <label for="t_job">职位名称: </label>
            <form:input id="t_job" name="t_job" path="t_job"/>
        </p>
        <p>
            <label for="t_skill">经验: </label>
            <form:input id="t_skill" name="t_skill" path="t_skill"/>
        </p>
        <p>
            <label for="t_pic">已上传的图片: </label>
            <c:if test="${team.tpicURLStringList==null}">
            	<label>还未上传团队图片</label>
            </c:if>
            <c:if test="${team.tpicURLStringList!=null}">
            	<label><a href="javascript:void(0)" onclick="showPics()" name="yulan" id="yulan" >图片预览</a ></label>
            	<input id="t_pic" name="hiddens" type="hidden" value="${team.t_pic}"/>
            	<div id="showPic">
					<img id="view" name="view" src=""/> 
				</div>
            </c:if>
        </p>
        <p>
            <label for="t_pic">重新上传项目图片:</label>
        	<input name="t_picURL" id="t_picURL" type="file">
        	<div id="showUploadPic1">
        		<img name="preview"  src="" style="width:100px;height:100px;"/>
        	</div>
        </p>
        <p> <a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="update()" style="width:90px">确认修改</a> 
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#update_team').dialog('close')" style="width:90px">取消</a>
		</p>   
</form:form>
</div> 

<script type="text/javascript">
$("#team").form({
	url:"update_team.action",
	success: function(data){
		if(data=="true"){
			$.messager.show({title:'提示',msg:'修改成功',timeout:2000,showType:'slide'});
		}else{
			$.messager.alert('提示','修改失败');
		}
		$('#show_team').datagrid('load');
		$('#update_team').dialog('close');
	}
},'json');

function update(){
	$("#team").submit();
}

//图片预览
$("input[name='t_picURL']").change(function(){
	$('#showUploadPic1').css("display","block");
	var objUrl = getObjectURL(this.files[0]) ;
	if (objUrl) {
		$("img[name='preview']").attr("src", objUrl) ;
	}
}) ;

function showPics(){
	$('#showPic').css("display","block");
	//获取隐藏域的值
	var url=$("input[name='hiddens']").val();
	$("img[name='view']").attr("src",url);
};


//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}
</script> 
