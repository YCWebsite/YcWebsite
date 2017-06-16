<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<style>
	p{
		padding:3px 40px;
		margin-top:20px;
	}
	
	#buttons{
		padding-left:170px;
	}

	#showUploadPic3{
			display:none;
			margin-left:50px;
			margin-top:0px;
		}
	#showUploadPic4{
			display:none;
			margin-left:50px;
			margin-top:0px;
		}
	#showPic1{
		display:none;
		margin-left:150px;
	}
	#showPic2{
		display:none;
		margin-left:150px;
	}
</style>
<div id="global">
<form:form commandName="courses" id="courses" enctype="multipart/form-data" method="post">
       <form:hidden path="cs_id" name="cs_id"></form:hidden>
        <p>
            <label for="cs_name">课程姓名: </label>
            <form:input id="cs_name" name="cs_name" path="cs_name"/>
        </p>
        <p>
            <label for="cs_namePic">已上传的课程图片: </label>
        	<c:if test="${courses.csnameURLStringList==null}">
            	<label>还未上传课程图片</label>
            </c:if>
            <c:if test="${courses.csnameURLStringList!=null}">
            	<label><a href="javascript:void(0)" onclick="showPics1()" name="yulan1" id="yulan1" >图片预览</a ></label>
            	<input id="cs_namePic" name="hiddens1" type="hidden" value="${courses.cs_namePic}"/>
            	<div id="showPic1">
					<img id="view1" name="view1" src=""/> 
				</div>
            </c:if>
        </p>
        <p>
            <label for="cs_namePic">重新上传课程图片:</label>
        	<input name="cs_nameURL" id="cs_nameURL" type="file">
        	<div id="showUploadPic3">
        		<img name="preview1"  src="" style="width:100px;height:100px;"/>
        	</div>
        </p>
        <p>
            <label for="cs_version">版本号: </label>
            <form:input id="cs_version" name="cs_version" path="cs_version"/>
        </p>
        <p>
            <label for="cs_text">文字说明: </label>
            <form:input id="cs_text" name="cs_text" path="cs_text"/>
        </p>
        <p>
            <label for="cs_pic">图片说明: </label>
            <c:if test="${courses.cspicURLStringList==null}">
            	<label>还未上传图片说明</label>
            </c:if>
            <c:if test="${courses.cspicURLStringList!=null}">
            	<label><a href="javascript:void(0)" onclick="showPics2()" name="yulan2" id="yulan2" >图片预览</a ></label>
            	<input id="cs_pic" name="hiddens2" type="hidden" value="${courses.cs_pic }"/>
            	<div id="showPic2">
					<img id="view2" name="view2" src=""/> 
				</div>
            </c:if>
        </p>
        <p>
            <label for="cs_pic">重新上传图片说明:</label>
        	<input name="cs_picURL" id="cs_picURL" type="file">
        	<div id="showUploadPic4">
        		<img name="preview2"  src="" style="width:100px;height:100px;"/>
        	</div>
        </p>
        <p> <a href="javascript:void(0)" class="easyui-linkbutton c6"
				iconCls="icon-ok" onclick="update()" style="width:90px">确认修改</a> 
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#update_courses').dialog('close')" style="width:90px">取消</a>
		</p>   
</form:form>
</div> 

<script type="text/javascript">
$("#courses").form({
	url:"update_courses.action",
	success: function(data){
		if(data=="true"){
			$.messager.show({title:'提示',msg:'修改成功',timeout:2000,showType:'slide'});
		}else{
			$.messager.alert('提示','修改失败');
		}
		$('#show_courses').datagrid('load');
		$('#update_courses').dialog('close');
	}
},'json');

function update(){
	$("#courses").submit();
}
//图片预览
$("input[name='cs_nameURL']").change(function(){
	$('#showUploadPic3').css("display","block");
	var objUrl = getObjectURL(this.files[0]) ;
	if (objUrl) {
		$("img[name='preview1']").attr("src", objUrl) ;
	}
}) ;

$("input[name='cs_picURL']").change(function(){
	$('#showUploadPic4').css("display","block");
	var objUrl = getObjectURL(this.files[0]) ;
	if (objUrl) {
		$("img[name='preview2']").attr("src", objUrl) ;
	}
}) ;

function showPics1(){
	$('#showPic1').css("display","block");
	//获取隐藏域的值
	var url=$("input[name='hiddens1']").val();
	$("img[name='view1']").attr("src",url);
};
function showPics2(){
	$('#showPic2').css("display","block");
	//获取隐藏域的值
	var url=$("input[name='hiddens2']").val();
	$("img[name='view2']").attr("src",url);
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
