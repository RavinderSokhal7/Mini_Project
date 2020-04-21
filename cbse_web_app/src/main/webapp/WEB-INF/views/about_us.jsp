<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>About Us | CBSE</title>
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
		<div id="title"><h2>About Us</h2>
		<span class="welcome">CBSE,</span></div>
		<div class="body" align="center">
			<fieldset class="box">
				<div class="body" align="center" style="margin-top:0;font-size:115%;letter-spacing:1px;">
					<h2 style="margin:10px 0;font-size:18px">Component Based Software Development</h2>
					<p>This is a software construction app using components.<br>You can preview the constructed page as per your selection of components and even download<br>the components as well.<br>
					The components are the "jsp" pages that you can use independently in your own software/app.<br>
					</p>
					<h2 style="margin:10px 0;font-size:18px">Steps to construct page in this website -</h2>
					<p>
					1. Go to Home tab of this website.<br>
					2. Select the category of website page you want to construct.<br>
					3. Select from components given for that category.<br>
					4. To preview the constructed page, click on "Preview Page" button.<br>
					5. To download the components, click on "Download Components" button.<br><br>
					That's it, CHEERS!
					</p>
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

<div id="footer" style="position:relative; font-size:14px;" >
<p id="legal">&copy;Copyright All Rights Reserved. Designed by <a href="contact">Web team</a></p>
	<!-- <p id="links"  ><a href="http://172.31.102.36">172.31.102.36</a> 
	
	</p> -->
</div>

</body>
</html>