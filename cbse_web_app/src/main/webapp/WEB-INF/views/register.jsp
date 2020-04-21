<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Library Webteam">
<title>Register | CBSE</title>
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
		<div id="title"><h2>Register</h2>
		<span class="welcome">Welcome guest,</span></div>
		
		<div class="body" align="center">
			<form method="post" action="register">
				<h3 style="margin:10px 0;font-size:24px">Fill the form.</h3>
				<div style="margin:10px 0">
					<label style="display:block">Username</label>
					<input type="text" name="username" style="padding:8px 10px;width:84.5%;border: 1px solid #999;text-transform:lowercase;" placeholder="dearjohn98" value="${username}" required>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Name</label>
					<input type="text" name="name" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" placeholder="John Doe" value="${name}" required>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Contact</label>
					<input type="text" name="contact" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" max-length="15" placeholder="+91-9876543210" value="${contact}" required>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Address</label>
					<input type="text" name="address" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" placeholder="Door 1-1-1/1, Park Street, Kolkata" value="${address}" required>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Password (Atleast 6 chars)</label>
					<input type="password" name="password" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" min-length="6" placeholder="password" value="${password}" required>
				</div>
				<input type="submit" value="Submit" style="width:12%;margin:0;padding:8px 10px;">
				<p id="errorMessage" <c:if test="${status == false}">style="color:red"</c:if><c:if test="${status == true}">style="color:green"</c:if>>${errorMessage}</p>
			</form>
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

<jsp:include page="footerComp.jsp"></jsp:include>s

</body>
</html>