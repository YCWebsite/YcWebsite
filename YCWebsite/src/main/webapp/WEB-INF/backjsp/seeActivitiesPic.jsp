<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<div id="global">
	<form:form commandName="activities" method="post">
		
		<p>
			 <c:if test="${activities.picsStringList!=null}">
		        <c:forEach items="${activities.picsStringList}" var="picUrl">
		            <image src="${picUrl}" style="width:500px;height:500px;"></image>
		        </c:forEach>
            </c:if>
		</p>
	</form:form>
</div>