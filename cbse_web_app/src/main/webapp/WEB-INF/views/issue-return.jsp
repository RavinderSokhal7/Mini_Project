<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Library Webteam">
<title>Issue/Return | Library</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="header">
	<h1><a href="#">Library Management System</a></h1>
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
		<div id="title"><h2>Issue/Return</h2>
		<span class="welcome">Welcome ${user},</span></div>
		
		<div class="body" align="center">
			<form method="post" action="issue-return">
				<h3 style="margin:10px 0;font-size:24px">Fill the form.</h3>
				<p>An user can only issue a maximum of 3 books at a time.</p>
				<div style="margin:10px 0">
					<label style="display:block">Username</label>
					<select name="username" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required>
						<option selected disabled value="">-- Select the user --</option>
						<c:if test="${not empty users}">
						    <c:forEach items="${users}" var="user">
								<option value="${user.getUsername()}">${user.getName()} - ${user.getUsername()}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Books</label>
					<select name="bookid" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required>
						<option selected disabled value="">-- Select the book --</option>
						<c:if test="${not empty books}">
						    <c:forEach items="${books}" var="book">
								<option value="${book.getID()}">${book.getName()} - ${book.getAuthor()}</option>
							</c:forEach>
						</c:if>
					</select>
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

<div id="footer" style="position:relative; font-size:14px;" >
<p id="legal">&copy;Copyright Library All Rights Reserved. Designed by <a href="contact">Library Webteam</a></p>
	<p id="links"  ><a href="http://172.31.102.36">172.31.102.36</a> 
	
	</p>
</div>

</body>
</html>