<%@ page language="java" contentType="textml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<div id="global">
<form:form commandName="teachers" method="post">
	   <form:hidden path="t_id" name="t_id"></form:hidden>
       <p>
            <c:if test="${teachers.tpicURLStringList!=null}">
            	<image src="${teachers.tpicURLStringList}" style="width:540px;height:525px;"></image>
            </c:if>
        </p>
</form:form>
</div>