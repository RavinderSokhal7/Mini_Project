<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true" errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>View-Details | RCL</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>

<jsp:include page="MainHeaderComp.jsp"></jsp:include>

<div id="page">
	<div id="content">
		<div id="title"><h2>View Details</h2>
		<span class="welcome">Welcome ${user},</span></div>
		
		<div class="body" align="center">
			<form method="get">
				<h3 style="margin:10px 0;font-size:24px">Fill the form.</h3>
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
				<input type="submit" value="View Details" style="width:12%;margin:0;padding:8px 10px;">
				<p id="errorMessage" <c:if test="${status == false}">style="color:red"</c:if><c:if test="${status == true}">style="color:green"</c:if>>${errorMessage}</p>
				<c:if test="${not empty user_data}">
				<br>
				<h3 style="margin:10px 0;font-size:24px">User Details:</h3>
					<div class="body" align="center" style="margin-top:0">
						<p style="margin:0"><strong>Name:</strong> ${user_data.getName()}</p>
						<p style="margin:0"><strong>Contact:</strong> ${user_data.getContact()}</p>
						<p style="margin:0"><strong>Address:</strong> ${user_data.getAddress()}</p>
						<div>
							<p style="margin:0"><strong>Issued Books:</strong></p>
							<ul style="margin:0">
								<c:if test="${not empty user_data_books}">
								    <c:forEach items="${user_data_books}" var="book">
										<li>${book.getName()} - ${book.getAuthor()}</li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>
				</c:if>
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

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>