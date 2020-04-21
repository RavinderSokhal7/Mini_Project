<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>Update Details | CBSE</title>
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
		<div id="title"><h2>Update Details</h2>
		<span class="welcome">Welcome ${user.getName()},</span></div>
		
		<div class="body" align="center">
			<h3 style="margin:10px 0;font-size:24px">User Details:</h3>
			<form method="POST">
				<div style="margin:10px 0">
					<label style="display:block">Name</label>
					<input type="text" name="name" placeholder="Enter Your Name" value="${user.getName()}" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required/>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Contact</label>
					<input type="text" name="contact" placeholder="Enter Your Contact" value="${user.getContact()}" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required/>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Address</label>
					<input type="text" name="address" placeholder="Enter Your Address" value="${user.getAddress()}" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required/>
				</div>
				<input type="submit" value="Update" style="width:12%;margin:0;padding:8px 10px;">
				<p <c:if test="${status == false}">style="color:red"</c:if><c:if test="${status == true}">style="color:green"</c:if>>${errorMessage1}</p>
			</form>
			<br>
			<h3 style="margin:10px 0;font-size:24px">Change Password</h3>
			<form method="POST" style="padding-top:0">
				<div style="margin:10px 0">
					<label style="display:block">Old Password</label>
					<input type="password" name="old_password" placeholder="Password" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required/>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">New Password</label>
					<input type="password" name="new_password" placeholder="Password" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required/>
				</div>
				<input type="submit" value="Update" style="width:12%;margin:0;padding:8px 10px;">
				<p <c:if test="${status == false}">style="color:red"</c:if><c:if test="${status == true}">style="color:green"</c:if>>${errorMessage2}</p>
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

<div id="footer" style="position:relative; font-size:14px;" >
<p id="legal">&copy;Copyright All Rights Reserved. Designed by <a href="contact">Web team</a></p>
	<!-- <p id="links"  ><a href="http://172.31.102.36">172.31.102.36</a> 
	
	</p> -->
</div>


</body>
</html>