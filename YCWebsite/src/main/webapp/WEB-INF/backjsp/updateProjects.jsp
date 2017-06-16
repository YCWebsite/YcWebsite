<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<style>
p {
	padding: 6px 125px;
	margin-top: 10px;
}

#projectsdlg-buttons2 {
	padding-left: 150px;
}

#showUploadPic2 {
	display: none;
	margin-left: 270px;
	margin-top: -100px;
}

#showPic {
	display: none;
	margin-left: 150px;
}
</style>
<div id="global">
	<form:form commandName="projects" id="projects" enctype="multipart/form-data" method="post" buttons="#projectsdlg-buttons2">
		<form:hidden path="p_id" name="p_id"></form:hidden>
		<p>
			<label for="p_name">项目名: </label>
			<form:input id="p_name" name="p_name" path="p_name" />
		</p>
		<p>
			<label for="p_developer">开发者: </label>
			<form:input id="p_developer" name="p_developer" path="p_developer" />
		</p>
		<p>
			<label for="p_time">开发时间: </label>
			<form:input type="date" id="p_time" name="p_time" path="p_time" />
		</p>
		<p>
			<label for="p_addr">项目发布地址: </label>
			<form:input id="p_addr" name="p_addr" path="p_addr" />
		</p>
		<p>
			<label for="p_pic">已上传的图片: </label>
			<c:if test="${projects.picsStringList==null}">
				<label>还未上传项目图片</label>
			</c:if>
			<c:if test="${projects.picsStringList!=null}">
				<label><a href="javascript:void(0)" onclick="showPics()" name="yulan" id="yulan">图片预览</a></label>
				<input id="pic" name="hiddens" type="hidden" value="${projects.picsStringList}"/>
				<div id="showPic">
					<img id="view" name="view" src=""
						style="width: 100px; height: 100px;" />
				</div>
				<%-- <label><a href="${projects.picsStringList}">下载图片</a></label> --%>
			</c:if>
		</p>
		<p>
			<label for="p_pic">重新上传项目图片:</label> 
			<input id="picUrl" name="picUrl" type="file">
			<div id="showUploadPic2">
				<img name="preview" id="preview" src="" style="width:100px;height:100px;"/>
        	</div>
        </p>
	</form:form>
</div>
<div id="projectsdlg-buttons2">
<a href="javascript:void(0)" class="easyui-linkbutton c6"
	iconCls="icon-ok" onclick="update()" style="width: 90px">确认修改</a>&nbsp;&nbsp; <a
	href="javascript:void(0)" class="easyui-linkbutton"
	iconCls="icon-cancel"
	onclick="javascript:$('#update_projects').dialog('close');$('#showPic').css({display:'none'})"
	style="width: 90px">取消</a>
</div>
<script type="text/javascript">
	$("#projects").form({
		url : "update_projects.action",
		success : function(data) {
			if (data == "true") {
				$.messager.show({
					title : '提示',
					msg : '修改成功',
					timeout : 2000,
					showType : 'slide'
				});
			} else {
				$.messager.alert('提示', '修改失败');
			}
			$('#show_projects').datagrid('load');
			$('#update_projects').dialog('close');
		}
	}, 'json');

	function update() {
		$("#projects").submit();
	}

	//上传
	$("input[name='picUrl']").change(function() {
		$('#showUploadPic2').css("display", "block");
		var objUrl = getObjectURL(this.files[0]);
		if (objUrl) {
			$("img[name='preview']").attr("src", objUrl);
		}
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
	function showPics() {
		$('#showPic').css("display", "block");
		//获取隐藏域的值
		var url = $("input[name='hiddens']").val();
		console.info(url);
		$("img[name='view']").attr("src", url);
	};

</script>
