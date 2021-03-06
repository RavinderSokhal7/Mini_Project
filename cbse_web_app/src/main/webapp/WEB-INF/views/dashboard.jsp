<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true" errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Library Webteam">
<title>Dashboard | RCL</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>

<jsp:include page="MainHeaderComp.jsp"></jsp:include>

<div id="page">
	<div id="content">
		<div id="title"><h2>Dashboard</h2>
		<span class="welcome">Welcome ${user.getName()},</span></div>
		
		<div class="body" align="center">
			<h3 style="margin:10px 0;font-size:24px">User Details:</h3>
			<div class="body" align="center" style="margin-top:0;font-size:115%;letter-spacing:1px;">
				<p style="margin:0"><strong>Username:</strong> ${user.getUsername()}</p>
				<p style="margin:0"><strong>Name:</strong> ${user.getName()}</p>
				<p style="margin:0"><strong>Contact:</strong> ${user.getContact()}</p>
				<p style="margin:0"><strong>Address:</strong> ${user.getAddress()}</p>
			</div>
		</div>
	</div>
			<ul id="side_menu">
		<li><a href="">Quick Links</a>
			<ul style="display:block">
				<c:if test="${not empty quicklinks}">
				    <c:forEach items="${quicklinks}" var="quicklink">
						<li><a href="${quicklink.getLink()}">${quicklink.getName()}</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</li>
		</ul>
		
	<div style="clear:both"></div>
</div>


<jsp:include page="footerComp.jsp"></jsp:include>
</body>
</html>