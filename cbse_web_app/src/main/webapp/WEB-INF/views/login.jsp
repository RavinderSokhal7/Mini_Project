<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>Login | CBSE</title>
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
		<div id="title"><h2>Login</h2>
		<span class="welcome">Welcome User,</span></div>
		<div class="body" align="center">
			<form method="post" action="login" class="login">
				<fieldset class="box">
				<table>
				<tr><td>Login Id</td><td><input type="text" id="txt" placeholder="Enter Id" name="loginid" value="${loginid}" required/></td></tr>
				<tr><td>Password</td><td><input type="password" id="txt" placeholder="Enter Password" name="password" value="${password}" required/></td></tr><tr></tr>
				<tr><td colspan="2" align="center"><input id="btn" type="submit" style="width:100%;" value="Login" /></td></tr>
				<tr><td colspan="2"><span id="msg">${msg}</span></td></tr>
				</table>
				</fieldset>
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