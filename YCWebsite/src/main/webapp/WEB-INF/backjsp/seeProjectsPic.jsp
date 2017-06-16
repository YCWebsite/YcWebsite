<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<div id="global">
<form:form commandName="projects" method="post">
       <p>
            <c:if test="${projects.picsStringList!=null}">
            	<image src="${projects.picsStringList}" style="width:500px;height:500px;"></image>
            </c:if>
        </p>
</form:form>
</div>