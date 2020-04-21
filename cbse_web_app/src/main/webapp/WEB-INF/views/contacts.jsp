<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true" errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>Contact Us | CBSE</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="header">
	<h1><a href="#">Component Based Software Development</a></h1>
			<ul id="nav-menu">
				<c:if test="${not empty menulinks}">
				    <c:forEach items="${menulinks}" var="menulink">
						<li><a href="${menulink.getLink()}">${menulink.getName()}</a></li>
					</c:forEach>
				</c:if>
			</ul>
</div>
<div id="page">
	<div id="content">
		<div id="title"><h2>Contacts</h2>
		<span class="welcome">Welcome,</span></div>
		
		<div class="body" align="center">
			<fieldset class="box">
			<h2 style="margin:10px 15px;font-size:18px">Contact Details</h2>
			<div class="body" align="center" style="margin-top:0;font-size:115%;letter-spacing:1px;">
				<p>Please contact the system administrator for any queries.<br>Contact: +91-XXXXXXXXXX<br>Email: xxxxxxxxxx@gmail.com</p>
			</div>
			</fieldset>
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