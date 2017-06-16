<%@ page language="java" contentType="text/html; charset=UTF-8" 
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台登录</title>
<link type="text/css" rel="stylesheet" href="css/login.css">
</head>
<body>
	<div id="loginDiv">
		<c:if test="${errmsg!=''}">
			<font style="color:red"><c:out value="${errmsg}"></c:out></font>
		</c:if>
		<form action="login.action" method="post" id="loginForm">
			<image src="images/logo.png" id="images">
			<label id="font">源辰信息科技</label><br/>
			用户名：<input name="u_name" required="required" placeholder="请输入用户名"/><br/>
			密&nbsp;&nbsp;&nbsp;码：<input name="u_password" type="password" required="required" placeholder="请输入密码"/><br/>
			验证码：<input type="text" name="validateCode" id="dd" /><br/><br/>
			<img id="randImg" border=0 src="imageCode.jsp">
			<a href="javascript:loadImage()">换一张</a>&nbsp;&nbsp;<br/>
			<input type="submit" value="登录" id="loginButton"/>
		</form>
	</div>
</body>
<script>
//刷新验证码
function loadImage(){
	var img=document.getElementById("randImg");
	img.src="imageCode.jsp?r=" +Math.random;
}
</script>
</html>