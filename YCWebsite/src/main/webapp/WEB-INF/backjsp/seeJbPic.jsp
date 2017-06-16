<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<%
	String path=request.getContextPath();  //   /SpringMVC7douban (上下文路径)
				//		http             ://         localhost         :        8080          /SpringMVC7douban /
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<div id="global">
<form:form commandName="jobDetails" name="jobDetails" id="jobDetailsPic" method="post">
      <%--  <form:hidden path="jd_id" name="jd_id"></form:hidden> --%>
       <p>
            <c:if test="${jobDetails.jd_pic!=null}">
            	<image src="${jobDetails.jd_pic}"></image>
            </c:if>
        </p>
</form:form>
</div> 