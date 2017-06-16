<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<style>
	p{
		padding:6px 125px;
		margin-top:10px;
	}
	
	#activitiesdlg-buttons2{
		padding-left:160px;
	}
	
	#showUploadPic2 {
		display: none;
		margin-left: 150px;
		margin-top: -10px;
	}

	 #showPic {
		display: none;
		margin-left: 150px;
	}
</style>
<div id="global">
<form:form commandName="activities" id="activities" enctype="multipart/form-data" method="post" buttons="#activitiesdlg-buttons2">
       <form:hidden path="ac_id" name="ac_id"></form:hidden>
        <p>
            <label for="ac_time">活动时间: </label>
            <form:input type="date" id="ac_time" path="ac_time"/>
        </p>
        <p>
            <label for="ac_illus">活动说明: </label>
            <form:textarea id="ac_illus" style="height:80px;" path="ac_illus"/>
        </p>
        <%-- <p>
            <label for="ac_pic">已上传的图片: </label>
            <c:if test="${activities.picsStringList==null}">
            	<label>还未上传活动图片</label>
            </c:if>
            <c:if test="${activities.picsStringList!=null}">
		        <c:forEach items="${activities.picsStringList}" var="picUrl">
			        <label><a href="javascript:void(0)" onclick="showPics()" name="yulan" id="yulan">图片预览</a></label>
					<input id="pic" name="hiddens" type="hidden" value="${activities.picsStringList[0]}"/>
					<div id="showPic">
						<img id="view" name="view" src="" style="width: 100px; height: 100px;" />
					</div>
		        </c:forEach>
            </c:if>
        </p> --%>
        <p>
            <label for="ac_pic">重新上传活动图片:</label>
        	<input name="picUrl" id="picUrl" multiple="multiple" type="file">
        	<div id="showUploadPic2">
				
        	</div>
        </p>
</form:form>
</div> 
<div id="activitiesdlg-buttons2">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
	iconCls="icon-ok" onclick="update()" style="width:90px">确认修改</a>&nbsp;&nbsp; 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
	onclick="javascript:$('#update_activities').dialog('close')" style="width:90px">取消</a>
</div>

<script type="text/javascript">
$("#activities").form({
	url:"update_activities.action",
	success: function(data){
		alert(data);
		if(data=="true"){
			$.messager.show({title:'提示',msg:'修改成功',timeout:2000,showType:'slide'});
		}else{
			$.messager.alert('提示','修改失败');
		}
		$('#show_activities').datagrid('load');
		$('#update_activities').dialog('close');
	}
},'json');

function update(){
	$("#activities").submit();
}

//上传
$("input[name='picUrl']").change(function() {
	$('#showUploadPic2').css("display","block");
		$('#showUploadPic2').empty('');
		var str='';
		for(var i=0;i<this.files.length;i++){
			var objUrl = getObjectURL(this.files[i]) ;
			if (objUrl) {
				str+='<img name="preview" id="preview" src="'+objUrl+'" style="width:100px;height:100px;"/>';
			}
		}
	$('#showUploadPic2').append(str);
});
//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

//图片预览
/* function showPics() {
	$('#showPic').css("display", "block");
	//获取隐藏域的值
	var url = $("input[name='hiddens']").val();
	$("img[name='view']").attr("src", url);
}; */
</script> 
