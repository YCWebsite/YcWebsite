<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<style>
	p{
		padding:0px 125px;
		margin-top:20px;
	}
	
	#buttons{
		padding-left:160px;
	}
	#showPic{
		display:none;
		margin-left:150px;
	}
	#showUploadPic{
		display:none;
		margin-left:500px;
		margin-top:-100px;
	}
</style>

<div id="global">
<form:form commandName="jobDetails" id="jobDetails" name="jobDetails" enctype="multipart/form-data" action="update_jobDetails.action" method="post">
       <form:hidden path="jd_id"></form:hidden>
        <p>
            <label for="jd_name">学员姓名: </label>
            <form:input id="jd_name" name="jd_name" path="jd_name"/>
        </p>
        <p>
            <label for="jd_salary">薪资: </label>
            <form:input id="jd_salary" name="jd_salary" path="jd_salary"/>
        </p>
        <p>
            <label for="jd_company">就业公司: </label>
            <form:input id="jd_company" name="jd_company" path="jd_company"/>
        </p>
        <p>
            <label for="jd_school">毕业学校: </label>
            <form:input id="jd_school" name="jd_school" path="jd_school"/>
        </p>
         <p>
            <label for="jd_profession">专业: </label>
            <form:input id="jd_profession" name="jd_profession" path="jd_profession"/>
        </p>
        <p>
            <label for="jd_emptime">就业时间: </label>
            <form:input type="date" id="jd_emptime" name="jd_emptime" path="jd_emptime"/>
        </p>  
         <p>
            <label for="jd_pic">已上传的图片: </label>
            <c:if test="${jobDetails.jd_picURLStringList==null}">
            	<label>还未上传学员图片</label>
            </c:if>
            <c:if test="${jobDetails.jd_picURLStringList!=null}">
            	<label><a  href="javascript:void(0);" onclick="showPics()" name="yulan" id="yulan" >图片预览</a></label>
            	<input id="jd_pic" name="hiddens" type="hidden" value="${jobDetails.jd_pic }"/>
            	<div id="showPic">
					<img id="view" name="view" src="" style="width:100px;height:100px;"/> 
				</div>
            </c:if>
        </p> 
         <p>
            <label for="jd_picURL">重新上传项目图片:</label>
        	<input name="jd_picURL" id="jd_picURL" type="file"/>
        	<div id="showUploadPic">
        		<img name="preview" id="preview" src="" style="width:100px;height:100px;"/>
        	</div>
        </p> 
         <p id="buttons">
           <a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="update()" style="width:90px">确认修改</a>&nbsp;&nbsp; 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#update_jobDetails').dialog('close')" style="width:90px">取消</a>
        </p> 
</form:form>
</div>
<script type="text/javascript">
$("#jobDetails").form({
	url:"update_jobDetails.action",
	success: function(data){
		if(data=="true"){
			$.messager.show({title:'提示',msg:'修改成功'});
		}else{
			$.messager.alert('info','修改失败');
		}
		
		$('#show_jobDetails').datagrid('load');
		$('#update_jobDetails').dialog('close');
	}
},'json');

function update(){
	var money=$('#jd_salary').val();
	if(isNaN(money)){
		$.messager.alert('提示','添加的参数错误'); 
		return;
	}
	
	$("#jobDetails").submit();
}
//图片预览
$("input[name='jd_picURL']").change(function(){
	$('#showUploadPic').css("display","block");
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
